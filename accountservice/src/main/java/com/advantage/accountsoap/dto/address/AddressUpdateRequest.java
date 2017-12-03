package com.advantage.accountsoap.dto.address;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "address",
        "base64Token"
})
@XmlRootElement(name = "AddressUpdateRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class AddressUpdateRequest implements IUserRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private AddressDto address;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    @Override
    public long getAccountId() {
        return address.getAccountId();
    }

    @Override
    public String getBase64Token() {
        return base64Token;
    }

    @Override
    public String toString() {
        return "AddressUpdateRequest{" +
                "address=" + address +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
