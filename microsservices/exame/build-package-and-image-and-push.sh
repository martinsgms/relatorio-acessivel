#!/bin/bash

echo && echo "building package ..." && echo
mvn clean package -DskipTests

echo && echo "removing image ..." && echo
docker rmi $(docker images -q martinsgms/ra-app-exame)

echo && echo "building image ..." && echo
docker build -t martinsgms/ra-app-exame:v0.1 .

echo && echo "pushing image ..." && echo
docker push martinsgms/ra-app-exame:v0.1

echo && echo "done!" && echo