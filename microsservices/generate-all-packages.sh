#!/bin/bash

echo && echo "packing config server..." && echo
cd ./config-server/ && mvn clean package -DskipTests

echo && echo "packing eureka server..." && echo
cd ../eureka-server/ && mvn clean package -DskipTests

echo && echo "packing exame app..." && echo
cd ../exame/ && mvn clean package -DskipTests

echo && echo "packing eureka server..." && echo
cd ../gateway-server/ && mvn clean package -DskipTests

echo && echo "packing eureka server..." && echo
cd ../usuario/ && mvn clean package -DskipTests

echo && echo "artifacts:" && echo
cd ../ && find -wholename "*target/*jar"
