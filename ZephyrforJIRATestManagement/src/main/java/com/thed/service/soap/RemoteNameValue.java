
package com.thed.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for remoteNameValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="remoteNameValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remoteData" type="{http://soap.service.thed.com/}remoteData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "remoteNameValue", propOrder = {
    "remoteData"
})
public class RemoteNameValue {

    protected RemoteData remoteData;

    /**
     * Gets the value of the remoteData property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteData }
     *     
     */
    public RemoteData getRemoteData() {
        return remoteData;
    }

    /**
     * Sets the value of the remoteData property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteData }
     *     
     */
    public void setRemoteData(RemoteData value) {
        this.remoteData = value;
    }

}
