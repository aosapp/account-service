package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.account.internal.AccountDto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "account"
        })
@XmlRootElement(name = "GetAllAccountsResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class GetAllAccountsResponse {
    @XmlElement(name = "account",namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected List<AccountDto> account;

    public List<AccountDto> getAccount() {
        if (account == null) {
            return new ArrayList<>();
        }

        return this.account;
    }

    public void setAccount(List<AccountDto> account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "GetAllAccountsResponse{" +
                "account=" + account +
                '}';
    }
}
