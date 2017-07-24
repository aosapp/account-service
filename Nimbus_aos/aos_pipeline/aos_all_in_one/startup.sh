#!/bin/bash
. .env
. .env_private

if [ "${PUBLIC_IP}" = "LOCAL" ]; then
 interface=`route | grep -w "default" | awk '{print $8}'`
 ip=`ifconfig | grep -A1 ${interface} | awk -F":" '/inet addr/ {print $2}' | awk '{print $1}'`
elif [ "${PUBLIC_IP}" = "AMAZON" ]; then
 ip=`curl http://169.254.169.254/latest/meta-data/public-ipv4`
else
 ip=${PUBLIC_IP}
fi
command1="sed -i 's/HOST_IP_CALCULATED/$ip/g' .env_private"
eval $command1
. .env_private
workspace=`pwd`

docker_compose_path=$(echo "$workspace" | sed 's/\//\\\//g')
command2="sed -i 's/DOCKER_COMPOSE_PATH_CALCULATED/${docker_compose_path}\//g' .env_private"
eval $command2

one_level_up_workspace="${workspace%/*}"
two_levels_up_workspace="${one_level_up_workspace%/*}"
three_levels_up_workspace="${two_levels_up_workspace%/*}"
workspace=${three_levels_up_workspace}
three_levels_up_workspace=$(echo "$three_levels_up_workspace" | sed 's/\//\\\//g')
command2="sed -i 's/WORKSPACE_ACCOUNT/${three_levels_up_workspace}\/accountservice/g' docker-compose.yml"
eval $command2
command2="sed -i 's/WORKSPACE_LEANFT/${three_levels_up_workspace}\/leanft/g' docker-compose.yml"
eval $command2

echo \#\!/bin/bash$'\n'"curl -X POST http://${JENKINS_IP}:${JENKINS_PORT}/job/Adventage-Online-Shopping-Pipeline-Commit/build" > $workspace/.git/hooks/post-commit
chmod +x ${workspace}/.git/hooks/post-commit
if [ ! -d "/${USER_NAME}/.ssh" ]; then
  mkdir -p /${USER_NAME}/.ssh
fi
echo "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDVgtJmv2gShk05pz0Pzhrxuk+yv4uvIFoNfoUpd5/FWScl382hyUMp9CkRymtzjh4r/P6zlW2UX80kMUHiMmv3Khxz5eKTVpr/4C91GrdLvOYij5iQ1kbAoxmY+ih0NSds4AHyz/oNfCxgDnRPtapzL6Ionx6TY2t5lNMW1+OOnWsMMUe2pIqDBh+N7GlTwAjBbefBQdL397GrEXEuzV1ngw464d6Ea8yVTW7Kom6D2J3fIpEJo1PmF0XCzON3oOau9K9P6999WxijWI5PSkwIL+vGyDnmM9xkSXzNnwdwVWA7Uu1fbgE5c50iACHv5chYfAyr5f3vtV1ZMbVrDV5l AOS_DeVops" > /${USER_NAME}/.ssh/authorized_keys

# if we are in AMAZON we need to remove the proxy from the containers, so we add it to the .evn file
if [ "$PUBLIC_IP" == "AMAZON" ] && [ -z "$(cat .env_private | grep -m 1 "http_proxy")" ];then
 printf "\nhttp_proxy=\nhttps_proxy=">> .env_private
fi

docker login -u=advantageonlineshoppingapp -p=W3lcome1
docker-compose pull
docker-compose up -d
