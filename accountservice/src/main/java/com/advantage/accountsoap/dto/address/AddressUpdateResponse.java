package com.advantage.accountsoap.dto.address;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "response"
        })
@XmlRootElement(name = "AddressUpdateResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class AddressUpdateResponse {
    @XmlElement(name = "StatusMessage", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private AddressStatusResponse response;

    public AddressUpdateResponse() {
    }

    public AddressUpdateResponse(AddressStatusResponse response) {
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
        return "AddressUpdateResponse{" +
                "response=" + response +
                '}';
    }
}
