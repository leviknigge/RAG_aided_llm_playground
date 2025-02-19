# JAMs: Just Autoformalization Models

Every good LLM sandwich needs JAM! Interacting with external systems requires formalisation of the given informal LLM input. The goal of the Just Autoformalization Models framework is to explore methods for LLMs to generate this formal representation as well as find the best ways to provide iterative feedback for the formalisation. 

This repository is a playground to explore different kinds of autoformalization methods with a particular focus on exploring the creation, improvment and reuse of formalization as part of these autoformalization architectures. 

## Basic structure
The framework relies on Hydra for configuration managment and MLFlow for experiment tracking. 
* jam: Acutal framework
* configs: YAML configuration files of the framework
* tests: All tests for JAM components
* data: 
* outputs:

slurm files
* ray.slurm: script to start a Ray cluster for Hydra-multiruns
* run.slurm: running single evaluation

experiments:
* evaluate.py: Running evaluation on a set of datasets.
* summarize.py: Summarize previous evaluation results from MLflow tracking server

## Setup 
.env file is required



## Configuration managment
Hydra


## Experiment tracking
MLFlow

### useful commands 
start the mlflow ui on a local version.
```
 mlflow ui --backend-store-uri  ./data/mlruns
```

For copying results between servers
```
find -iname "*.yaml" -print0 | xargs -0  sed -i 's/\/home\/fhoppe\/Workspace\/augmented_llm_playground\/data\/results\/prontoqa_certified\/test_mlflow/\/home\/fhoppe\/Downloads\/test_mlflow/g'
```

## Experiments

Needs loaded .env file 

```
python -u evaluate.py
```
