#!/bin/bash

CT="Content-Type:application/json"
mqm_default_sharedspace=1001

echo "JENKINS_IP=${JENKINS_IP}"
echo "JENKINS_PORT=${JENKINS_PORT}"

# Set colors
cc_red="\e[31m"
cc_green="\e[32m"
cc_yellow="\e[33m"
cc_blue="\e[34m"
cc_normal="\e[39m"

echo -e "\n---------------------------------------------------------------------------"
echo -e "${cc_blue}Waiting for Jenkins to load ${cc_normal}"
echo -e "-----------------------------------------------------------------------------\n"

status1=`curl -s -o /dev/null -w '%{http_code}' http://${JENKINS_IP}:${JENKINS_PORT}/`
 while true
 do
  if [ "${status1}" != "200" ] ;then
   status1=`curl -s -o /dev/null -w '%{http_code}' http://${JENKINS_IP}:${JENKINS_PORT}/`
  else
   break
  fi
 done

echo -e "\n---------------------------------------------------------------------------"
echo -e "${cc_blue}Creating CI Server with CI/CD role ${cc_normal}"
echo -e "-----------------------------------------------------------------------------\n"

curl -H ${CT} -X "POST" -d '{"user":"sa@nga","password":"Welcome1"}' -c auth_cookie.txt http://${OCTANE_IP}:${OCTANE_PORT}/authentication/sign_in

#*******instance_id in jenkins need to be the same!!
jenkins_url="http://${JENKINS_IP}:${JENKINS_PORT}"
curl -b auth_cookie.txt -H ${CT} -X "POST" -d '{"data":[{"name":"AOS","server_type":"jenkins","url":"'"$jenkins_url"'","instance_id":"80b57f19-bb16-4d97-a93c-732aecb747aa"}]}' http://${OCTANE_IP}:${OCTANE_PORT}/api/shared_spaces/1001/workspaces/1002/ci_servers

echo -e "\n---------------------------------------------------------------------------"
echo -e "${cc_blue}Add Pipelines ${cc_normal}"
echo -e "-----------------------------------------------------------------------------\n"

curl -b auth_cookie.txt -H ${CT} -H "HPECLIENTTYPE:HPE_MQM_UI" -X "POST" -d '{"data":[{"name":"CI Pipeline","notification_track":false,"notification_track_tester":false,"ci_server":{"type":"ci_server","id":"1001"},"root_job_name":"Adventage-Online-Shopping-Pipeline-Commit"}]}' http://${OCTANE_IP}:${OCTANE_PORT}/api/shared_spaces/${mqm_default_sharedspace}/workspaces/1002/pipelines

curl -b auth_cookie.txt -H ${CT} -H "HPECLIENTTYPE:HPE_MQM_UI" -X "POST" -d '{"data":[{"name":"Nightly Pipeline","notification_track":false,"notification_track_tester":false,"ci_server":{"type":"ci_server","id":"1001"},"root_job_name":"Adventage-Online-Shopping-Pipeline-Nightly"}]}' http://${OCTANE_IP}:${OCTANE_PORT}/api/shared_spaces/${mqm_default_sharedspace}/workspaces/1002/pipelines