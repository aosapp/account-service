package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

/**
 * <b>IMPORTANT:</b> {@code base64Token} must be of an <i>ADMIN USER</i>.
 * @author Binyamin Regev on 14/03/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accountId",
        "newPassword",
        "base64Token"
})
@XmlRootElement(name = "AccountResetPasswordRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class AccountResetPasswordRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long accountId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String newPassword;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getBase64Token() {
        return base64Token;
    }

    public void setBase64Token(String base64Token) {
        this.base64Token = base64Token;
    }

    @Override
    public String toString() {
        return "AccountResetPasswordRequest{" +
                "accountId=" + accountId +
                ", newPassword='" + newPassword + '\'' +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
