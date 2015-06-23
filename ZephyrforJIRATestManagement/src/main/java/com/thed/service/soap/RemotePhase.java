
package com.thed.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for remotePhase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="remotePhase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="remoteRepository" type="{http://soap.service.thed.com/}remoteNameValue" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="freeForm" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="remoteCycle" type="{http://soap.service.thed.com/}remoteNameValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "remotePhase", propOrder = {
    "id",
    "remoteRepository",
    "startDate",
    "endDate",
    "freeForm",
    "remoteCycle"
})
public class RemotePhase {

    protected Long id;
    protected RemoteNameValue remoteRepository;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected Boolean freeForm;
    protected RemoteNameValue remoteCycle;

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
     * Gets the value of the remoteRepository property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteNameValue }
     *     
     */
    public RemoteNameValue getRemoteRepository() {
        return remoteRepository;
    }

    /**
     * Sets the value of the remoteRepository property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteNameValue }
     *     
     */
    public void setRemoteRepository(RemoteNameValue value) {
        this.remoteRepository = value;
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
     * Gets the value of the freeForm property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFreeForm() {
        return freeForm;
    }

    /**
     * Sets the value of the freeForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFreeForm(Boolean value) {
        this.freeForm = value;
    }

    /**
     * Gets the value of the remoteCycle property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteNameValue }
     *     
     */
    public RemoteNameValue getRemoteCycle() {
        return remoteCycle;
    }

    /**
     * Sets the value of the remoteCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteNameValue }
     *     
     */
    public void setRemoteCycle(RemoteNameValue value) {
        this.remoteCycle = value;
    }

}
