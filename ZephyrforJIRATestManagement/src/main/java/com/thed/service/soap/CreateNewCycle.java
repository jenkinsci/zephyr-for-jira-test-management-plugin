
package com.thed.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createNewCycle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createNewCycle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remoteCycle" type="{http://soap.service.thed.com/}remoteCycle" minOccurs="0"/>
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
@XmlType(name = "createNewCycle", propOrder = {
    "remoteCycle",
    "token"
})
public class CreateNewCycle {

    protected RemoteCycle remoteCycle;
    protected String token;

    /**
     * Gets the value of the remoteCycle property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteCycle }
     *     
     */
    public RemoteCycle getRemoteCycle() {
        return remoteCycle;
    }

    /**
     * Sets the value of the remoteCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteCycle }
     *     
     */
    public void setRemoteCycle(RemoteCycle value) {
        this.remoteCycle = value;
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
