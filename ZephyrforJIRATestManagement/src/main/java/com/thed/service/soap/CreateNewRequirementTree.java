
package com.thed.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createNewRequirementTree complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createNewRequirementTree">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remoteRequirementTree" type="{http://soap.service.thed.com/}remoteRequirementTree" minOccurs="0"/>
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
@XmlType(name = "createNewRequirementTree", propOrder = {
    "remoteRequirementTree",
    "token"
})
public class CreateNewRequirementTree {

    protected RemoteRequirementTree remoteRequirementTree;
    protected String token;

    /**
     * Gets the value of the remoteRequirementTree property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteRequirementTree }
     *     
     */
    public RemoteRequirementTree getRemoteRequirementTree() {
        return remoteRequirementTree;
    }

    /**
     * Sets the value of the remoteRequirementTree property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteRequirementTree }
     *     
     */
    public void setRemoteRequirementTree(RemoteRequirementTree value) {
        this.remoteRequirementTree = value;
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
