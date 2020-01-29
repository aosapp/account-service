package com.advantage.accountsoap.dto.country;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.model.Country;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "country"
        })
@XmlRootElement(name = "GetCountriesResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class GetCountriesResponse {
    @XmlElement(name = "Country", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    public List<CountryDto> country;

    public List<CountryDto> getCountry() {
        return country;
    }

    public void setCountry(List<CountryDto> country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "GetCountriesResponse{" +
                "country=" + country +
                '}';
    }
}
