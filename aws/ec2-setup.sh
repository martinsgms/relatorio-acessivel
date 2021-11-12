#!/bin/bash

# (manualmente) alterar senha do root
# sudo passwd root

# instala kubernetes
curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.13.0/bin/linux/amd64/kubectl

# dá permissão de execução kubernetes e move pro path
chmod +x kubectl && sudo mv kubectl /usr/local/bin/

# atualiza pacotes
sudo apt-get update

# instala docker. Importante ser a versão abaixo
sudo apt-get install docker.io=17.12.1-0ubuntu1

# instala minikube
curl -Lo minikube https://storage.googleapis.com/minikube/releases/v0.32.0/minikube-linux-amd64 && chmod +x minikube && sudo cp minikube /usr/local/bin && rm minikube

# (manualmente) ativa minikube
# sudo -su root
# minikube start --vm-driver=none
# minikube status