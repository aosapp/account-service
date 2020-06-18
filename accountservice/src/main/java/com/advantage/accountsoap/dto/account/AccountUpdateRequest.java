package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IUserRequest;
import com.advantage.accountsoap.dto.country.CountryID;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accountId",
        "lastName",
        "firstName",
        "countryId",
        "stateProvince",
        "cityName",
        "address",
        "zipcode",
        "phoneNumber",
        "email",
        "accountType",
        "allowOffersPromotion"
})
@XmlRootElement(name = "AccountUpdateRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class AccountUpdateRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String lastName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String firstName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected long accountId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected CountryID countryId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String stateProvince;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true, nillable = true)
    protected String cityName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true, nillable = true)
    protected String address;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true, nillable = true)
    protected String zipcode;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true, nillable = true)
    protected String phoneNumber;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true, nillable = true)
    protected String email;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected AccountType accountType;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true, nillable = true)
    protected boolean allowOffersPromotion;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public CountryID getCountry() {

        return countryId != null ? countryId : CountryID.UNITED_STATES_US;
    }

    public void setCountry(CountryID country) {
        this.countryId = country;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAccountType() {
        return accountType != null ? accountType.AccountType() : 10;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public boolean isAllowOffersPromotion() {
        return allowOffersPromotion;
    }

    public void setAllowOffersPromotion(boolean allowOffersPromotion) {
        this.allowOffersPromotion = allowOffersPromotion;
    }

    @Override
    public String toString() {
        return "AccountUpdateRequest{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", accountId=" + accountId +
                ", countryId=" + countryId +
                ", stateProvince='" + stateProvince + '\'' +
                ", cityName='" + cityName + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", accountType=" + accountType +
                ", allowOffersPromotion=" + allowOffersPromotion +
                '}';
    }
}
