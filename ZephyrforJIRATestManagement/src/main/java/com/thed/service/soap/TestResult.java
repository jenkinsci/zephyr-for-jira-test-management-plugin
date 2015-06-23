
package com.thed.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for testResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="testResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attachmentLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defect" type="{http://soap.service.thed.com/}defect" minOccurs="0"/>
 *         &lt;element name="executionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="executionNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="executionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="releaseTestScheduleId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="testerId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "testResult", propOrder = {
    "attachmentLocation",
    "defect",
    "executionDate",
    "executionNotes",
    "executionStatus",
    "id",
    "releaseTestScheduleId",
    "status",
    "testerId"
})
public class TestResult {

    protected String attachmentLocation;
    protected Defect defect;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar executionDate;
    protected String executionNotes;
    protected String executionStatus;
    protected long id;
    protected Long releaseTestScheduleId;
    protected String status;
    protected Long testerId;

    /**
     * Gets the value of the attachmentLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    /**
     * Sets the value of the attachmentLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachmentLocation(String value) {
        this.attachmentLocation = value;
    }

    /**
     * Gets the value of the defect property.
     * 
     * @return
     *     possible object is
     *     {@link Defect }
     *     
     */
    public Defect getDefect() {
        return defect;
    }

    /**
     * Sets the value of the defect property.
     * 
     * @param value
     *     allowed object is
     *     {@link Defect }
     *     
     */
    public void setDefect(Defect value) {
        this.defect = value;
    }

    /**
     * Gets the value of the executionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExecutionDate() {
        return executionDate;
    }

    /**
     * Sets the value of the executionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExecutionDate(XMLGregorianCalendar value) {
        this.executionDate = value;
    }

    /**
     * Gets the value of the executionNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutionNotes() {
        return executionNotes;
    }

    /**
     * Sets the value of the executionNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutionNotes(String value) {
        this.executionNotes = value;
    }

    /**
     * Gets the value of the executionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutionStatus() {
        return executionStatus;
    }

    /**
     * Sets the value of the executionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutionStatus(String value) {
        this.executionStatus = value;
    }

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
     * Gets the value of the releaseTestScheduleId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReleaseTestScheduleId() {
        return releaseTestScheduleId;
    }

    /**
     * Sets the value of the releaseTestScheduleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReleaseTestScheduleId(Long value) {
        this.releaseTestScheduleId = value;
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
     * Gets the value of the testerId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTesterId() {
        return testerId;
    }

    /**
     * Sets the value of the testerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTesterId(Long value) {
        this.testerId = value;
    }

}
