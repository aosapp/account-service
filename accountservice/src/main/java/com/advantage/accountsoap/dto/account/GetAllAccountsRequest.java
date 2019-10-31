package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IAdminRequest;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "base64Token"
})
@XmlRootElement(name = "GetAllAccountsRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class GetAllAccountsRequest implements IAdminRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    @Override
    public String getBase64Token() {
        return base64Token;
    }

    @Override
    public String toString() {
        return "GetAllAccountsRequest{" +
                "base64Token='" + base64Token + '\'' +
                '}';
    }
}
