#!/bin/bash
. .env
. .env_private

if [ "${DEPLOYMENT_LOCATION}" = "LOCAL" ] && ([ -z "${PROXY_HOST}" ] || [ -z "${PROXY_PORT}" ] || [ "${PROXY_PORT}" = "proxy_port" ] || [ "${PROXY_HOST}" = "proxy_host" ]);then
 echo "Please set Proxy host & port"
 exit 1
elif [ "${DEPLOYMENT_LOCATION}" = "LOCAL" ] && [ -z "$(cat .env_private | grep -m 1 "http_proxy")" ];then
  printf "\nhttp_proxy=http://${PROXY_HOST}:${PROXY_PORT}\nhttps_proxy=http://${PROXY_HOST}:${PROXY_PORT}" >> .env_private
fi

# change NODE_1 and NODE_2 values
ind=1
 docker node ls | grep -v Leader | grep -v HOSTNAME | awk '{print $2}' | while read line; do command="sed -i 's/NODE_$ind/$line/g' docker-compose.yml"; eval $command; ((ind++));  done
# change public ip
ind=1
 docker node ls | grep -v Leader | grep -v HOSTNAME | awk '{print $2}' | xargs docker inspect $1 | grep "Addr" | awk '{ gsub("\"",""); print $2}' | while read line; do command="sed -i 's/CALCULATED_IP_NODE_$ind/$line/g' .env_private"; eval $command; ((ind++)); done
# host name
 command1="sed -i 's/MASTER_NODE/$(docker node ls | grep -w Leader | awk '{print $3}')/' docker-compose.yml"
 eval $command1

# if we are in AMAZON we need to remove the proxy from the containers, so we add it to the .evn file
#if [ "DEPLOYMENT_LOCATION" == "AMAZON" ] && [ -z "$(cat .env_private | grep -m 1 "http_proxy")" ];then
# printf "\nhttp_proxy=\nhttps_proxy=">> .env_private
#fi

docker login -u=advantageonlineshoppingapp -p=W3lcome1
docker-compose config > docker-compose-processed.yml
docker stack deploy -prune --with-registry-auth --resolve-image=always -c docker-compose-processed.yml STACK
