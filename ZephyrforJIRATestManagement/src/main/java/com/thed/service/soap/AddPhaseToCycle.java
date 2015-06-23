
package com.thed.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addPhaseToCycle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addPhaseToCycle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remotePhase" type="{http://soap.service.thed.com/}remotePhase" minOccurs="0"/>
 *         &lt;element name="bulkAssign" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addPhaseToCycle", propOrder = {
    "remotePhase",
    "bulkAssign",
    "token"
})
public class AddPhaseToCycle {

    protected RemotePhase remotePhase;
    protected Integer bulkAssign;
    protected String token;

    /**
     * Gets the value of the remotePhase property.
     * 
     * @return
     *     possible object is
     *     {@link RemotePhase }
     *     
     */
    public RemotePhase getRemotePhase() {
        return remotePhase;
    }

    /**
     * Sets the value of the remotePhase property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemotePhase }
     *     
     */
    public void setRemotePhase(RemotePhase value) {
        this.remotePhase = value;
    }

    /**
     * Gets the value of the bulkAssign property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBulkAssign() {
        return bulkAssign;
    }

    /**
     * Sets the value of the bulkAssign property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBulkAssign(Integer value) {
        this.bulkAssign = value;
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

}
