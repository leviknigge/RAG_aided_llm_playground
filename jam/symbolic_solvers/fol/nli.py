#!/usr/bin/env python3
"""
Natural Language Inference Model 
"""
import logging
import os

import torch
import transformers


class NLI():
    """
    Natural language inference model
    based on https://huggingface.co/facebook/bart-large-mnli
    """

    def __init__(self, device: str, api_key_env: str, ) -> None:
        access_token = os.getenv(api_key_env)
        if not device:
            device = "cuda" if torch.cuda.is_available() else "cpu"
        if device != "cuda":
            logging.warning("Running on CPU")
        self.device = device

        self.tokenizer = transformers.AutoTokenizer.from_pretrained("facebook/bart-large-mnli", token=access_token)
        self.model = transformers.AutoModelForSequenceClassification.from_pretrained("facebook/bart-large-mnli",token=access_token)

    def predict(self,premise, hypothesis) -> bool:
        """
        Predict if the premise entails the hypothesis. 
        """

        x = self.tokenizer.encode(premise, hypothesis, return_tensors="pt")
        logits = self.model(x.to(self.device))[0]

        # throw away "neutral" (dim 1) and take the probability of "entailment" (2) as the probability of the label being true
        entail_contradiction_logits = logits[:,[0,2]]
        probs = entail_contradiction_logits.softmax(dim=1)
        prob_label_is_true = probs[:,1]
        return prob_label_is_true.item()
        # return prob_label_is_true > 0.7
