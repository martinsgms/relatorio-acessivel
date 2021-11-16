#!/bin/bash

start_time="$(date -u +%s)"

printf "\nremovendo imagens...\n"
docker rmi $(docker images -q martinsgms/ra-app-usuario)
docker rmi $(docker images -q martinsgms/ra-app-exame)
docker rmi $(docker images -q martinsgms/ra-app-servicosaude)
docker rmi $(docker images -q martinsgms/ra-gateway-server)
docker rmi $(docker images -q martinsgms/ra-config-server)
docker rmi $(docker images -q martinsgms/ra-eureka-server)

printf "\npacking...\n"
#./generate-all-packages.sh

printf "\nbuilding imagens...\n"
docker-compose build --no-cache

printf "\nuploading to DockerHub...\n"
docker-compose push

end_time="$(date -u +%s)"

elapsed="$(($end_time-$start_time))"
printf "elapsed %f min" $(($elapsed/60))

printf "\ndone!\n"