package com.advantage.accountsoap.dto.address;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.account.AccountStatusResponse;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "response"
        })
@XmlRootElement(name = "AddressStatusResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class AddAddressesResponse {
    @XmlElement(name = "StatusMessage", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private AddressStatusResponse response;

    public AddAddressesResponse() {
    }

    public AddAddressesResponse(AddressStatusResponse response) {
        this.response = response;
    }

    public AddressStatusResponse getResponse() {
        return response;
    }

    public void setResponse(AddressStatusResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "AddAddressesResponse{" +
                "response=" + response +
                '}';
    }
}
