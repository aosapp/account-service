package com.advantage.accountsoap.dto.payment;

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accountId",
        "safePayUsername",
        "safePayPassword",
        "base64Token"
})
@XmlRootElement(name = "AddSafePayMethodRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class AddSafePayMethodRequest implements IUserRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long accountId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String safePayUsername;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String safePayPassword;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    public AddSafePayMethodRequest() {
    }

    public AddSafePayMethodRequest(long accountId, String safePayUsername, String safePayPassword) {
        this.accountId = accountId;
        this.safePayUsername = safePayUsername;
        this.safePayPassword = safePayPassword;
    }

    @Override
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
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

    @Override
    public String getBase64Token() {
        return base64Token;
    }

    @Override
    public String toString() {
        return "AddSafePayMethodRequest{" +
                "accountId=" + accountId +
                ", safePayUsername='" + safePayUsername + '\'' +
                ", safePayPassword='" + safePayPassword + '\'' +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
