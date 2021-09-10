#!/bin/bash

echo && echo "building package ..." && echo
mvn clean package -DskipTests

echo && echo "removing image ..." && echo
docker rmi $(docker images -q martinsgms/ra-gateway-server)

echo && echo "building image ..." && echo
docker build -t martinsgms/ra-gateway-server:v0.1 .

echo && echo "pushing image ..." && echo
docker push martinsgms/ra-gateway-server:v0.1

echo && echo "done!" && echo