
package com.thed.service.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for remoteProject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="remoteProject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="remoteDepartment" type="{http://soap.service.thed.com/}remoteDepartment" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="showItem" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="newItem" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="externalSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="members" type="{http://soap.service.thed.com/}member" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="accessToDashboardSecured" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="urlFragment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accessLimitedOnlyToTeam" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "remoteProject", propOrder = {
    "id",
    "remoteDepartment",
    "name",
    "description",
    "startDate",
    "endDate",
    "status",
    "showItem",
    "newItem",
    "externalSystem",
    "members",
    "type",
    "accessToDashboardSecured",
    "urlFragment",
    "accessLimitedOnlyToTeam"
})
public class RemoteProject {

    protected long id;
    protected RemoteDepartment remoteDepartment;
    protected String name;
    protected String description;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected String status;
    protected boolean showItem;
    protected boolean newItem;
    protected String externalSystem;
    @XmlElement(nillable = true)
    protected List<Member> members;
    protected int type;
    protected boolean accessToDashboardSecured;
    protected String urlFragment;
    protected boolean accessLimitedOnlyToTeam;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the remoteDepartment property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteDepartment }
     *     
     */
    public RemoteDepartment getRemoteDepartment() {
        return remoteDepartment;
    }

    /**
     * Sets the value of the remoteDepartment property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteDepartment }
     *     
     */
    public void setRemoteDepartment(RemoteDepartment value) {
        this.remoteDepartment = value;
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
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the showItem property.
     * 
     */
    public boolean isShowItem() {
        return showItem;
    }

    /**
     * Sets the value of the showItem property.
     * 
     */
    public void setShowItem(boolean value) {
        this.showItem = value;
    }

    /**
     * Gets the value of the newItem property.
     * 
     */
    public boolean isNewItem() {
        return newItem;
    }

    /**
     * Sets the value of the newItem property.
     * 
     */
    public void setNewItem(boolean value) {
        this.newItem = value;
    }

    /**
     * Gets the value of the externalSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalSystem() {
        return externalSystem;
    }

    /**
     * Sets the value of the externalSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalSystem(String value) {
        this.externalSystem = value;
    }

    /**
     * Gets the value of the members property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the members property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMembers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Member }
     * 
     * 
     */
    public List<Member> getMembers() {
        if (members == null) {
            members = new ArrayList<Member>();
        }
        return this.members;
    }

    /**
     * Gets the value of the type property.
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

    /**
     * Gets the value of the accessToDashboardSecured property.
     * 
     */
    public boolean isAccessToDashboardSecured() {
        return accessToDashboardSecured;
    }

    /**
     * Sets the value of the accessToDashboardSecured property.
     * 
     */
    public void setAccessToDashboardSecured(boolean value) {
        this.accessToDashboardSecured = value;
    }

    /**
     * Gets the value of the urlFragment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlFragment() {
        return urlFragment;
    }

    /**
     * Sets the value of the urlFragment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlFragment(String value) {
        this.urlFragment = value;
    }

    /**
     * Gets the value of the accessLimitedOnlyToTeam property.
     * 
     */
    public boolean isAccessLimitedOnlyToTeam() {
        return accessLimitedOnlyToTeam;
    }

    /**
     * Sets the value of the accessLimitedOnlyToTeam property.
     * 
     */
    public void setAccessLimitedOnlyToTeam(boolean value) {
        this.accessLimitedOnlyToTeam = value;
    }

}
