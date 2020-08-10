package com.advantage.accountsoap.config;

import com.advantage.accountsoap.dto.account.GetAccountConfigurationResponse;
import com.advantage.common.Constants;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.inject.Inject;

/**
 * Application User configuration class
 */
@Configuration
public class AccountConfiguration {

    @Inject
    private Environment env;

    private static final Logger logger = Logger.getLogger(AccountConfiguration.class);
    //private AppUserConfig appUserConfig = new AppUserConfig();

    private static int numberOfFailedLoginAttemptsBeforeBlocking;  //numberOfFailedLoginAttemptsBeforeBlocking
    private static long loginBlockingIntervalInSeconds;              //loginBlockingIntervalInSeconds
    private static int productInStockDefaultValue;
    private static int userLoginTimeout;
    private static String allowUserConfiguration;
    private static String productionHostIp;
    private static String productionHostName;
    private static String productionHostPortAccountservice;

    //  //  Class that is called must have a method "public void init() throws Exception"
    //@Bean(initMethod = "init")
    //public AppUserConfig initAppUserConfiguration() {
    //    return new AppUserConfig();
    //}

    @Bean
    public int getAppUserConfiguration() {
        this.setNumberOfLoginAttemptsBeforeBlocking(Constants.ENV_NUMBER_OF_LOGIN_TRIES_BEFORE_BLOCKING);
        this.setLoginBlockingIntervalInSeconds(Constants.ENV_USER_LOGIN_BLOCKING);
        this.setProductInStockDefaultValue(Constants.ENV_PRODUCT_INSTOCK_DEFAULT_VALUE);
        this.setUserLoginTimeout(Constants.ENV_USER_LOGIN_TIMEOUT);
        this.setAllowUserConfiguration(Constants.ENV_ALLOW_USER_CONFIGURATION);
        this.setProductionHostName(Constants.ENV_PRODUCTION_HOST_NAME);
        this.setProductionHostPortAccountService(Constants.ENV_PRODUCTION_HOST_PORT_ACCOUNTSERVICE);
        this.setProductionHostIp(Constants.ENV_PRODUCTION_HOST_IP);

        logger.debug("Configuration: loginBlockingIntervalInSeconds=" + this.getLoginBlockingIntervalInSeconds() + System.lineSeparator());
        logger.debug("Configuration: numberOfFailedLoginAttemptsBeforeBlocking=" + this.getNumberOfLoginAttemptsBeforeBlocking() + System.lineSeparator());
        logger.debug("Configuration: productInStockDefaultValue=\"" + this.getProductInStockDefaultValue() + "\"" + System.lineSeparator());
        logger.debug("Configuration: userLoginTimeout=\"" + this.getUserLoginTimeout() + "\"" + System.lineSeparator());
        logger.debug("Configuration: allowUserConfiguration=\"" + this.getAllowUserConfiguration() + "\"" + System.lineSeparator());
        logger.debug("Configuration: productionHostPortAccountservice=\"" + this.getProductionHostPortAccountservice() + "\"" + System.lineSeparator());
        logger.debug("Configuration: productionHostIp=\"" + this.getProductionHostIp() + "\"" + System.lineSeparator());
        logger.debug("Configuration: productionHostName=\"" + this.getProductionHostName() + "\"" + System.lineSeparator());

        return 1;   //  Successful
    }

    /**
     * <ul>Get configuration value:</ul> <br/>
     * Number of unsuccessful login attempts before blocking the user from attempting to login again.
     *
     * @return Number of unsuccessful login attempts.
     */
    public static int getNumberOfLoginAttemptsBeforeBlocking() {
        return numberOfFailedLoginAttemptsBeforeBlocking;
    }

    private void setNumberOfLoginAttemptsBeforeBlocking(final String parameterKey) {
        String parameterValue = env.getProperty(parameterKey);
        numberOfFailedLoginAttemptsBeforeBlocking = (parameterValue != null ? Integer.valueOf(parameterValue) : 0);
    }

    /**
     * <ul>Get configuration value:</ul> <br/>
     * How much time the user is blocked from attempting login again? Data in seconds.
     *
     * @return How much time the user is blocked from attempting login again?
     */
    public static long getLoginBlockingIntervalInSeconds() {
        return loginBlockingIntervalInSeconds;
    }

    private void setLoginBlockingIntervalInSeconds(final String parameterKey) {
        String parameterValue = env.getProperty(parameterKey);
        loginBlockingIntervalInSeconds = (parameterValue != null ? Integer.valueOf(parameterValue) : 0);
    }

    public static int getProductInStockDefaultValue() {
        return productInStockDefaultValue;
    }

    private void setProductInStockDefaultValue(final String parameterKey) {
        String parameterValue = env.getProperty(parameterKey);
        productInStockDefaultValue = (parameterValue != null ? Integer.valueOf(parameterValue) : 0);
    }

    public static int getUserLoginTimeout() {
        return userLoginTimeout;
    }

    /**
     * User login timeout parameter value in minutes, default 60 minutes.
     */
    public void setUserLoginTimeout(final String parameterKey) {
        String parameterValue = env.getProperty(parameterKey);
        userLoginTimeout = (parameterValue != null ? Integer.valueOf(parameterValue) : 0);
    }

    public static String getAllowUserConfiguration() {
        return allowUserConfiguration;
    }

    public void setAllowUserConfiguration(final String parameterKey) {
        allowUserConfiguration = (env.getProperty(parameterKey) != null ? env.getProperty(parameterKey) : "null");
    }

    public static String getProductionHostPortAccountservice() {
        return productionHostPortAccountservice;
    }

    public void setProductionHostPortAccountService(final String parameterKey) {
        String parameterValue = env.getProperty(parameterKey);
        productionHostPortAccountservice = (parameterValue != null ? env.getProperty(parameterKey) : "null");
    }

    public static String getProductionHostIp() {
        return productionHostIp;
    }

    public void setProductionHostIp(final String parameterKey) {
        String parameterValue = env.getProperty(parameterKey);
        productionHostIp = (parameterValue != null ? "http://" + env.getProperty(parameterKey) + ":" + productionHostPortAccountservice : "null");
    }

    public static String getProductionHostName() {
        return productionHostName;
    }

    public void setProductionHostName(final String parameterKey) {
        String parameterValue = env.getProperty(parameterKey);
        productionHostName = (parameterValue != null ? "http://" + env.getProperty(parameterKey) : "null");
    }

    public GetAccountConfigurationResponse getAllConfigurationParameters() {
        GetAccountConfigurationResponse getAccountConfigurationResponse = new GetAccountConfigurationResponse();

        getAccountConfigurationResponse.setNumberOfFailedLoginAttemptsBeforeBlocking(this.getNumberOfLoginAttemptsBeforeBlocking());
        getAccountConfigurationResponse.setLoginBlockingIntervalInSeconds(this.getLoginBlockingIntervalInSeconds());
        getAccountConfigurationResponse.setProductInStockDefaultValue(this.getProductInStockDefaultValue());
        getAccountConfigurationResponse.setUserLoginTimeout(this.getUserLoginTimeout());
        getAccountConfigurationResponse.setAllowUserConfiguration(this.getAllowUserConfiguration().equalsIgnoreCase("yes"));
        getAccountConfigurationResponse.setUserSecondWsdl(false);   //  MOVED to user-level
        getAccountConfigurationResponse.setProductionHostIp(this.getProductionHostIp());
        getAccountConfigurationResponse.setProductionHostName(this.getProductionHostName());

        return getAccountConfigurationResponse;
    }
}
