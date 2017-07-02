#!/bin/bash
cd webapps
bash updatewars.sh
catalina.sh run
tail -f /dev/null
