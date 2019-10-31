package com.advantage.accountsoap.dto.payment;

import com.advantage.accountsoap.config.WebServiceConfig;
import com.advantage.accountsoap.dto.account.AccountStatusResponse;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "response"
        })
@XmlRootElement(name = "DeletePaymentPreferenceResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class DeletePaymentPreferenceResponse {
    @XmlElement(name = "StatusMessage", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private AccountStatusResponse response;

    public DeletePaymentPreferenceResponse() {
    }

    public DeletePaymentPreferenceResponse(AccountStatusResponse response) {
        this.response = response;
    }

    public AccountStatusResponse getResponse() {
        return response;
    }

    public void setResponse(AccountStatusResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "DeletePaymentPreferenceResponse{" +
                "response=" + response +
                '}';
    }
}
