
package com.thed.service.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for remoteRepositoryTree complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="remoteRepositoryTree">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parent" type="{http://soap.service.thed.com/}remoteRepositoryTree" minOccurs="0"/>
 *         &lt;element name="categories" type="{http://soap.service.thed.com/}remoteRepositoryChild" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remoteRepositoryUser" type="{http://soap.service.thed.com/}remoteNameValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="releaseId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="linkedTCRCatalogTreeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "remoteRepositoryTree", propOrder = {
    "id",
    "type",
    "name",
    "description",
    "parent",
    "categories",
    "remoteRepositoryUser",
    "releaseId",
    "linkedTCRCatalogTreeId"
})
public class RemoteRepositoryTree {

    protected Long id;
    protected String type;
    protected String name;
    protected String description;
    protected RemoteRepositoryTree parent;
    @XmlElement(nillable = true)
    protected List<RemoteRepositoryChild> categories;
    @XmlElement(nillable = true)
    protected List<RemoteNameValue> remoteRepositoryUser;
    protected Long releaseId;
    protected Long linkedTCRCatalogTreeId;

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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteRepositoryTree }
     *     
     */
    public RemoteRepositoryTree getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteRepositoryTree }
     *     
     */
    public void setParent(RemoteRepositoryTree value) {
        this.parent = value;
    }

    /**
     * Gets the value of the categories property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categories property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategories().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RemoteRepositoryChild }
     * 
     * 
     */
    public List<RemoteRepositoryChild> getCategories() {
        if (categories == null) {
            categories = new ArrayList<RemoteRepositoryChild>();
        }
        return this.categories;
    }

    /**
     * Gets the value of the remoteRepositoryUser property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the remoteRepositoryUser property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemoteRepositoryUser().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RemoteNameValue }
     * 
     * 
     */
    public List<RemoteNameValue> getRemoteRepositoryUser() {
        if (remoteRepositoryUser == null) {
            remoteRepositoryUser = new ArrayList<RemoteNameValue>();
        }
        return this.remoteRepositoryUser;
    }

    /**
     * Gets the value of the releaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReleaseId() {
        return releaseId;
    }

    /**
     * Sets the value of the releaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReleaseId(Long value) {
        this.releaseId = value;
    }

    /**
     * Gets the value of the linkedTCRCatalogTreeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLinkedTCRCatalogTreeId() {
        return linkedTCRCatalogTreeId;
    }

    /**
     * Sets the value of the linkedTCRCatalogTreeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLinkedTCRCatalogTreeId(Long value) {
        this.linkedTCRCatalogTreeId = value;
    }

}
