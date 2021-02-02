#!/bin/bash
mkdir -p accountservice
chmod 777 accountservice.war
mv accountservice.war "./accountservice"
cd "accountservice"

ls -l
jar -xf accountservice.war
rm -rf accountservice.war
#sed -i 's/=validate/=create/g' "./WEB-INF/classes/properties/internal_config_for_env.properties"
sed -i "s/=localhost/=${POSTGRES_IP}/g" ./WEB-INF/classes/properties/internal_config_for_env.properties
sed -i "s/=5432/=${POSTGRES_PORT}/g" ./WEB-INF/classes/properties/internal_config_for_env.properties
sed -i "s/=postgres/=aos/g" ./WEB-INF/classes/properties/internal_config_for_env.properties
sed -i "s/account\.soapservice\.url\.port=8080/account\.soapservice\.url\.port=${ACCOUNT_PORT}/g" ./WEB-INF/classes/properties/services.properties
sed -i "s/=8080/=${MAIN_PORT}/g" ./WEB-INF/classes/properties/services.properties
sed -i "s/account\.soapservice\.url\.host=localhost/account\.soapservice\.url\.host=${ACCOUNT_IP}/g" ./WEB-INF/classes/properties/services.properties
sed -i "s/=localhost/=${MAIN_IP}/g" ./WEB-INF/classes/properties/services.properties
sed -i 's/single\.machine\.deployment=true/single\.machine\.deployment=false/g' "./WEB-INF/classes/properties/services.properties"
sed -i 's/\.\./\/opt/g' "./WEB-INF/classes/log4j.properties"
if [ "$reverse_proxy" == "true" ];then
  echo "using nginx--------"
  sed -i 's/reverse\.proxy=false/reverse\.proxy=true/g' "./WEB-INF/classes/properties/services.properties"
  #sed -i 's/single\.machine\.deployment=false/single\.machine\.deployment=true/g' "./WEB-INF/classes/properties/services.properties"
fi

cd ../


