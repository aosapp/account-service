#!/bin/bash
if [ ! -f /initialized.txt ]; then # Run initialization logic
    cd webapps
    bash updatewars.sh

    sed -i '0,/RE/s/<Connector port="8080"/<!--\n&/' /usr/local/tomcat/conf/server.xml

    sed -i '0,/RE/s/<!-- A "Connector" using the shared thread pool-->/\n-->\nCONNECTOR_DETAILES\n&/' /usr/local/tomcat/conf/server.xml

    connector_details='<Connector port="8080"  maxHttpHeaderSize="8192"\n
    maxThreads="150" minSpareThreads="25" maxSpareThreads="75"\n
    acceptCount="100"\n
    disableUploadTimeout="true"\n
    compression="on"\n
    compressionMinSize="2048"\n
    noCompressionUserAgents="gozilla, traviata"\n
    compressableMimeType="text\/html,text\/xml,text\/json,text\/javascript,text\/css,text\/plain,\n
    application\/javascript,application\/xml,application\/xml+xhtml"  protocol="HTTP\/1.1"\n
    connectionTimeout="20000"\n
    redirectPort="8443" \/>\n'

    command="sed -i 's/CONNECTOR_DETAILES/${connector_details}/' /usr/local/tomcat/conf/server.xml"
    eval $command
    touch /initialized.txt
fi
bash wait-for-it.sh "${POSTGRES_IP}" "${POSTGRES_PORT}"
catalina.sh run
tail -f /dev/null
