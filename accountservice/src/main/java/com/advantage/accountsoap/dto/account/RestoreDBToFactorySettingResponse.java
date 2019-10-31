
package com.advantage.accountsoap.dto.account;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="httpStatus" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="success" type="{com.advantage.online.store.accountservice}responseReason" form="qualified"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "httpStatus",
    "success",
    "reason"
})
@XmlRootElement(name = "RestoreDBToFactorySettingResponse", namespace = "com.advantage.online.store.accountservice")
public class RestoreDBToFactorySettingResponse  {

    @XmlElement(namespace = "com.advantage.online.store.accountservice", required = true)
    protected String httpStatus;
    @XmlElement(namespace = "com.advantage.online.store.accountservice")
    protected boolean success;
    @XmlElement(namespace = "com.advantage.online.store.accountservice", required = true)
    protected String reason;

//    public RestoreDBToFactorySettingResponse(String httpStatus, boolean success, String reason) {
//        this.httpStatus = httpStatus;
//        this.success = success;
//        this.reason = reason;
//    }

    /**
     * Gets the value of the httpStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHttpStatus() {
        return httpStatus;
    }

    /**
     * Sets the value of the httpStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHttpStatus(String value) {
        this.httpStatus = value;
    }

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
