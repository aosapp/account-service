package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "lastName",
        "firstName",
        "loginName",
        "countryId",
        "stateProvince",
        "cityName",
        "address",
        "zipcode",
        "phoneNumber",
        "email",
        "password",
        "accountType",
        "allowOffersPromotion"
})
@XmlRootElement(name = "AccountCreateRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class AccountCreateRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String lastName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String firstName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String loginName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected Long countryId;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String stateProvince;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String cityName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String address;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String zipcode;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String phoneNumber;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String email;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected String password;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected Integer accountType;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getCountry() {
        return countryId;
    }

    public void setCountry(Long country) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
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
        return "AccountCreateRequest{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", countryId=" + countryId +
                ", stateProvince='" + stateProvince + '\'' +
                ", cityName='" + cityName + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accountType=" + accountType +
                ", allowOffersPromotion=" + allowOffersPromotion +
                '}';
    }
}


