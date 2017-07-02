#!/bin/bash
. .env
if [ "${PUBLIC_IP}" = "LOCAL" ]; then
 interface=`route | grep -w "default" | awk '{print $8}'`
 ip=`ifconfig | grep -A1 ${interface} | awk -F":" '/inet addr/ {print $2}' | awk '{print $1}'`
elif [ "${PUBLIC_IP}" = "AMAZON" ]; then
 ip=`curl http://169.254.169.254/latest/meta-data/public-ipv4`
else
 ip=${PUBLIC_IP}
fi
command1="sed -i 's/HOST_IP_CALCULATED/$ip/g' .env"
eval $command1
docker login -u=advantageonlineshoppingapp -p=W3lcome1
docker-compose up -d

