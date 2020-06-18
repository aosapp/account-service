package com.advantage.accountsoap.dto.payment;

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "preference"
        })
@XmlRootElement(name = "PaymentPreferences", namespace = WebServiceConfig.NAMESPACE_URI)
public class GetAccountPaymentPreferencesResponse {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    protected List<PaymentPreferencesDto> preference;

    public List<PaymentPreferencesDto> getPreferences() {
        return preference;
    }

    public void setPreferences(List<PaymentPreferencesDto> preference) {
        this.preference = preference;
    }

    @Override
    public String toString() {
        return "GetAccountPaymentPreferencesResponse{" +
                "preference=" + preference +
                '}';
    }
}
