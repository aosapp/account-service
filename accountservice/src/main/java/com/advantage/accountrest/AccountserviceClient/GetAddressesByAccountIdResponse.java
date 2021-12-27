
package com.advantage.accountrest.AccountserviceClient;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="ShippingAddress" maxOccurs="unbounded" form="qualified"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" form="qualified"/&gt;
 *                   &lt;element name="addressLine1" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
 *                   &lt;element name="addressLine2" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
 *                   &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
 *                   &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
 *                   &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
 *                   &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
 *                   &lt;element name="accountId" type="{http://www.w3.org/2001/XMLSchema}long" form="qualified"/&gt;
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
    "shippingAddress"
})
@XmlRootElement(name = "GetAddressesByAccountIdResponse")
public class GetAddressesByAccountIdResponse {

    @XmlElement(name = "ShippingAddress", required = true)
    protected List<GetAddressesByAccountIdResponse.ShippingAddress> shippingAddress;

    /**
     * Gets the value of the shippingAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the shippingAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShippingAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetAddressesByAccountIdResponse.ShippingAddress }
     * 
     * 
     */
    public List<GetAddressesByAccountIdResponse.ShippingAddress> getShippingAddress() {
        if (shippingAddress == null) {
            shippingAddress = new ArrayList<GetAddressesByAccountIdResponse.ShippingAddress>();
        }
        return this.shippingAddress;
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
     *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" form="qualified"/&gt;
     *         &lt;element name="addressLine1" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
     *         &lt;element name="addressLine2" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
     *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
     *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
     *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
     *         &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
     *         &lt;element name="accountId" type="{http://www.w3.org/2001/XMLSchema}long" form="qualified"/&gt;
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
        "id",
        "addressLine1",
        "addressLine2",
        "city",
        "country",
        "state",
        "postalCode",
        "accountId"
    })
    public static class ShippingAddress {

        @XmlElement(required = true, type = Long.class, nillable = true)
        protected Long id;
        @XmlElement(required = true)
        protected String addressLine1;
        @XmlElement(required = true)
        protected String addressLine2;
        @XmlElement(required = true)
        protected String city;
        @XmlElement(required = true)
        protected String country;
        @XmlElement(required = true)
        protected String state;
        @XmlElement(required = true)
        protected String postalCode;
        protected long accountId;

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setId(Long value) {
            this.id = value;
        }

        /**
         * Gets the value of the addressLine1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAddressLine1() {
            return addressLine1;
        }

        /**
         * Sets the value of the addressLine1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAddressLine1(String value) {
            this.addressLine1 = value;
        }

        /**
         * Gets the value of the addressLine2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAddressLine2() {
            return addressLine2;
        }

        /**
         * Sets the value of the addressLine2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAddressLine2(String value) {
            this.addressLine2 = value;
        }

        /**
         * Gets the value of the city property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCity() {
            return city;
        }

        /**
         * Sets the value of the city property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCity(String value) {
            this.city = value;
        }

        /**
         * Gets the value of the country property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCountry() {
            return country;
        }

        /**
         * Sets the value of the country property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCountry(String value) {
            this.country = value;
        }

        /**
         * Gets the value of the state property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getState() {
            return state;
        }

        /**
         * Sets the value of the state property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setState(String value) {
            this.state = value;
        }

        /**
         * Gets the value of the postalCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPostalCode() {
            return postalCode;
        }

        /**
         * Sets the value of the postalCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPostalCode(String value) {
            this.postalCode = value;
        }

        /**
         * Gets the value of the accountId property.
         * 
         */
        public long getAccountId() {
            return accountId;
        }

        /**
         * Sets the value of the accountId property.
         * 
         */
        public void setAccountId(long value) {
            this.accountId = value;
        }

    }

}
