package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userName",
    "password",
    "base64Token",
    "accountId"
})

@XmlRootElement(name = "EncodePasswordRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class EncodePasswordRequest implements IUserRequest{
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String password;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String userName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String base64Token;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected long accountId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getBase64Token() {
        return base64Token;
    }

    public void setBase64Token(String base64Token){
        this.base64Token = base64Token;
    }

    @Override
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId){
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "EncodePasswordRequest{" +
                ", password='" + password + '\'' +
                '}';
    }
}
