#!/usr/bin/env python3
"""
Util functions for jam
"""

import collections
import collections.abc
import json
import copy

import hydra
import jam.langgraph_rag
import mlflow
import torch
import torcheval
import torcheval.metrics
import torcheval.metrics.functional.classification
import torcheval.utils

import jam


def convert_json_prompt(prompt: str) -> dict:
    """
    Convert the prompt with (partial) json syntax into a dictionary  
    """
    # add to list
    prompt = f"[{prompt}]"
    # escape newlines
    prompt = prompt.replace("\n","\\n")
    prompt = prompt.replace("\r","\\r")
    prompt = prompt.replace("\t","\\t")
    prompt = prompt.replace("\f","\\f")
    prompt = prompt.replace("\v","\\v")

    # convert
    chat_prompt = json.loads(prompt)
    # merge messages with same role following each other together
    merged_prompt = []
    while chat_prompt:
        last_chat =chat_prompt.pop(0)
        if merged_prompt and last_chat["role"] == merged_prompt[-1]["role"]:
            merged_prompt[-1]["content"] += last_chat["content"]
        else:
            merged_prompt.append(last_chat)
    return merged_prompt


def flatten_dictionary(dictionary: dict, seperator: str='/') -> dict:
    """
    Turn a nested dictionary into a flattened dictionary. 
    :param dictionary: 
    :param seperator: 
    :return: flattend dictionary  
    """

    item_stack = collections.deque(dictionary.items())
    result_dict = {}
    while len(item_stack) > 0:
        item = item_stack.pop()
        if isinstance(item[1],collections.abc.MutableMapping):
            # nested dict: add all key, item pairs from nested dict
            for i in item[1].items():
                item_stack.append((item[0]+seperator+i[0], i[1]))
        elif isinstance(item[1], collections.abc.Iterable) and any(isinstance(x, collections.abc.MutableMapping) for x in item[1]):
            # nested list: add all items with index
            for i,v in enumerate(item[1]):
                item_stack.append((item[0]+seperator+str(i), v))
        else:
            assert item[0] not in result_dict
            result_dict[item[0]] = item[1]
    return result_dict

def register_configs() -> None:
    """Register the Hydra configruations defined in the library. (Required if they are used)
    """
    cs = hydra.core.config_store.ConfigStore.instance()

    # LM configurations
    cs.store(group="jam/lm", name="Transformers", node=jam.language_models.TransformersWrapperConfig)
    cs.store(group="jam/lm", name="open_ai", node=jam.language_models.GPTConfig)

    # Solver configurations
    cs.store(group="jam/solver", name="SolverPipeline", node=jam.symbolic_solvers.SolverPipelineConfig)
    cs.store(group="jam/solver/fol", name="TPTPParser", node=jam.symbolic_solvers.fol.tools.TPTPParserConfig)
    cs.store(group="jam/solver/fol", name="VampireReasoner", node=jam.symbolic_solvers.fol.tools.VampireReasonerConfig)
    cs.store(group="jam/solver/fol", name="FOLLinter", node=jam.symbolic_solvers.fol.tools.FOLLinterConfig)
    cs.store(group="jam/solver/fol", name="ConsistentTest", node=jam.symbolic_solvers.fol.tools.ConsistentTestConfig)
    cs.store(group="jam/solver/fol", name="FOLContextLinter", node=jam.symbolic_solvers.fol.FOLContextLinterConfig)
    cs.store(group="jam/solver/tptp", name="NoneParser", node=jam.symbolic_solvers.tptp.tools.NoneParserConfig)

    # Adapted Logic-LM implementations using Prover9
    cs.store(group="jam/solver/logic_lm/fol", name="Prover9Parser", node=jam.symbolic_solvers.logic_lm.fol.Prover9ParserConfig)
    cs.store(group="jam/solver/logic_lm/fol", name="Prover9Reasoner", node=jam.symbolic_solvers.logic_lm.fol.Prover9ReasonerConfig)

    # Pipeline configruations
    cs.store(group="jam", name="LangGraphWrapper", node=jam.langgraph.LangGraphWrapperConfig)
    cs.store(group="jam", name="LangGraphRagWrapper", node=jam.langgraph_rag.LangGraphWrapperRagConfig)
    cs.store(group="jam", name="LMPipeline", node=jam.baseline.LMPipelineConfig)
    cs.store(group="jam", name="RandomPipeline", node=jam.baseline.RandomPipelineConfig)


def write_results(results: list[dict], dataset_filename: str):
    """Writes the results to files (json + jsonl format) and uploads to MLflow server.

    Parameters
    * results: 
    * dataset_filename: name of the 
    """
    # convert None values to empty strings
    results = [{k: v if v is not None else "" for k,v in result.items()} for result in results]

    result_filepath = hydra.core.hydra_config.HydraConfig.get().runtime.output_dir + "/" + dataset_filename[0] + ".jsonl"
    print(result_filepath)
    with open(result_filepath, "w", encoding="utf-8") as outfile:
        for result in results:
            #avoid new lines in individual dumps with indent=None
            result_json = json.dumps(result, indent=None)
            outfile.write(f"{result_json}\n")
    # mlflow.log_artifact(result_filepath)

    # convert results to table format and log to MLFlow
    result_keys = {k for row in results for k in [*row]}
    result_table = {key:[row[key] if key in row else None for row in results] for key in result_keys}
    with open(hydra.core.hydra_config.HydraConfig.get().runtime.output_dir + "/" + dataset_filename[0] + ".json", "w", encoding="utf-8") as outfile:
        json.dump(result_table,outfile, indent=4)
    # mlflow.log_table(result_table, artifact_file=dataset_filename[0] + ".json")


class MetricCollection():
    """Collection of metrics for QA evaluation
    """
    def __init__(self):
        # initialize metrics
        # classification metrics
        self.classification_metrics = {"accuracy" : torcheval.metrics.BinaryAccuracy(), "precision" : torcheval.metrics.BinaryPrecision(), "recall": torcheval.metrics.BinaryRecall(), "F1score": torcheval.metrics.BinaryF1Score()}
        self.valid_classification_metrics = copy.deepcopy(self.classification_metrics)

        # aggregation metrics
        # status metrics
        self.status_count_metrics = {status.name : torcheval.metrics.Mean() for status in jam.symbolic_solvers.JAMStatus}

    def update(self, targets, predictions ,status_steps):
        """Update all metrics.
        """
        # generate metrics with prediction result
        for metric in self.classification_metrics.values():
            metric.update(targets, predictions)

        # create mask
        ok_mask = torch.tensor([x in (jam.symbolic_solvers.JAMStatus.OK.name, jam.symbolic_solvers.JAMStatus.WARNING.name) for x in status_steps])
        valid_predictions = torch.masked_select(predictions, ok_mask)
        valid_targets = torch.masked_select(targets, ok_mask)
        for metric in self.valid_classification_metrics.values():
            metric.update(valid_targets, valid_predictions)

        for status_type, status_sum in self.status_count_metrics.items():
            mask = torch.tensor([x == status_type for x in status_steps])
            status_sum.update(mask)

    def compute(self,):
        """Compute the agregated metrics.
        """
        return { k:v.compute().item() for k,v in self.classification_metrics.items()} | { f"{k} valid":v.compute().item() for k,v in self.valid_classification_metrics.items()} | { f"Status {k}":v.compute().item() for k,v in self.status_count_metrics.items()}
