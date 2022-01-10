
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
 *                   &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long" form="qualified"/&gt;
 *                   &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
 *                   &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
 *                   &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
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
@XmlRootElement(name = "AccountLogoutResponse")
public class AccountLogoutResponse {

    @XmlElement(name = "StatusMessage", required = true)
    protected AccountLogoutResponse.StatusMessage statusMessage;

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link AccountLogoutResponse.StatusMessage }
     *     
     */
    public AccountLogoutResponse.StatusMessage getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountLogoutResponse.StatusMessage }
     *     
     */
    public void setStatusMessage(AccountLogoutResponse.StatusMessage value) {
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
     *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long" form="qualified"/&gt;
     *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
     *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
     *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
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
        "userId",
        "reason",
        "token",
        "sessionId"
    })
    public static class StatusMessage {

        protected boolean success;
        protected long userId;
        @XmlElement(required = true)
        protected String reason;
        @XmlElement(required = true)
        protected String token;
        @XmlElement(required = true)
        protected String sessionId;

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
         * Gets the value of the userId property.
         * 
         */
        public long getUserId() {
            return userId;
        }

        /**
         * Sets the value of the userId property.
         * 
         */
        public void setUserId(long value) {
            this.userId = value;
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

        /**
         * Gets the value of the token property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getToken() {
            return token;
        }

        /**
         * Sets the value of the token property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setToken(String value) {
            this.token = value;
        }

        /**
         * Gets the value of the sessionId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSessionId() {
            return sessionId;
        }

        /**
         * Sets the value of the sessionId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSessionId(String value) {
            this.sessionId = value;
        }

    }

}
