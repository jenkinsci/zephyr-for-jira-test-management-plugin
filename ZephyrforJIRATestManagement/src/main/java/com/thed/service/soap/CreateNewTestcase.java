
package com.thed.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createNewTestcase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createNewTestcase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remoteRepositoryTreeTestcase" type="{http://soap.service.thed.com/}remoteRepositoryTreeTestcase" minOccurs="0"/>
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
@XmlType(name = "createNewTestcase", propOrder = {
    "remoteRepositoryTreeTestcase",
    "token"
})
public class CreateNewTestcase {

    protected RemoteRepositoryTreeTestcase remoteRepositoryTreeTestcase;
    protected String token;

    /**
     * Gets the value of the remoteRepositoryTreeTestcase property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteRepositoryTreeTestcase }
     *     
     */
    public RemoteRepositoryTreeTestcase getRemoteRepositoryTreeTestcase() {
        return remoteRepositoryTreeTestcase;
    }

    /**
     * Sets the value of the remoteRepositoryTreeTestcase property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteRepositoryTreeTestcase }
     *     
     */
    public void setRemoteRepositoryTreeTestcase(RemoteRepositoryTreeTestcase value) {
        this.remoteRepositoryTreeTestcase = value;
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
