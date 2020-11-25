package com.advantage.accountsoap.dto.payment;

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "paymentMethod",
                "cardNumber",
                "expirationDate",
                "cvvNumber",
                "safePayUsername",
                "safePayPassword",
                "customerName",
                "preferenceId"
        })
@XmlRootElement(name = "PaymentPreferencesDto", namespace = WebServiceConfig.NAMESPACE_URI)
public class PaymentPreferencesDto {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private int paymentMethod;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String cardNumber;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String expirationDate;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String cvvNumber;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String safePayUsername;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String safePayPassword;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String customerName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long preferenceId;

    public PaymentPreferencesDto() {
    }

    public PaymentPreferencesDto(int paymentMethod, String cardNumber, String expirationDate,
                                 String cvvNumber, String customerName, String safePayUsername,
                                 String safePayPassword, long preferenceId) {
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.customerName = customerName;
        this.safePayUsername = safePayUsername;
        this.safePayPassword = safePayPassword;
        this.preferenceId = preferenceId;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSafePayUsername() {
        return safePayUsername;
    }

    public void setSafePayUsername(String safePayUsername) {
        this.safePayUsername = safePayUsername;
    }

    public String getSafePayPassword() {
        return this.safePayPassword;
    }

    public void setSafePayPassword(String safePayPassword) {
        this.safePayPassword = safePayPassword;
    }

    public long getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(long preferenceId) {
        this.preferenceId = preferenceId;
    }

    @Override
    public String toString() {
        return "PaymentPreferencesDto{" +
                "paymentMethod=" + paymentMethod +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvvNumber='" + cvvNumber + '\'' +
                ", safePayUsername='" + safePayUsername + '\'' +
                ", safePayPassword='" + safePayPassword + '\'' +
                ", customerName='" + customerName + '\'' +
                ", preferenceId=" + preferenceId +
                '}';
    }
}
