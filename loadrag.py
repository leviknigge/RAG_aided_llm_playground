from ragatouille import RAGPretrainedModel
import requests
import json

import zipfile
import pprint
import random
import copy
import os
import pandas as pd
import pickle
import json
import sklearn
import nltk
import torch
import numpy as np
from collections import Counter

def main():

    model_path = r".ragatouille/colbert/none/2025-05/01/15.40.43/checkpoints/colbert"

    # RAG = RAGPretrainedModel.from_pretrained(model_path)

    path_to_index = ".ragatouille/colbert/indexes/test/"
    RAG = RAGPretrainedModel.from_index(path_to_index)
    
    # print("model loaded")
    # print(RAG.model_name)



     # ################################
    # Evaluation data
    eval_kb_data = {}
    eval_kb_sents = []

    eval_train_data = {}
    eval_original_data = {}
    eval_queries = []
    eval_contexts = []

    root = r"/home/lknigge/augmented_llm_playground_fabian/data/LogicBench"
    eval_kb_path = r"/abducted_data/binary/fol_eval_kb.jsonl"
    eval_train_path = r"/abducted_data/binary/fol_eval.jsonl"
    eval_original_path = r"/transformed_data/binary/fol_eval.jsonl"

    with open(root + eval_original_path, "r") as f:
        for line in f:
            datapoint = json.loads(line)
            eval_original_data[datapoint["id"]] = datapoint

    with open(root + eval_train_path, "r") as f:
        for line in f:
            datapoint = json.loads(line)
            eval_train_data[datapoint["id"]] = datapoint
            
            # print(datapoint['query'])
            eval_queries.extend([datapoint["query"]])    
            eval_contexts.extend([datapoint["context"]]) 

    with open(root + eval_kb_path, "r") as f:
        for line in f:
            datapoint = json.loads(line)
            # print(datapoint.items()/?)
            id, sent = list(datapoint.items())[0]
            # print(sent)
            # break

            if not sent == []:
                eval_kb_data[id] = sent
                eval_kb_sents.extend(sent)
            if sent == []:
                eval_kb_sents.extend([""])
                eval_kb_data[id] = [""]

    # print(eval_kb_sents[0])
    # ################################
    # CREATE INDEX
    # RAG = RAGPretrainedModel.from_pretrained("colbert-ir/colbertv2.0")


    # RAG.index(
    #     collection=eval_kb_sents, 
    #     index_name="test", 
    #     split_documents=False
    #     )
    ####################################


    # path_to_index = ".ragatouille/colbert/indexes/test/"
    # RAG = RAGPretrainedModel.from_index(path_to_index)


    path_to_index = ".ragatouille/colbert/indexes/untrained/"
    RAG = RAGPretrainedModel.from_index(path_to_index)

    # RAG = RAGPretrainedModel.from_pretrained("colbert-ir/colbertv2.0")
    ####################################
    print("index loaded")
    
    
    def retrieve_x(rag_model, queries, k=1):
        return rag_model.search(query=queries, k=k)

    
    def evaluate(rag_model, kb_data, kb_sents, train_data):
        new_data = dict()
        outputs = np.array([np.array([0,0,0,0,0])])
        j = 0
        x_list = []
        for i, (id,data) in enumerate(train_data.items()):
            x = data['context'] + " " + data['query']
        #     x_list.extend(x)
        #     top_sents = retrieve_x(rag_model, x)

        # for i, (id,data) in enumerate(train_data.items()):
        #     top_sent = top_sents[i][0]['content']

            top_sent = retrieve_x(rag_model, x)[0]['content']
            true_sent = kb_data[id[:-2]][0]

            if top_sent == true_sent:
                outputs = np.vstack((outputs, np.array([[1, i, id, top_sent, true_sent]])))
                # print(top_sent)
            else:
                outputs = np.vstack((outputs, np.array([[0, i, id, top_sent, true_sent]])))

            new_data[id] = copy.deepcopy(data)
            new_data[id]["context"] = data['context'] + " " + top_sent
            j = j + 1
            # if j > 2:
                # break
        return outputs[1:], new_data

    
    def evaluate_5(rag_model, kb_data, kb_sents, train_data, i=5):
        new_data = dict()
        outputs = np.array([np.array([0,0,0,0,0])])
        j = 0
        x_list = []
        for i, (id,data) in enumerate(train_data.items()):
            x = data['context'] + " " + data['query']
        #     x_list.extend(x)
        #     top_sents = retrieve_x(rag_model, x)

        # for i, (id,data) in enumerate(train_data.items()):
        #     top_sent = top_sents[i][0]['content']
            retrieved_sents = retrieve_x(rag_model, x, k=5)

            top_sents = [sent['content'] for sent in retrieved_sents]
            true_sent = kb_data[id[:-2]][0]

            if true_sent in top_sents:
                outputs = np.vstack((outputs, np.array([[1, i, id, true_sent, true_sent]])))
                # print(top_sent)
            else:
                outputs = np.vstack((outputs, np.array([[0, i, id, top_sents[0], true_sent]])))

            new_data[id] = copy.deepcopy(data)
            new_data[id]["context"] = data['context'] + " " + top_sents[0] + " " + top_sents[1] + " " + top_sents[2] + " " + top_sents[3] + " " + top_sents[4]
            j = j + 1
            # if j > 2:
                # break
        return outputs[1:], new_data
    # test_query = eval_contexts[0] + " " + eval_queries[0]

    # print(test_query)
    # print()
    # test = retrieve_1(RAG, test_query)
    # print()
    # print(test)
    # print()
    # print(test[0])
    # print()
    # print(test[0]["content"])

    output,new_data = evaluate_5(RAG, eval_kb_data, eval_kb_sents, eval_train_data)
    
    # pprint.pprint(output)
    # print()

    # print(output[1],"\n")
    # print(eval_train_data["bd.1.1"],"\n")
    # print(new_data["bd.1.1"])


    # print(RAG.model_name)
    # print(RAG.index_name)


    def write_jsonl(filename, data):
        sjsonl = r".jsonl"

        if not filename.endswith(sjsonl):
            filename = filename + sjsonl

        with open(filename, 'w') as out:
            for ddict in data.values():
                jout = json.dumps(ddict) + '\n'
                out.write(jout)
        return

    branch = r"/retrieved_data/binary"
    # filename = r"/untrained_retrieve"
    filename = r"/trained_retrieve_top5"

    # write_jsonl(root + branch + filename, new_data)




    print()
    results = output[:,0].astype(bool)
    accuracy = sum(results)/len(results)
    print("total accuracy: ", accuracy)



    filtered_output = np.array([np.array([0,0,0,0,0])])
    hell = np.array([])

    for i,x in enumerate(output):
        if x[2][:-2] in hell:
            # print('double')
            pass
        else:
            filtered_output = np.vstack((filtered_output, x))
            hell = np.append(hell, x[2][:-2])


    filtered_output = filtered_output[1:]

    results = filtered_output[:,0].astype(bool)
    accuracy = sum(results)/len(results)
    print("filtered_accuracy: ", accuracy)

    # print(new_data)
    # c30 = "If an individual frequently uses the internet, they may develop a dependency on it. On the contrary, if they spend more time outdoors, they tend to engage in physical activities. It is possible that solely (1) is true, or solely (2) is true, or even both are true simultaneously."
    # q30 = "'Can we say at least one of the following must always be true? (a) he becomes addicted to it and (b) he goes outdoors more often"

    # r30 = "It is established that at least one of the following statements is true: either Aryan spends a significant amount of time on the internet or he will not become more active."

    # k = 3 # How many documents you want to retrieve, defaults to 10, we set it to 3 here for readability
    # results = RAG.search(query=q30, k=k)
    # print(results)





if __name__ == '__main__':
    main()
