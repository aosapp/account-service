package com.advantage.accountsoap.dto.payment;

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accountId",
        "paymentMethod",
        "base64Token"
})
@XmlRootElement(name = "PaymentMethodUpdateRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class PaymentMethodUpdateRequest implements IUserRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected long accountId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected Integer paymentMethod;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String base64Token;

    public PaymentMethodUpdateRequest() {
    }

    public PaymentMethodUpdateRequest(long accountId, Integer paymentMethod) {
        this.setAccountId(accountId);
        this.setPaymentMethod(paymentMethod);
    }

    @Override
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String getBase64Token() {
        return base64Token;
    }

    @Override
    public String toString() {
        return "PaymentMethodUpdateRequest{" +
                "accountId=" + accountId +
                ", paymentMethod=" + paymentMethod +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
