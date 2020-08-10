package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.account.internal.AccountDtoNew;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "account"
        })
@XmlRootElement(name = "GetAccountByIdNewResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class GetAccountByIdNewResponse {
    @XmlElement(name = "AccountResponse", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private AccountDtoNew account;

    public GetAccountByIdNewResponse() {
    }

    public GetAccountByIdNewResponse(AccountDtoNew account) {
        this.account = account;
    }

    public AccountDtoNew getAccount() {
        return account;
    }

    public void setAccount(AccountDtoNew account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "GetAccountByIdNewResponse{" +
                "account=" + account +
                '}';
    }
}
