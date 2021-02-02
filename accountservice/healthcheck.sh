#!/bin/sh

status=$(curl -s -H "Content-Type: text/xml; charset=UTF-8" -H "SOAPAction:" -d @healthcheck.xml --write-out "%{http_code}" -X POST  -i  http://localhost:8080/accountservice | head -n 1)
echo $status
case "$status" in *200*) exit 0 ;;
esac

