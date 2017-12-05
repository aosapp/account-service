#!/bin/bash
set -a

. .env
. .env_private

set +a

if [ "${PUBLIC_IP}" = "LOCAL" ] && ([ -z "${PROXY_HOST}" ] || [ -z "${PROXY_PORT}" ] || [ "${PROXY_PORT}" = "proxy_port" ] || [ "${PROXY_HOST}" = "proxy_host" ]);then
 echo "Please set Proxy host & port"
 exit 1
elif [ "${PUBLIC_IP}" = "LOCAL" ] && [ -z "$(cat .env_private | grep -m 1 "http_proxy")" ];then
  printf "\nhttp_proxy=http://${PROXY_HOST}:${PROXY_PORT}\nhttps_proxy=http://${PROXY_HOST}:${PROXY_PORT}" >> .env_private
fi

if [ "${PUBLIC_IP}" = "LOCAL" ]; then
 distributed_os=`cat /etc/*-release | grep -w "ID" | awk -F"=" '{print $2}' | tr -d '"'`
 echo "distributed_os=$distributed_os"
 interface=`route | grep -w "default" | awk '{print $8}'`
 if [ "$distributed_os" == "centos" ]; then
  ip=`ifconfig | grep -A1 ${interface} | awk -F" " '/inet/ {print $2}'`
 elif [ "$distributed_os" == "ubuntu" ]; then
  ip=`ifconfig | grep -A1 ${interface} | awk -F":" '/inet addr/ {print $2}' | awk '{print $1}'`
 fi
elif [ "${PUBLIC_IP}" = "AMAZON" ]; then
 ip=`curl http://169.254.169.254/latest/meta-data/public-ipv4`
else
 ip=${PUBLIC_IP}
fi
command1="sed -i 's/HOST_IP_CALCULATED/$ip/g' .env_private"
eval $command1

set -a

. .env_private

set +a

workspace=`pwd`

docker_compose_path=$(echo "$workspace" | sed 's/\//\\\//g')
command2="sed -i 's/DOCKER_COMPOSE_PATH_CALCULATED/${docker_compose_path}\//g' .env_private"
eval $command2

# if we are in AMAZON we need to remove the proxy from the containers, so we add it to the .evn file
#if [ "$PUBLIC_IP" == "AMAZON" ] && [ -z "$(cat .env_private | grep -m 1 "http_proxy")" ];then
# printf "\nhttp_proxy=\nhttps_proxy=">> .env_private
#fi

# remove octane service if we dont want to use it
if [ "${CREATE_OCTANE}" != "YES" ];then
 sed -i '/octane/,$d' docker-compose.yml
fi

docker login -u=advantageonlineshoppingapp -p=W3lcome1
docker-compose pull
docker-compose up -d

if [ "${CREATE_OCTANE}" == "YES" ];then
 . configure_octane.sh
fi
