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
                "password"
        })
@XmlRootElement(name = "EncodePasswordResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class EncodePasswordResponse {
    @XmlElement(name = "password", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String password;

    public EncodePasswordResponse(){};

    public EncodePasswordResponse(String password){
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "EncodePasswordResponse{" +
                "password=" + password +
                '}';
    }
}
