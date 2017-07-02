#!/bin/bash

mkdir "Temp"
mv accountservice.war ./Temp
cd "./Temp"
chmod 777 accountservice.war
ls -l
jar -xf accountservice.war
#sed -i 's/=validate/=create/g' "./WEB-INF/classes/properties/internal_config_for_env.properties"
command1="sed -i 's/=localhost/=${POSTGRES_IP}/g' ./WEB-INF/classes/properties/internal_config_for_env.properties"
command2="sed -i 's/=5432/=${POSTGRES_PORT}/g' ./WEB-INF/classes/properties/internal_config_for_env.properties"
command3="sed -i 's/account\.soapservice\.url\.port=8080/account\.soapservice\.url\.port=${ACCOUNT_PORT}/g' ./WEB-INF/classes/properties/services.properties"
command4="sed -i 's/=8080/=${MAIN_PORT}/g' ./WEB-INF/classes/properties/services.properties"
command5="sed -i 's/account\.soapservice\.url\.host=localhost/account\.soapservice\.url\.host=${ACCOUNT_IP}/g' ./WEB-INF/classes/properties/services.properties"
command6="sed -i 's/=localhost/=${MAIN_IP}/g' ./WEB-INF/classes/properties/services.properties"
eval $command1
eval $command2
eval $command3
eval $command4
eval $command5
eval $command6
sed -i 's/single\.machine\.deployment=true/single\.machine\.deployment=false/g' "./WEB-INF/classes/properties/services.properties"
sed -i 's/\.\./\/opt/g' "./WEB-INF/classes/log4j.properties"
rm -rf accountservice.war
jar -cvf accountservice.war *
mv accountservice.war ../
rm -rf *
cd ../

rm -rf "Temp/"

