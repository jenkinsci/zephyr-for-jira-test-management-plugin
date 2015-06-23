
package com.thed.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createNewTestcaseTree complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createNewTestcaseTree">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remoteRepositoryTree" type="{http://soap.service.thed.com/}remoteRepositoryTree" minOccurs="0"/>
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
@XmlType(name = "createNewTestcaseTree", propOrder = {
    "remoteRepositoryTree",
    "token"
})
public class CreateNewTestcaseTree {

    protected RemoteRepositoryTree remoteRepositoryTree;
    protected String token;

    /**
     * Gets the value of the remoteRepositoryTree property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteRepositoryTree }
     *     
     */
    public RemoteRepositoryTree getRemoteRepositoryTree() {
        return remoteRepositoryTree;
    }

    /**
     * Sets the value of the remoteRepositoryTree property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteRepositoryTree }
     *     
     */
    public void setRemoteRepositoryTree(RemoteRepositoryTree value) {
        this.remoteRepositoryTree = value;
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
