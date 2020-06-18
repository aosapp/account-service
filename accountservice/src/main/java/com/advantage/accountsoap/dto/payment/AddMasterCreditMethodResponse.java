package com.advantage.accountsoap.dto.payment;

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "response"
        })
@XmlRootElement(name = "PaymentPreferencesStatusResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class AddMasterCreditMethodResponse {
    @XmlElement(name = "StatusMessage", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private PaymentPreferencesStatusResponse response;

    public AddMasterCreditMethodResponse() {
    }

    public AddMasterCreditMethodResponse(PaymentPreferencesStatusResponse response) {
        this.response = response;
    }

    public PaymentPreferencesStatusResponse getResponse() {
        return response;
    }

    public void setResponse(PaymentPreferencesStatusResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "AddMasterCreditMethodResponse{" +
                "response=" + response +
                '}';
    }
}
