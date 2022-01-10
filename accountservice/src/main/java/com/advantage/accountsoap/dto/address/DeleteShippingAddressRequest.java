package com.advantage.accountsoap.dto.address;

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

        "accountId",
        "base64Token"
})
@XmlRootElement(name = "DeleteShippingAddressRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class DeleteShippingAddressRequest implements IUserRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long accountId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)

    private String base64Token;

    @Override
    public long getAccountId() {
        return accountId;
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
        return "DeletePaymentPreferenceRequest{" +
                ", accountId=" + accountId +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}