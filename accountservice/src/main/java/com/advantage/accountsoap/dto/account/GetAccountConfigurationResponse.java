package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "numberOfFailedLoginAttemptsBeforeBlocking",
                "loginBlockingIntervalInSeconds",
                "productInStockDefaultValue",
                "userSecondWsdl",
                "userLoginTimeout",
                "allowUserConfiguration",
                "productionHostIp",
                "productionHostName"
        })

@XmlRootElement(name = "GetAccountConfigurationResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class GetAccountConfigurationResponse {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private int numberOfFailedLoginAttemptsBeforeBlocking;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long loginBlockingIntervalInSeconds;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private int productInStockDefaultValue;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private boolean userSecondWsdl;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private int userLoginTimeout;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private boolean allowUserConfiguration;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String productionHostIp;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String productionHostName;

    public GetAccountConfigurationResponse() {
    }

    public GetAccountConfigurationResponse(int numberOfFailedLoginAttemptsBeforeBlocking,
                                           long loginBlockingIntervalInMilliSeconds,
                                           int productInStockDefaultValue,
                                           boolean userSecondWsdl,
                                           int userLoginTimeout,
                                           boolean allowUserConfiguration,
                                           String productionHostIp,
                                           String productionHostName) {

        this.numberOfFailedLoginAttemptsBeforeBlocking = numberOfFailedLoginAttemptsBeforeBlocking;
        this.loginBlockingIntervalInSeconds = loginBlockingIntervalInMilliSeconds;
        this.productInStockDefaultValue = productInStockDefaultValue;
        this.userSecondWsdl = userSecondWsdl;
        this.userLoginTimeout = userLoginTimeout;
        this.allowUserConfiguration = allowUserConfiguration;
        this.productionHostIp = productionHostIp;
        this.productionHostName = productionHostName;
    }

    public int getNumberOfFailedLoginAttemptsBeforeBlocking() {
        return numberOfFailedLoginAttemptsBeforeBlocking;
    }

    public void setNumberOfFailedLoginAttemptsBeforeBlocking(int numberOfFailedLoginAttemptsBeforeBlocking) {
        this.numberOfFailedLoginAttemptsBeforeBlocking = numberOfFailedLoginAttemptsBeforeBlocking;
    }

    public long getLoginBlockingIntervalInSeconds() {
        return loginBlockingIntervalInSeconds;
    }

    public void setLoginBlockingIntervalInSeconds(long loginBlockingIntervalInSeconds) {
        this.loginBlockingIntervalInSeconds = loginBlockingIntervalInSeconds;
    }

    public int getProductInStockDefaultValue() {
        return this.productInStockDefaultValue;
    }

    public void setProductInStockDefaultValue(int productInStockDefaultValue) {
        this.productInStockDefaultValue = productInStockDefaultValue;
    }

    public boolean isUserSecondWsdl() {
        return userSecondWsdl;
    }

    public void setUserSecondWsdl(boolean userSecondWsdl) {
        this.userSecondWsdl = userSecondWsdl;
    }

    public int getUserLoginTimeout() {
        return this.userLoginTimeout;
    }

    public void setUserLoginTimeout(int userLoginTimeout) {
        this.userLoginTimeout = userLoginTimeout;
    }

    public boolean isAllowUserConfiguration() {
        return this.allowUserConfiguration;
    }

    public void setAllowUserConfiguration(boolean allowUserConfiguration) {
        this.allowUserConfiguration = allowUserConfiguration;
    }

    public String getProductionHostIp() {
        return this.productionHostIp;
    }

    public void setProductionHostIp(String productionHostIp) {
        this.productionHostIp = productionHostIp;
    }

    public String getProductionHostName() {
        return this.productionHostName;
    }

    public void setProductionHostName(String productionHostName) {
        this.productionHostName = productionHostName;
    }

    @Override
    public String toString() {
        return "GetAccountConfigurationResponse{" +
                "numberOfFailedLoginAttemptsBeforeBlocking=" + numberOfFailedLoginAttemptsBeforeBlocking +
                ", loginBlockingIntervalInSeconds=" + loginBlockingIntervalInSeconds +
                ", productInStockDefaultValue=" + productInStockDefaultValue +
                ", userSecondWsdl=" + userSecondWsdl +
                ", userLoginTimeout=" + userLoginTimeout +
                ", allowUserConfiguration=" + allowUserConfiguration +
                ", productionHostIp=" + productionHostIp +
                ", productionHostName=" + productionHostName +
                '}';
    }
}
