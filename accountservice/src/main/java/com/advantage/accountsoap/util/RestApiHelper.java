package com.advantage.accountsoap.util;


import com.advantage.common.Constants;
import com.advantage.common.dto.DemoAppConfigParameter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * @author Binyamin Regev on on 22/06/2016.
 */
public abstract class RestApiHelper {

    private static final Logger logger = Logger.getLogger(RestApiHelper.class);

    private static final String DEMO_APP_CONFIG_BY_PARAMETER_NAME = "DemoAppConfig/parameters/";    //  Show_error_500_in_update_cart


    public RestApiHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param jsonObjectString
     * @return
     * @throws IOException
     */
    private static DemoAppConfigParameter getConfigParameterValueFromJsonObjectString(String jsonObjectString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        DemoAppConfigParameter demoAppConfigParameter = objectMapper.readValue(jsonObjectString, DemoAppConfigParameter.class);

        return demoAppConfigParameter;
    }

    /**
     *
     * @param parameterName
     * @return
     */
    public static String getDemoAppConfigParameterValue(String parameterName) {
        URL productsPrefixUrl = null;
        try {
            productsPrefixUrl = new URL(UrlResources.getUrlCatalog(), DEMO_APP_CONFIG_BY_PARAMETER_NAME);
        } catch (MalformedURLException e) {
            logger.error(productsPrefixUrl, e);
        }

        URL getDemoAppConfigByParameterName = null;
        try {
            getDemoAppConfigByParameterName = new URL(productsPrefixUrl, parameterName);
        } catch (MalformedURLException e) {
            logger.error(getDemoAppConfigByParameterName, e);
        }

        if (logger.isInfoEnabled()) {
            logger.info("stringURL=\"" + getDemoAppConfigByParameterName.toString() + "\"");
        }

        DemoAppConfigParameter demoAppConfigParameter = null;
        String parameterValue = null;

        try {
            String stringResponse = RestApiHelper.httpGet(getDemoAppConfigByParameterName, "order");
            if (!stringResponse.equalsIgnoreCase(Constants.NOT_FOUND)) {
                demoAppConfigParameter = getConfigParameterValueFromJsonObjectString(stringResponse);
                if (demoAppConfigParameter != null) {
                    parameterValue = demoAppConfigParameter.getParameterValue();
                }
            }
        } catch (IOException e) {
            logger.error("Calling httpGet(\"" + getDemoAppConfigByParameterName.toString() + "\") throws IOException: ", e);
        }

        return parameterValue;
    }

    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String httpGet(URL url, String serviceName) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        logger.debug("HttpURLConnection = " + conn.getURL().toString());
        conn.setRequestProperty(HttpHeaders.USER_AGENT, "AdvantageService/" + serviceName);
        int responseCode = conn.getResponseCode();

        logger.debug("responseCode = " + responseCode);
        String returnValue = responseSolver(responseCode, conn);

        conn.disconnect();

        return returnValue;
    }

    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String httpGet(URL url) throws IOException {
        Proxy proxy = null;
        try{
            proxy = getProxyFromProperties();
        } catch (Exception e){
            logger.error("Unable to build proxy", e);
        }

        HttpURLConnection conn = null;
        if(proxy == null){
            conn = (HttpURLConnection) url.openConnection();
        }
        else
            conn = (HttpURLConnection) url.openConnection(proxy);
        conn.setConnectTimeout(120000);
        logger.debug("HttpURLConnection = " + conn.getURL().toString());
        int responseCode = conn.getResponseCode();

        logger.debug("responseCode = " + responseCode);
        String returnValue = responseSolver(responseCode, conn);

        conn.disconnect();

        return returnValue;
    }

    public static Proxy getProxyFromProperties() throws Exception{

        String host = System.getProperty("aos.cobol.proxy.host");
        String port = System.getProperty("aos.cobol.proxy.port");

        if(host == null || port == null)
            return null;
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, Integer.parseInt(port)));
    }

    public static String httpGetWithAuthorization(URL url, String serviceName, String key, String value ) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        logger.debug("HttpURLConnection = " + conn.getURL().toString());
        conn.setRequestProperty(HttpHeaders.USER_AGENT, "AdvantageService/" + serviceName);
        conn.setRequestProperty(key, value);
        int responseCode = conn.getResponseCode();

        logger.debug("responseCode = " + responseCode);

        String returnValue = responseSolver(responseCode, conn);

        conn.disconnect();

        return returnValue;
    }

    private static String responseSolver (int responseCode, HttpURLConnection conn) throws IOException{

        String returnValue;
        switch (responseCode) {
            case org.apache.http.HttpStatus.SC_OK:
                // Buffer the result into a string
                InputStreamReader inputStream = new InputStreamReader(conn.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStream);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();
                returnValue = sb.toString();
                break;
            case org.apache.http.HttpStatus.SC_CONFLICT:
                //  Product not found
                returnValue = "CONFLICT";
                break;
            case org.apache.http.HttpStatus.SC_NOT_FOUND:
                returnValue = "NOT FOUND";
                break;
            case org.apache.http.HttpStatus.SC_FORBIDDEN:
                returnValue = "FORBIDDEN";
                break;
            case org.apache.http.HttpStatus.SC_UNAUTHORIZED:
                returnValue = "UNAUTHORIZED";
                break;
            default:
                IOException e = new IOException(conn.getResponseMessage());
                logger.fatal(e);
                throw e;
        }

        return returnValue;
    }
}
