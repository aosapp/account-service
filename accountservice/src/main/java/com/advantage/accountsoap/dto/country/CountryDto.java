package com.advantage.accountsoap.dto.country;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "id",
                "name",
                "isoName",
                "phonePrefix"
        })
@XmlRootElement(name = "Country", namespace = WebServiceConfig.NAMESPACE_URI)
public class CountryDto {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private Long id;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String name;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private String isoName;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private int phonePrefix;

    public CountryDto() {
    }

    public CountryDto(Long id, String name, String isoName, int phonePrefix) {
        this.id = id;
        this.name = name;
        this.isoName = isoName;
        this.phonePrefix = phonePrefix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public String toString() {
        return "CountryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isoName='" + isoName + '\'' +
                ", phonePrefix=" + phonePrefix +
                '}';
    }
}
