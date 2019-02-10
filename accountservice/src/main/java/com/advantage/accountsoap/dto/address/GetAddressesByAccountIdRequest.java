package com.advantage.accountsoap.dto.address;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accountId",
        "base64Token"
})
@XmlRootElement(name = "GetAddressesByAccountIdRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class GetAddressesByAccountIdRequest implements IUserRequest {
    @XmlElement(name = "accountId", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private int accountId;

    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    @Deprecated
    public int getId() {
        return accountId;
    }

    @Deprecated
    public void setId(int id) {
        this.accountId = id;
    }

    @Override
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String getBase64Token() {
        return base64Token;
    }

    public void setBase64Token(String base64Token) {
        this.base64Token = base64Token;
    }

    @Override
    public String toString() {
        return "GetAddressesByAccountIdRequest{" +
                "accountId=" + accountId +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
