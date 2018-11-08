#!/bin/bash
. .env
. .env_private

if [ "${DEPLOYMENT_LOCATION}" = "LOCAL" ] && ([ -z "${PROXY_HOST}" ] || [ -z "${PROXY_PORT}" ] || [ "${PROXY_PORT}" = "proxy_port" ] || [ "${PROXY_HOST}" = "proxy_host" ]);then
 echo "Please set Proxy host & port"
 exit 1
elif [ "${DEPLOYMENT_LOCATION}" = "LOCAL" ] && [ -z "$(cat .env_private | grep -m 1 "http_proxy")" ];then
  printf "\nhttp_proxy=http://${PROXY_HOST}:${PROXY_PORT}\nhttps_proxy=http://${PROXY_HOST}:${PROXY_PORT}" >> .env_private
fi

sed -i "s/POSTGRES_PORT/${POSTGRES_PORT}/g" docker-compose.yml
sed -i "s/MAIN_PORT/${MAIN_PORT}/g" docker-compose.yml
sed -i "s/ACCOUNT_PORT/${ACCOUNT_PORT}/g" docker-compose.yml
sed -i "s/TAG/${TAG}/g" docker-compose.yml

# change host name
 docker node ls | grep -v Leader | grep -v HOSTNAME | awk '{print $2}' | while read line; do command="sed -i '0,/HOST_NAME/{s/HOST_NAME/$line/}' docker-compose.yml"; eval $command; done
# change public ip
 docker node ls | grep -v Leader | grep -v HOSTNAME | awk '{print $2}' | xargs docker inspect $1 | grep "Addr" | awk '{ gsub("\"",""); print $2}' | while read line; do command="sed -i '0,/PUBLIC_IP_CALCULATED/{s/PUBLIC_IP_CALCULATED/$line/}' .env_private"; eval $command; done
# host name
 command1="sed -i 's/HOST_NAME/$(docker node ls | grep -w Leader | awk '{print $3}')/' docker-compose.yml"
 eval $command1
# ip of the host
 command2="sed -i 's/PUBLIC_IP_CALCULATED/$(docker node ls | grep -w Leader | docker inspect $(awk '{print $3}') | grep -m2 "Addr" | tail -n1 | awk '{ gsub("\"",""); print $2}' | awk -F":" '{print $1}')/' .env_private"
 eval $command2

# if we are in AMAZON we need to remove the proxy from the containers, so we add it to the .evn file
#if [ "DEPLOYMENT_LOCATION" == "AMAZON" ] && [ -z "$(cat .env_private | grep -m 1 "http_proxy")" ];then
# printf "\nhttp_proxy=\nhttps_proxy=">> .env_private
#fi

docker login -u=advantageonlineshoppingapp -p=W3lcome1
docker stack deploy --with-registry-auth -c docker-compose.yml STACK