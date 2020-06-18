package com.advantage.accountsoap.dto.address;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "addresses",
        "accountId",
        "base64Token"
})
@XmlRootElement(name = "AddAddressesRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class AddAddressesRequest implements IUserRequest {
    @XmlElement(name = "address", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private List<AddAddressDto> addresses;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long accountId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    public List<AddAddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddAddressDto> addresses) {
        this.addresses = addresses;
    }

    @Override
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String getBase64Token() {
        return base64Token;
    }

    @Override
    public String toString() {
        return "AddAddressesRequest{" +
                "addresses=" + addresses +
                ", accountId=" + accountId +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
