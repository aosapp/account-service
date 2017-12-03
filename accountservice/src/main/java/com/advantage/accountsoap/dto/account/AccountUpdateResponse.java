package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "response"
        })
@XmlRootElement(name = "AccountUpdateResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class AccountUpdateResponse {
    @XmlElement(name = "StatusMessage", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private AccountStatusResponse response;

    public AccountUpdateResponse() {
    }

    public AccountUpdateResponse(AccountStatusResponse response) {
        this.response = response;
    }

    public AccountStatusResponse getResponse() {
        return response;
    }

    public void setResponse(AccountStatusResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "AccountUpdateResponse{" +
                "response=" + response +
                '}';
    }
}
