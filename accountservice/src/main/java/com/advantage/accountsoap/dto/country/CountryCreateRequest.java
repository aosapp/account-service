package com.advantage.accountsoap.dto.country;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.IAdminRequest;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "isoName",
        "phonePrefix",
        "base64Token"
})
@XmlRootElement(name = "CountryCreateRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class CountryCreateRequest implements IAdminRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String name;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String isoName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private int phonePrefix;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String base64Token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoName() {
        return isoName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    public int getPhonePrefix() {
        return phonePrefix;
    }

    public void setPhonePrefix(int phonePrefix) {
        this.phonePrefix = phonePrefix;
    }

    @Override
    public String getBase64Token() {
        return base64Token;
    }

    @Override
    public String toString() {
        return "CountryCreateRequest{" +
                "name='" + name + '\'' +
                ", isoName='" + isoName + '\'' +
                ", phonePrefix=" + phonePrefix +
                ", base64Token='" + base64Token + '\'' +
                '}';
    }
}
