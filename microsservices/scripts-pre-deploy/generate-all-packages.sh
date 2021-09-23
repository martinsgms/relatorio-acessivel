#!/bin/bash

printf "\npacking config server...\n"
cd ../config-server/ && mvn clean package -DskipTests

printf "\npacking eureka server...\n"
cd ../eureka-server/ && mvn clean package -DskipTests

printf "\npacking exame app...\n"
cd ../exame/ && mvn clean package -DskipTests

printf "\npacking eureka server...\n"
cd ../gateway-server/ && mvn clean package -DskipTests

printf "\npacking eureka server...\n"
cd ../usuario/ && mvn clean package -DskipTests

printf "\nartifacts:\n"
cd ../ && find -wholename "*target/*jar"
