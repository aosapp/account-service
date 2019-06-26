package com.advantage.accountsoap.dto.address;

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.account.AccountStatusResponse;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "response"
        })
@XmlRootElement(name = "DeleteShippingAddressResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class DeleteShippingAddressResponse {
    @XmlElement(name = "StatusMessage", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private AccountStatusResponse response;

    public DeleteShippingAddressResponse() {
    }

    public DeleteShippingAddressResponse(AccountStatusResponse response) {
        this.response = response;
    }

    public DeleteShippingAddressResponse(AddressStatusResponse response) {

    }

    public AccountStatusResponse getResponse() {
        return response;
    }

    public void setResponse(AccountStatusResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "DeleteShippingAddressResponse{" +
                "response=" + response +
                '}';
    }
}

