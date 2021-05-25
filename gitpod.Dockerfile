# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.177.0/containers/java-8/.devcontainer/base.Dockerfile

FROM gitpod/workspace-full

# install flink
USER gitpod
RUN wget -qO- https://mirror.netcologne.de/apache.org/flink/flink-1.13.0/flink-1.13.0-bin-scala_2.11.tgz | tar -xzf -
ENV PATH "$PATH:/flink-1.13.0/bin"
