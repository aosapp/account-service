package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.account.internal.AccountDto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "account"
        })
@XmlRootElement(name = "GetAccountByLoginResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class GetAccountByLoginResponse {
    @XmlElement(name = "AccountResponse", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private AccountDto account;

    public GetAccountByLoginResponse() {
    }

    public GetAccountByLoginResponse(AccountDto account) {
        this.account = account;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "GetAccountByIdResponse{" +
                "account=" + account +
                '}';
    }
}
