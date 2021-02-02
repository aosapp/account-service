
package com.advantage.accountrest.AccountserviceClient;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="StatusMessage" form="qualified"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="success" type="{com.advantage.online.store.accountservice}responseReason" form="qualified"/&gt;
 *                   &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "statusMessage"
})
@XmlRootElement(name = "AddressUpdateResponse")
public class AddressUpdateResponse {

    @XmlElement(name = "StatusMessage", required = true)
    protected AddressUpdateResponse.StatusMessage statusMessage;

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link AddressUpdateResponse.StatusMessage }
     *     
     */
    public AddressUpdateResponse.StatusMessage getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressUpdateResponse.StatusMessage }
     *     
     */
    public void setStatusMessage(AddressUpdateResponse.StatusMessage value) {
        this.statusMessage = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="success" type="{com.advantage.online.store.accountservice}responseReason" form="qualified"/&gt;
     *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "success",
        "reason"
    })
    public static class StatusMessage {

        protected boolean success;
        protected boolean reason;

        /**
         * Gets the value of the success property.
         * 
         */
        public boolean isSuccess() {
            return success;
        }

        /**
         * Sets the value of the success property.
         * 
         */
        public void setSuccess(boolean value) {
            this.success = value;
        }

        /**
         * Gets the value of the reason property.
         * 
         */
        public boolean isReason() {
            return reason;
        }

        /**
         * Sets the value of the reason property.
         * 
         */
        public void setReason(boolean value) {
            this.reason = value;
        }

    }

}
