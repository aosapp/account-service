package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accountId",
        "base64Token"
})
@XmlRootElement(name = "GetAccountByIdRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class GetAccountByIdRequest implements IUserRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long accountId;

    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    @Deprecated
    public long getId() {
        return accountId;
    }

    public void setId(long id) {
        this.accountId = id;
    }

    @Override
    public long getAccountId() {
        return accountId;
    }

    @Override
    public String getBase64Token() {
        return base64Token;
    }

    @Override
    public String toString() {
        return "GetAccountByIdRequest{" +
                "accountId=" + accountId +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
