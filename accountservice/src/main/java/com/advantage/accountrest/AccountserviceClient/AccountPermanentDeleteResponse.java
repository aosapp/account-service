
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
 *                   &lt;element name="OrderHeaderDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
 *                   &lt;element name="OrderLinesDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
 *                   &lt;element name="ShippingAddressDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
 *                   &lt;element name="PaymentPreferenceDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
 *                   &lt;element name="AccountDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
 *                   &lt;element name="IsSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
 *                   &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
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
@XmlRootElement(name = "AccountPermanentDeleteResponse")
public class AccountPermanentDeleteResponse {

    @XmlElement(name = "StatusMessage", required = true)
    protected AccountPermanentDeleteResponse.StatusMessage statusMessage;

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link AccountPermanentDeleteResponse.StatusMessage }
     *     
     */
    public AccountPermanentDeleteResponse.StatusMessage getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountPermanentDeleteResponse.StatusMessage }
     *     
     */
    public void setStatusMessage(AccountPermanentDeleteResponse.StatusMessage value) {
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
     *         &lt;element name="OrderHeaderDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
     *         &lt;element name="OrderLinesDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
     *         &lt;element name="ShippingAddressDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
     *         &lt;element name="PaymentPreferenceDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
     *         &lt;element name="AccountDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
     *         &lt;element name="IsSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean" form="qualified"/&gt;
     *         &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
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
        "orderHeaderDelete",
        "orderLinesDelete",
        "shippingAddressDelete",
        "paymentPreferenceDelete",
        "accountDelete",
        "isSuccess",
        "reason"
    })
    public static class StatusMessage {

        protected boolean success;
        @XmlElement(name = "OrderHeaderDelete")
        protected boolean orderHeaderDelete;
        @XmlElement(name = "OrderLinesDelete")
        protected boolean orderLinesDelete;
        @XmlElement(name = "ShippingAddressDelete")
        protected boolean shippingAddressDelete;
        @XmlElement(name = "PaymentPreferenceDelete")
        protected boolean paymentPreferenceDelete;
        @XmlElement(name = "AccountDelete")
        protected boolean accountDelete;
        @XmlElement(name = "IsSuccess")
        protected boolean isSuccess;
        @XmlElement(name = "Reason", required = true)
        protected String reason;

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
         * Gets the value of the orderHeaderDelete property.
         * 
         */
        public boolean isOrderHeaderDelete() {
            return orderHeaderDelete;
        }

        /**
         * Sets the value of the orderHeaderDelete property.
         * 
         */
        public void setOrderHeaderDelete(boolean value) {
            this.orderHeaderDelete = value;
        }

        /**
         * Gets the value of the orderLinesDelete property.
         * 
         */
        public boolean isOrderLinesDelete() {
            return orderLinesDelete;
        }

        /**
         * Sets the value of the orderLinesDelete property.
         * 
         */
        public void setOrderLinesDelete(boolean value) {
            this.orderLinesDelete = value;
        }

        /**
         * Gets the value of the shippingAddressDelete property.
         * 
         */
        public boolean isShippingAddressDelete() {
            return shippingAddressDelete;
        }

        /**
         * Sets the value of the shippingAddressDelete property.
         * 
         */
        public void setShippingAddressDelete(boolean value) {
            this.shippingAddressDelete = value;
        }

        /**
         * Gets the value of the paymentPreferenceDelete property.
         * 
         */
        public boolean isPaymentPreferenceDelete() {
            return paymentPreferenceDelete;
        }

        /**
         * Sets the value of the paymentPreferenceDelete property.
         * 
         */
        public void setPaymentPreferenceDelete(boolean value) {
            this.paymentPreferenceDelete = value;
        }

        /**
         * Gets the value of the accountDelete property.
         * 
         */
        public boolean isAccountDelete() {
            return accountDelete;
        }

        /**
         * Sets the value of the accountDelete property.
         * 
         */
        public void setAccountDelete(boolean value) {
            this.accountDelete = value;
        }

        /**
         * Gets the value of the isSuccess property.
         * 
         */
        public boolean isIsSuccess() {
            return isSuccess;
        }

        /**
         * Sets the value of the isSuccess property.
         * 
         */
        public void setIsSuccess(boolean value) {
            this.isSuccess = value;
        }

        /**
         * Gets the value of the reason property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReason() {
            return reason;
        }

        /**
         * Sets the value of the reason property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReason(String value) {
            this.reason = value;
        }

    }

}
