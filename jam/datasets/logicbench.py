"""
LogicBench dataset
"""

import dataclasses
import json

import torch

from . import dataset

@dataclasses.dataclass
class LogicBenchConfig(dataset.DatasetConfig): # This default setting is not used at the moment
    _target_: str = "jam.datasets.LogicBench"
    prompt_dir: str = "../prompts/logicbench"

class LogicBench(dataset.BaseDataset):
    """
    LogicBench dataset 
    reasoning with different reasoning patterns 
    """
    def __init__(self, file_name :str, prompt_dir : str) -> None:
        self.prompt_dir = prompt_dir
        with open(file_name, 'rb') as file:
            raw_data = [json.loads(x) for x in list(file)]

        self.data = [(v["id"], v["context"], v["query"], torch.Tensor([v["label"],]).bool(), v["formal context"], v["formal query"]) for v in raw_data]

    def __len__(self) -> int:
        return len(self.data)

    def __getitem__(self, idx) -> tuple[str,str,int]:
        return self.data[idx]

    @classmethod
    def collate_eval(cls, data) -> list[tuple[str,int]]:
        """
        stack batch together, currently only list of examples works
        """
        example_ids = [sample[0] for sample in data]
        contexts = [sample[1] for sample in data]
        queries = [sample[2] for sample in data]
        targets = torch.flatten(torch.stack([sample[3] for sample in data]))

        return {"example_id": example_ids, "context": contexts, "query": queries, "target": targets}
    
    @classmethod
    def collate_analyze(cls, data) -> list[tuple[str,int]]:
        """
        stack batch together, currently only list of examples works
        """
        example_ids = [sample[0] for sample in data]
        contexts = [sample[1] for sample in data]
        queries = [sample[2] for sample in data]
        targets = torch.flatten(torch.stack([sample[3] for sample in data]))
        formal_context = [sample[4] for sample in data]
        formal_query = [sample[5] for sample in data]

        return {"example_id": example_ids, "context": contexts, "query": queries, "target": targets, "formal context":formal_context, "formal query":formal_query}
