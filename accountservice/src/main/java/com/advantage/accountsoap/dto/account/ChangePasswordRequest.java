package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accountId",
        "oldPassword",
        "newPassword",
        "base64Token"
})
@XmlRootElement(name = "ChangePasswordRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class ChangePasswordRequest implements IUserRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long accountId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String oldPassword;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String newPassword;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    @Override
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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
        return "ChangePasswordRequest{" +
                "accountId=" + accountId +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
