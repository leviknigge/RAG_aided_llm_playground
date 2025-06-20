from ragatouille import RAGPretrainedModel
from ragatouille import RAGTrainer

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
# from sentence_transformers import SentenceTransformer
# If sentence_transformer missing -> activate thesis-env 




def main():
    random.seed(1)



    # RAG = RAGPretrainedModel.from_pretrained("colbert-ir/colbertv2.0")

    # print("model loaded")


    ################################
    # Load training Data

    train_kb_data = {}
    train_kb_sents = []
    train_data = {}

    root = r"/home/lknigge/augmented_llm_playground_fabian/data/LogicBench"
    train_kb_path = r"/abducted_data/binary/fol_aug_kb.jsonl"
    train_data_path = r"/abducted_data/binary/fol_aug.jsonl"

    with open(root + train_kb_path, "r") as f:
        for line in f:
            datapoint = json.loads(line)
            # print(datapoint.items()/?)
            id, sent = list(datapoint.items())[0]
            # print(sent)
            # break

            if not sent == []:
                train_kb_data[id] = sent
                train_kb_sents.extend(sent)
            if sent == []:
                train_kb_sents.extend([""])
                train_kb_data[id] = [""]


    with open(root + train_data_path, "r") as f:
        for line in f:
            datapoint = json.loads(line)
            train_data[datapoint["id"]] = datapoint

            # print(datapoint['query'])
            # queries.extend([datapoint["query"]])    
            # contexts.extend([datapoint["context"]])    

    train_ids = list(train_data.keys())

    # print(kb_sents[0])
    # print(train_data[ids[0]])


    queries = []
    unique_ids = []

    for i in train_ids:

        if i[:-2] in unique_ids:
            pass
        else:
            query = train_data[i]["context"] + " " +  train_data[i]["query"]
            queries.extend([query])
            unique_ids.extend([i[:-2]])


    # print(queries[:2])
        
    # print(len(queries))
    # print(len(kb_sents))

    # kb_sents[0]
    # print(kb_sents[0])

    def choice_excluding(lst, exception):
        possible_choices = [v for v in lst if v != exception]
        return random.choice(possible_choices)

    # print(choice_excluding(kb_sents, kb_sents[0]))


    train_data_out_path = root + r"/abducted_data/binary/rag_train_test/"

    my_full_corpus = train_kb_sents
    queries = queries

    triplets = []

    for i in range(len(queries)):
        negatives = [choice_excluding(train_kb_sents, train_kb_sents[i]) for _ in range(10)]
        triplets.append((queries[i],[train_kb_sents[i]],negatives))

    # print(triplets[0][2])
    # print(len(triplets))

    ################################

    trainer = RAGTrainer(model_name="RAGColBERT", pretrained_model_name="colbert-ir/colbertv2.0", language_code="en")


    trainer.prepare_training_data(raw_data=triplets, data_out_path=train_data_out_path, all_documents=my_full_corpus, num_new_negatives=0, mine_hard_negatives=False)



    trained_model_path = trainer.train(batch_size=32,
                nbits=4, # How many bits will the trained model use when compressing indexes
                # maxsteps=/10000, # Maximum steps hard stop
                maxsteps=500000, # Maximum steps hard stop
                use_ib_negatives=True, # Use in-batch negative to calculate loss
                dim=128, # How many dimensions per embedding. 128 is the default and works well.
                learning_rate=5e-6, # Learning rate, small values ([3e-6,3e-5] work best if the base model is BERT-like, 5e-6 is often the sweet spot)
                doc_maxlen=128, # Maximum document length. Because of how ColBERT works, smaller chunks (128-256) work very well.
                use_relu=False, # Disable ReLU -- doesn't improve performance
                warmup_steps="auto", # Defaults to 10%
                )
    print('loop')


    print(trainer)

 


        # ################################
    # Evaluation data
    eval_kb_data = {}
    eval_kb_sents = []

    root = r"/home/lknigge/augmented_llm_playground_fabian/data/LogicBench"
    eval_kb_path = r"/abducted_data/binary/fol_eval_kb.jsonl"

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

    print(eval_kb_sents[0])
    # ################################

    # RAG = RAGPretrainedModel.from_pretrained("colbert-ir/colbertv2.0")
    # RAG = RAGPretrainedModel.from_pretrained("colbert-ir/colbertv2.0")  RAGColBERT
    print()
    print(trained_model_path)
    RAG = RAGPretrainedModel.from_pretrained(trained_model_path)
    print(RAG)


    ## CPU ONLY
    # RAG.index(
    #     collection=eval_kb_sents, 
    #     index_name="test", 
    #     split_documents=False
    #     )

    # path_to_index = ".ragatouille/colbert/indexes/test/"
    # RAG2 = RAGPretrainedModel.from_index(path_to_index)
    print("index loaded")

    c30 = "If an individual frequently uses the internet, they may develop a dependency on it. On the contrary, if they spend more time outdoors, they tend to engage in physical activities. It is possible that solely (1) is true, or solely (2) is true, or even both are true simultaneously."
    q30 = "'Can we say at least one of the following must always be true? (a) he becomes addicted to it and (b) he goes outdoors more often"

    r30 = "It is established that at least one of the following statements is true: either Aryan spends a significant amount of time on the internet or he will not become more active."

    k = 3 # How many documents you want to retrieve, defaults to 10, we set it to 3 here for readability
    # results = RAG2.search(query=q30, k=k)
    # print(results)

if __name__ == '__main__':
    main()
