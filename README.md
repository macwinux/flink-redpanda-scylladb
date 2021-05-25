# Flink devcontainer

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/lukashass/flink-devcontainer)

## Usage

This template repository can be used two ways:

1. Using Gitpod (just click the open in Gitpod badge above)
1. Using a VSCode devcontainer

### Requirements for VSCode devcontainer

- Docker
- VSCode
- <https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers>

## Build

```bash
mvn clean package
```

## Run

Start flink cluster with `start-cluster.sh`.

Run app:

```bash
flink run target/wordcount-0.1.jar --input <FILENAME>
```
