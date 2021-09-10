#!/bin/bash

echo && echo "building package ..." && echo
mvn clean package -DskipTests

echo && echo "removing image ..." && echo
docker rmi $(docker images -q martinsgms/ra-app-usuario)

echo && echo "building image ..." && echo
docker build -t martinsgms/ra-app-usuario:v0.1 .

echo && echo "pushing image ..." && echo
docker push martinsgms/ra-app-usuario:v0.1

echo && echo "done!" && echo