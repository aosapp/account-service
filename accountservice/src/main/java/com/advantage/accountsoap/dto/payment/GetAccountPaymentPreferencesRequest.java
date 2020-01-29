package com.advantage.accountsoap.dto.payment;


import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accountId",
        "base64Token"
})
@XmlRootElement(name = "GetAccountPaymentPreferencesRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class GetAccountPaymentPreferencesRequest implements IUserRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long accountId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    @Override
    public long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String getBase64Token() {
        return base64Token;
    }

    @Override
    public String toString() {
        return "GetAccountPaymentPreferencesRequest{" +
                "accountId=" + accountId +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
