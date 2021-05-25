# Flink devcontainer

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
