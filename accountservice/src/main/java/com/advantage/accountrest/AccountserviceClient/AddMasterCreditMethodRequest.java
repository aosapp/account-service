package com.advantage.accountrest.AccountserviceClient;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "cardNumber",
        "expirationDate",
        "cvvNumber",
        "customerName",
        "accountId",
        "base64Token"
})
@XmlRootElement(name = "AddMasterCreditMethodRequest")
public class AddMasterCreditMethodRequest {
    @XmlElement(name = "cardNumber", required = true)
    private String cardNumber;
    @XmlElement(name = "expirationDate", required = true)
    private String expirationDate;
    @XmlElement(name = "cvvNumber", required = true)
    private String cvvNumber;
    @XmlElement(name = "customerName", required = true)
    private String customerName;
    @XmlElement(name = "accountId", required = true)
    private long accountId;
    @XmlElement(name = "base64Token", required = true)
    private String base64Token;

    public AddMasterCreditMethodRequest() {
    }

    public AddMasterCreditMethodRequest(String cardNumber, String expirationDate, String cvvNumber, String customerName, long accountId) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.customerName = customerName;
        this.accountId = accountId;
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


    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "AddMasterCreditMethodRequest{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvvNumber='" + cvvNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", accountId=" + accountId +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }

    public void setBase64Token(String base64Token) {
        this.base64Token = base64Token;
    }
}
