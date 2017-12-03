package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

/**
 * @author Binyamin Regev on 02/02/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "loginUser",
        "base64Token"
})
@XmlRootElement(name = "AccountLogoutRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class AccountLogoutRequest implements IUserRequest {
    //Actually this field is AccountId as a string
    @XmlElement(name = "loginUser", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String loginUser;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    public AccountLogoutRequest() {
    }

    public AccountLogoutRequest(final String loginUser, final String base64Token) {
        this.setAccountId(loginUser);
        this.setToken(base64Token);
    }

    public String getStrAccountId() {
        return this.loginUser;
    }

    public void setAccountId(String strAccountId) {
        this.loginUser = strAccountId;
    }

    public void setToken(String base64Token) {
        this.base64Token = base64Token;
    }

    @Override
    public long getAccountId() {
        return Long.valueOf(loginUser);
    }

    @Override
    public String getBase64Token() {
        return base64Token;
    }

    @Override
    public String toString() {
        return "AccountLogoutRequest{" +
                "loginUser='" + loginUser + '\'' +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
