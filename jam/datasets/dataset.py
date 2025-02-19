#!/usr/bin/env python3
"""
Dataset module
"""

import dataclasses

import torch

@dataclasses.dataclass
class DatasetConfig():
    file_name: str = dataclasses.MISSING
    prompt_dir: str = dataclasses.MISSING
    _target_: str = "jam.datasets.BaseDataset"

class BaseDataset(torch.utils.data.Dataset):
    pass
