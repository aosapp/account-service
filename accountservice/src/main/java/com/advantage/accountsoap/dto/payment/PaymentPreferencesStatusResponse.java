package com.advantage.accountsoap.dto.payment;

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "success",
                "id",
                "reason"
        })
@XmlRootElement(name = "PaymentPreferencesStatusResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class PaymentPreferencesStatusResponse {
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    boolean success;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    long id;
    @XmlElement(namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    String reason;

    public PaymentPreferencesStatusResponse() {
    }

    /**
     * @param success
     * @param reason
     * @param id
     */
    public PaymentPreferencesStatusResponse(boolean success, String reason, long id) {
        this.setSuccess(success);
        this.setId(id);
        this.setReason(reason);
    }

    /**
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "PaymentPreferencesStatusResponse{" +
                "success=" + success +
                ", id=" + id +
                ", reason='" + reason + '\'' +
                '}';
    }
}
