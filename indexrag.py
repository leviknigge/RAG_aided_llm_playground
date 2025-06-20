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

    # model_path = r".ragatouille/colbert/none/2025-05/01/15.40.43/checkpoints/colbert"

    # model_path = r".ragatouille/colbert/none/2025-05/04/13.28.15/checkpoints/colbert"

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
        # collection=eval_kb_sents, 
        # index_name="untrained", 
        # split_documents=False
        # )
    ####################################





    # path_to_index = ".ragatouille/colbert/indexes/untrained/"
    # RAG2 = RAGPretrainedModel.from_index(path_to_index)
    # RAG = RAGPretrainedModel.from_pretrained("colbert-ir/colbertv2.0")
    ####################################
    # print("index loaded")
    
    
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
            if j > 2:
                break
        return outputs[1:], new_data



    output,new_data = evaluate(RAG, eval_kb_data, eval_kb_sents, eval_train_data)
    
    pprint.pprint(output)
    print()




if __name__ == '__main__':
    main()
