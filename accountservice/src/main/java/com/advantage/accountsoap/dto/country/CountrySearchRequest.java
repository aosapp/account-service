package com.advantage.accountsoap.dto.country;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "internationalPhonePrefix",
        "startOfName"
})
@XmlRootElement(name = "CountrySearchRequest", namespace = WebServiceConfig.NAMESPACE_URI)
public class CountrySearchRequest {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private int internationalPhonePrefix;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String startOfName;

    public int getInternationalPhonePrefix() {
        return internationalPhonePrefix;
    }

    public void setInternationalPhonePrefix(int internationalPhonePrefix) {
        this.internationalPhonePrefix = internationalPhonePrefix;
    }

    public String getStartOfName() {
        return startOfName;
    }

    public void setStartOfName(String startOfName) {
        this.startOfName = startOfName;
    }

    @Override
    public String toString() {
        return "CountrySearchRequest{" +
                "internationalPhonePrefix=" + internationalPhonePrefix +
                ", startOfName='" + startOfName + '\'' +
                '}';
    }
}
