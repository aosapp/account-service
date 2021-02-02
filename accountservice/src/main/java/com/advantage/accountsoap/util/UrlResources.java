package com.advantage.accountsoap.util;

import com.advantage.accountsoap.config.SmokingGunInit;
import com.advantage.common.Constants;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import java.net.URL;

@Configuration
public class UrlResources {

    private static URL urlPrefixCatalog;
    private static URL urlPrefixMasterCredit;
    private static URL urlPrefixOrder;
    private static URL urlPrefixSafePay;

    private static URL urlPrefixSoapAccount;
    private static URL urlPrefixSoapShipEx;

    public static URL getUrlSoapAccount() { return urlPrefixSoapAccount; }

    public static URL getUrlCatalog() {
        return urlPrefixCatalog;
    }

    public static URL getUrlMasterCredit() {
        return urlPrefixMasterCredit;
    }

    public static URL getUrlOrder() {
        return urlPrefixOrder;
    }

    public static URL getUrlSafePay() { return urlPrefixSafePay; }

    public static URL getUrlSoapShipEx() { return urlPrefixSoapShipEx; }

    private static final Logger logger = Logger.getLogger(UrlResources.class);

    @Inject
    private Environment env;


    @Bean
    public int setConfiguration(){
        urlPrefixCatalog = generateUrlPrefix("Catalog");
        urlPrefixMasterCredit = generateUrlPrefix("MasterCredit");
        urlPrefixOrder = generateUrlPrefix("Order");
        urlPrefixSafePay = generateUrlPrefix("SafePay");
        //urlPrefixService = generateUrlPrefix("Service");

        urlPrefixSoapAccount = generateUrlSoapPrefix("Account");
        urlPrefixSoapShipEx = generateUrlSoapPrefix("ShipEx");


        if (logger.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder("Url_resources: ").append(System.lineSeparator());
            sb.append("   Catalog=\'" + getUrlCatalog() + "\'").append(System.lineSeparator());
            sb.append("   MasterCredit=\'" + getUrlMasterCredit() + "\'").append(System.lineSeparator());
            sb.append("   Order=\'" + getUrlOrder() + "\'").append(System.lineSeparator());
            sb.append("   SafePay=\'" + getUrlSafePay() + "\'").append(System.lineSeparator());
            sb.append("   Account (SOAP)=\'" + getUrlSoapAccount() + "\'").append(System.lineSeparator());
            sb.append("   ShipEx (SOAP)=\'" + getUrlSoapShipEx() + "\'");
            logger.info(sb.toString());
        }
        return 1;
    }

    public URL generateUrlPrefix(String serviceName) {
        URL url = null;

        try {
            String schema = Constants.URI_SCHEMA;
            String host = "";

            if(env.getProperty(Constants.SINGLE_MACHINE_DEPLOYMENT).equals("true")){
                host = "localhost";
            }
            else{
                host = env.getProperty(serviceName.toLowerCase() + ".service.url.host");
            }

            String isAOSDomain = env.getProperty("AOS.Domain.url.host");
            int port = (isAOSDomain != null && isAOSDomain.equalsIgnoreCase("Yes"))
                    || host.charAt(0) == '@'
                    ? 80
                    : Integer.parseInt(env.getProperty(serviceName.toLowerCase() + ".service.url.port"));

            String suffix = '/' + env.getProperty(serviceName.toLowerCase() + ".service.url.suffix") + "/";
            host = host.charAt(0) == '@' ? "localhost" : host;
            url = new URL(schema, host, port, suffix);

        } catch (Throwable e) {
            logger.fatal("Wrong properties file", e);
        }
        logger.debug("URL = " + url.toString());
        return url;
    }

    public URL generateUrlSoapPrefix(String serviceName) {

        URL urlWithWsdl = null;

        try {
            String schema = Constants.URI_SCHEMA;
            String host = "";

            if(env.getProperty(Constants.SINGLE_MACHINE_DEPLOYMENT).equals("true")){
                host = "localhost";
            }
            else{
                host = env.getProperty(serviceName.toLowerCase() + ".soapservice.url.host");
            }

            int port = host.charAt(0) == '@'
                    ? 80 : Integer.parseInt(env.getProperty(serviceName.toLowerCase() + ".soapservice.url.port"));
            String suffix = env.getProperty(serviceName.toLowerCase() + ".soapservice.url.suffix");
            String wsdl = env.getProperty(serviceName.toLowerCase() + ".soapservice.url.wsdl");
            if (! wsdl.contains("/")) { suffix += '/'; }
            host = host.charAt(0) == '@' ? "localhost" : host;
            urlWithWsdl = new URL(new URL(schema, host, port, suffix), suffix + wsdl);

        } catch (Throwable e) {
            logger.fatal("Wrong properties file", e);
        }
        logger.debug("URL = " + urlWithWsdl.toString());
        return urlWithWsdl;

    }
}
