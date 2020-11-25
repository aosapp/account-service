package com.advantage.accountsoap.dto.address;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "shippingAddress"
        })
@XmlRootElement(name = "GetAddressesByAccountIdResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class GetAddressesByAccountIdResponse {
    @XmlElement(name = "ShippingAddress",namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private List<AddressDto> shippingAddress;

    public GetAddressesByAccountIdResponse() {
    }

    public GetAddressesByAccountIdResponse(List<AddressDto> shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<AddressDto> getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(List<AddressDto> shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "GetAddressesByAccountIdResponse{" +
                "shippingAddress=" + shippingAddress +
                '}';
    }
}
