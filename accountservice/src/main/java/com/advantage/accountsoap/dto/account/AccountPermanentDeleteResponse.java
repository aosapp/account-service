package com.advantage.accountsoap.dto.account;
//Modify this class with resources/accountservice.xsd

import com.advantage.accountsoap.config.WebServiceConfig;

import javax.xml.bind.annotation.*;

/**
 * @author Tamir Shina 13.8.2018.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",
        namespace = WebServiceConfig.NAMESPACE_URI,
        propOrder = {
                "response"
        })
@XmlRootElement(name = "AccountPermanentDeleteResponse", namespace = WebServiceConfig.NAMESPACE_URI)
public class AccountPermanentDeleteResponse {


    @XmlElement(name = "StatusMessage", namespace = WebServiceConfig.NAMESPACE_URI, required = true)
    private AccountStatusResponse response;

    /**
     * Gets the value of the statusMessage property.
     *
     * @return
     *     possible object is
     *     {@link AccountStatusResponse }
     *
     */
    public AccountStatusResponse getStatusMessage() {
        return response;
    }

    /**
     * Sets the value of the statusMessage property.
     *
     * @param value
     *     allowed object is
     *     {@link AccountStatusResponse }
     *
     */
    public void setStatusMessage(AccountStatusResponse value) {
        this.response = value;
    }




}
