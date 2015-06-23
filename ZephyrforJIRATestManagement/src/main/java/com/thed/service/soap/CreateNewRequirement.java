
package com.thed.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createNewRequirement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createNewRequirement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remoteRequirement" type="{http://soap.service.thed.com/}remoteRequirement" minOccurs="0"/>
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
@XmlType(name = "createNewRequirement", propOrder = {
    "remoteRequirement",
    "token"
})
public class CreateNewRequirement {

    protected RemoteRequirement remoteRequirement;
    protected String token;

    /**
     * Gets the value of the remoteRequirement property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteRequirement }
     *     
     */
    public RemoteRequirement getRemoteRequirement() {
        return remoteRequirement;
    }

    /**
     * Sets the value of the remoteRequirement property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteRequirement }
     *     
     */
    public void setRemoteRequirement(RemoteRequirement value) {
        this.remoteRequirement = value;
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
