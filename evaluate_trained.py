#!/usr/bin/env python3
""" Evaluation script for autoformalization models of the JAM package. 

"""
import os
import logging
import dataclasses
import requests
import dotenv

import hydra
import omegaconf
import tqdm
import torch
import mlflow
import mlflow.tracing
import nltk

import jam

dotenv.load_dotenv()

def run_evaluation_loop(model, dataset_config, batch_size):
    """Run the evaluation loop for a model with the given dataset configuration. 

    Parameters
    * model: 
    * dataset_config: 
    * batch_size:
    Returns
    * metrics: 
    """
    # load dataset
    # print("test")
    # print(dataset_config)
    # print(os.getenv("PROJECT_ROOT"))
    # print("ok")
    test_dataset = hydra.utils.instantiate(dataset_config)
    # print("test")

    test_dataloader = torch.utils.data.DataLoader(test_dataset, collate_fn=test_dataset.collate_eval, batch_size = batch_size)
    # TODO adapt dataset to mlflow interface and log it with log_input
    # dataset_mlflow = mlflow.data.Dataset(name=dataset_file, source=dataset)
    # mlflow.log_input(dataset_mlflow, context="testing")
    # load prompt based on dataset prompt directory
    model.load_prompt(test_dataset.prompt_dir)



    metrics = jam.utils.MetricCollection()
    results = []
    for batch in tqdm.tqdm(test_dataloader):
        
        predictions, steps = model.predict(batch["context"], batch["query"])

        metrics.update(targets=batch["target"],predictions=predictions, status_steps=[s["status"] for s in steps])

        # aggregate results
        for i,prediction in enumerate(predictions):
            result = {"example_id": batch["example_id"][i], "context": batch["context"][i], "query": batch["query"][i], "target":batch["target"][i].item(), "prediction": prediction.item(),}
            # save intermediate results
            result = result | { f"step_{k}": v for k,v in steps[i].items()}
            results.append(result)



    metrics = metrics.compute()

    logging.info(metrics)
    mlflow.log_metrics(metrics)

    jam.utils.write_results(results, dataset_filename=os.path.splitext(os.path.basename(dataset_config.file_name)),)
    return metrics

@dataclasses.dataclass
class Config:
    """
    Configuration for evaluation of JAM models
    """
    datasets: list[jam.datasets.DatasetConfig] = dataclasses.MISSING
    model: jam.pipeline.PipelineConfig = dataclasses.MISSING
    batch_size: int = 16
    result_dir: str = "./outputs"
    verbose: bool = False
    experiment_name: str = "JAM"
    experiment_description: str = "Experiment on autoformalization with language models."
    release_version: str = "0.1"
    experiment_tracking_uri: str =  "file:///${result_dir}/mlruns"

# @hydra.main(version_base=None, config_path="configs", config_name="eval")
@hydra.main(version_base=None, config_path="configs", config_name="trained")
def main(config: Config) -> None:
    """Evaluate a tool-augmented language model. 

    Parameters
    * config: Configuration 
    """
    # print(config.result_dir)
    if config.verbose :
        logging.basicConfig(format="%(levelname)s: %(message)s", level=logging.DEBUG)
        logging.info("Verbose output.")
        # mlflow.tracing.enable()
    else :
        logging.basicConfig(format="%(levelname)s: %(message)s")
        # mlflow.tracing.disable()

    # set up MLflow tracking
    mlflow.config.enable_async_logging()
    mlflow.enable_system_metrics_logging()
    mlflow.set_tracking_uri(config.experiment_tracking_uri)
    if not config.experiment_tracking_uri.startswith("file://"):
        response_version = requests.get(config.experiment_tracking_uri+"/version", auth=(os.getenv("MLFLOW_TRACKING_USERNAME"),os.getenv("MLFLOW_TRACKING_PASSWORD") ), timeout=10)
        assert response_version.text == mlflow.__version__  # Checking for a strict version match between server and local MLflow
    mlflow.set_experiment(config.experiment_name,)
    experiment_tags = {"mlflow.note.content": config.experiment_description, }
    mlflow.set_experiment_tags(tags=experiment_tags)

    with mlflow.start_run() as run:
        # set MLflow run name based on hyper parameter
        try:
            run_name = f'{hydra.core.hydra_config.HydraConfig.get().runtime.choices["model"]}/{hydra.core.hydra_config.HydraConfig.get().runtime.choices["model/lm"]} {hydra.core.hydra_config.HydraConfig.get().runtime.choices["datasets"]} ({run.info.run_name})'
        except omegaconf.errors.ConfigKeyError as _:
            run_name = run.info.run_name
        mlflow.set_tags(tags={"mlflow.runName":run_name, "release.version": config.release_version, }, synchronous=False)

        # upload config files from hydra
        # mlflow.log_artifact(hydra.core.hydra_config.HydraConfig.get().runtime.output_dir + "/.hydra/config.yaml")
        model = hydra.utils.instantiate(config.model, _convert_="all")
        # TODO register model with mlflow

        mlflow.log_params(jam.utils.flatten_dictionary(config))# mlflow cannot write nested dictionary

        if len(config.datasets) <= 1:
            run_evaluation_loop(model, config.datasets[0], config.batch_size)
        else:
            metrics = []
            for dataset_config in config.datasets:
                with mlflow.start_run(nested=True) as child_run:
                    try:
                        run_name = f'{hydra.core.hydra_config.HydraConfig.get().runtime.choices["model"]}/{hydra.core.hydra_config.HydraConfig.get().runtime.choices["model/lm"]} {os.path.splitext(os.path.basename(dataset_config.file_name))[0]} ({child_run.info.run_name})'
                    except omegaconf.errors.ConfigKeyError as _:
                        run_name = child_run.info.run_name
                    mlflow.set_tags(tags={"mlflow.runName":run_name, "release.version": config.release_version, }, synchronous=False)
                    single_config = omegaconf.OmegaConf.to_container(config)
                    single_config["datasets"]= [ omegaconf.OmegaConf.to_container(dataset_config), ]
                    mlflow.log_params(jam.utils.flatten_dictionary(single_config))# mlflow cannot write nested dictionary
                    metrics.append(run_evaluation_loop(model, dataset_config, config.batch_size))
            # aggregate metrics in nested run
            mlflow.log_metrics(metrics[-1])# just log the last ones

        # upload .log from hydra to mlflow server
        # mlflow.log_artifact(hydra.core.hydra_config.HydraConfig.get().job_logging.handlers.file.filename)

if __name__ == "__main__":
    nltk.download('punkt_tab')

    cs = hydra.core.config_store.ConfigStore.instance()
    cs.store(name="base_config", node=Config)

    jam.utils.register_configs()
    #pylint: disable = no-value-for-parameter
    main()
