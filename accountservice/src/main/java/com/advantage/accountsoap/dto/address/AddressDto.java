package com.advantage.accountsoap.dto.address;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "id",
                "addressLine1",
                "addressLine2",
                "city",
                "country",
                "state",
                "postalCode",
                "accountId"
        })
@XmlRootElement(name = "Address", namespace = WebServiceConfig.NAMESPACE_URI)
public class AddressDto {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = false, nillable = true)
    private long id;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String addressLine1;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String addressLine2;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String city;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String country;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String state;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String postalCode;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private long accountId;

    public AddressDto() {
    }

    public AddressDto(long id, String addressLine1, String addressLine2, String city, String country, String state,
                      String postalCode, long accountId) {
        this.id = id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.country = country;
        this.state = state;
        this.postalCode = postalCode;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}
