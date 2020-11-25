package com.advantage.accountsoap.services;

import com.advantage.accountsoap.config.AccountConfiguration;
import com.advantage.accountsoap.util.UrlResources;
import com.advantage.common.Constants;


import com.advantage.common.utils.JsonHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Service
public class DynamicConfiguration {
    private static final String XML_SLA_ADD_DELAY_SESSIONS_PARAMNAME = "SLA_add_delay_sessions";
    private static final String XML_SLA_ADD_DELAY_TIME_PARAMNAME = "SLA_add_delay_time";
    private static final String XML_MAX_CONCURRENT_USERS_PARAMNAME = "Max_concurrent_users";
    private static final int DEFAULT_DELAY = 0;

    private static final int DEFAULT_NUMBER_OF_SESSIONS_TO_DELAY = 20;
    private static final int DEFAULT_NUMBER_OF_SESSIONS_TO_REJECT = 1000;

    private static final Logger logger = Logger.getLogger(DynamicConfiguration.class);

    @Autowired
    @Qualifier("accountConfiguration")
    private AccountConfiguration accountConfiguration;

//    @Autowired
//    private Environment env;

    // "SLA: Add delay in add to log in response time (seconds)":
    // 0 = (Default) Disabled; any other positive number = the number of seconds to add as a delay in response time
    private int delayLength;

    //This parameter is enabled only if "SLA: Add delay in add to cart response time (seconds)" is greater than zero.
    // The system will start adding the delay if the number of sessions will be higher than this value and will stop the delay when the number of sessions will go back down.
    // Valid values: 0-n, default=20.
    //For LoadRunner and StormRunner
    private int numberOfSessionsToDelay;

    private int numberOfSessionsToReject;

    public DynamicConfiguration() {
        if (logger.isTraceEnabled()) {
            logger.trace("Constructor, objectId=" + ((Object) this).toString());
        }
    }

    public int getDelayLength(int sessionsNumber) {
        //DynamicConfiguration dynamicConfiguration = new DynamicConfiguration();
        readConfiguration();
        int result = DEFAULT_DELAY;
        if (logger.isDebugEnabled()) {
            logger.debug("REAL delayLength = " + delayLength + " sec.");
            logger.debug("REAL numberOfSessionsToDelay = " + numberOfSessionsToDelay);
            logger.debug("REAL sessionsNumber = " + sessionsNumber);
        }
        if (sessionsNumber > numberOfSessionsToDelay) {
            result = delayLength;
        }
        logger.info(String.format("Current %d users, max to delay is %d, the delay length is %d seconds.",
                sessionsNumber, numberOfSessionsToDelay, result));
        return result;
    }

    public boolean needReject(int currentLogingUsersNumber) {
        readConfiguration();
        boolean result;
        if (numberOfSessionsToReject == 0) {
            result = false;
        } else {
            result = currentLogingUsersNumber > numberOfSessionsToReject;
        }
        logger.info(String.format("Current logged %d users, max allow users = %d, so needReject = %b",
                currentLogingUsersNumber, numberOfSessionsToReject, result));
        return result;
    }

    private void readConfiguration() {
        if (accountConfiguration.getAllowUserConfiguration().equalsIgnoreCase("yes")) {
            delayLength = requestFromConfigurationDelayTimeResponse();
            if (delayLength > 0) {
                numberOfSessionsToDelay = requestFromCatalogNumberOfSessionsToDelay();
            } else {
                numberOfSessionsToDelay = 0;
            }
            numberOfSessionsToReject = requestFromCatalogNumberOfSessionsToReject();
        } else {
            delayLength = DEFAULT_DELAY;
            numberOfSessionsToDelay = DEFAULT_NUMBER_OF_SESSIONS_TO_DELAY;
            numberOfSessionsToReject = DEFAULT_NUMBER_OF_SESSIONS_TO_REJECT;
        }
        if (logger.isDebugEnabled()) {
            StringBuffer sb = new StringBuffer();
            sb.append("delayLength = ").append(delayLength).append(System.lineSeparator());
            sb.append("numberOfSessionsToDelay = ").append(numberOfSessionsToDelay).append(System.lineSeparator());
            sb.append("numberOfSessionsToReject = ").append(numberOfSessionsToReject);
            logger.debug(sb.toString());
        }
    }

    private int requestFromConfigurationDelayTimeResponse() {
        String delay = getCatalogConfigParameter(XML_SLA_ADD_DELAY_TIME_PARAMNAME);
        if (delay == null) {
            return DEFAULT_DELAY;
        }
        try {
            return Integer.parseInt(delay);
        } catch (NumberFormatException e) {
            logger.warn(delay + " is not a number");
            return DEFAULT_DELAY;
        }
    }

    private int requestFromCatalogNumberOfSessionsToReject() {
        String maxUsers = getCatalogConfigParameter(XML_MAX_CONCURRENT_USERS_PARAMNAME);
        if (maxUsers == null) {
            return DEFAULT_NUMBER_OF_SESSIONS_TO_REJECT;
        }
        try {
            int i = Integer.parseInt(maxUsers);
            if (i == -1) {
                return DEFAULT_NUMBER_OF_SESSIONS_TO_REJECT;
            } else {
                return i;
            }
        } catch (NumberFormatException e) {
            logger.warn(maxUsers + " is not a number");
            return DEFAULT_NUMBER_OF_SESSIONS_TO_REJECT;
        }
    }

    private int requestFromCatalogNumberOfSessionsToDelay() {
        String numberOfSessions = getCatalogConfigParameter(XML_SLA_ADD_DELAY_SESSIONS_PARAMNAME);
        if (numberOfSessions == null) {
            return DEFAULT_NUMBER_OF_SESSIONS_TO_DELAY;
        }
        try {
            return Integer.parseInt(numberOfSessions);
        } catch (NumberFormatException e) {
            logger.warn(numberOfSessions + " is not a number");
            return DEFAULT_NUMBER_OF_SESSIONS_TO_DELAY;
        }
    }

    private String getCatalogConfigParameter(String requestPart) {
        String value = null;
        URL urlConfig;
        try {
            logger.debug("UrlResources.getUrlCatalog()=\"" + UrlResources.getUrlCatalog().toString() + "\"");

            urlConfig = new URL(UrlResources.getUrlCatalog(), "DemoAppConfig/parameters/" + requestPart);
            logger.debug("urlConfig = " + urlConfig);

            HttpURLConnection conn = (HttpURLConnection) urlConfig.openConnection();
            conn.setRequestMethod(HttpMethod.GET.name());
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                String message = "Failed connect to catalog service: HTTP error code : " + responseCode + Constants.DOUBLE_SPACES +
                        "class com.advantage.accountsoap.services.DynamicConfiguration method getCatalogConfigParameter(String requestPart) line 164: urlConfig = " + urlConfig;
                logger.fatal(message);
                throw new RuntimeException(message);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuilder sb = new StringBuilder();

            logger.info("Output from Server .... " + System.lineSeparator());
            while ((output = br.readLine()) != null) {
                sb.append(output);
                logger.trace(output);
            }
            conn.disconnect();
            logger.trace("Disconnected");

            Map<String, Object> jsonMap = JsonHelper.jsonStringToMap(sb.toString());
            value = ((String) jsonMap.get("parameterValue"));
        } catch (MalformedURLException e) {
            logger.fatal(e);
        } catch (IOException e) {
            logger.fatal(e);
        }
        if (value == null) {
            logger.warn("Value is null");
        } else if (value.isEmpty()) {
            logger.warn("Value is empty");
            value = null;
        }
        return value;
    }

}
