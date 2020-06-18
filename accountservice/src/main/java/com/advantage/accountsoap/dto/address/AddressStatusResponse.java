package com.advantage.accountsoap.dto.address;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "success",
                "reason",
        })
@XmlRootElement(name = "AddressStatusResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class AddressStatusResponse {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    boolean success;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    String reason;

    public AddressStatusResponse() {
    }

    public AddressStatusResponse(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "AddressStatusResponse{" +
                "success=" + success +
                ", reason='" + reason + '\'' +
                '}';
    }
}
