package com.advantage.accountrest.AccountserviceClient;

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.payment.PaymentPreferencesStatusResponse;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        propOrder = {
                "response"
        })
@XmlRootElement(name = "PaymentPreferencesStatusResponse")
public class AddMasterCreditMethodResponse {
    @XmlElement(name = "StatusMessage", required = true)
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
