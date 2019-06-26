package com.advantage.accountsoap.dto.payment;

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "userId",
        "cardNumber",
        "expirationDate",
        "cvvNumber",
        "customerName",
        "referenceId",
        "base64Token"
})
@XmlRootElement(name = "UpdateMasterCreditMethodRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class UpdateMasterCreditMethodRequest implements IUserRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long userId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String cardNumber;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String expirationDate;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String cvvNumber;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String customerName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long referenceId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    public UpdateMasterCreditMethodRequest() {
    }

    public UpdateMasterCreditMethodRequest(long userId, String cardNumber, String expirationDate, String cvvNumber, String customerName, long referenceId) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.customerName = customerName;
        this.referenceId = referenceId;
    }

    @Deprecated
    public long getUserId() {
        return this.userId;
    }

    @Deprecated
    public void setUserId(long userId) {
        this.userId = userId;
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

    public long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(long referenceId) {
        this.referenceId = referenceId;
    }

    @Override
    public long getAccountId() {
        return userId;
    }

    @Override
    public String getBase64Token() {
        return base64Token;
    }

    @Override
    public String toString() {
        return "UpdateMasterCreditMethodRequest{" +
                "userId=" + userId +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvvNumber='" + cvvNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", referenceId=" + referenceId +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
