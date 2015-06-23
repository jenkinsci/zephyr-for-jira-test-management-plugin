
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
 * <p>Java class for remoteTestResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="remoteTestResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actualTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="executionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="executionNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="executionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="releaseTestScheduleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="testStepExecutions" type="{http://soap.service.thed.com/}remoteFieldValue" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "remoteTestResult", propOrder = {
    "actualTime",
    "executionDate",
    "executionNotes",
    "executionStatus",
    "id",
    "releaseTestScheduleId",
    "testStepExecutions",
    "testerId"
})
public class RemoteTestResult {

    protected Integer actualTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar executionDate;
    protected String executionNotes;
    protected String executionStatus;
    protected Long id;
    protected String releaseTestScheduleId;
    @XmlElement(nillable = true)
    protected List<RemoteFieldValue> testStepExecutions;
    protected Long testerId;

    /**
     * Gets the value of the actualTime property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getActualTime() {
        return actualTime;
    }

    /**
     * Sets the value of the actualTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setActualTime(Integer value) {
        this.actualTime = value;
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
     * Gets the value of the releaseTestScheduleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseTestScheduleId() {
        return releaseTestScheduleId;
    }

    /**
     * Sets the value of the releaseTestScheduleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseTestScheduleId(String value) {
        this.releaseTestScheduleId = value;
    }

    /**
     * Gets the value of the testStepExecutions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testStepExecutions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestStepExecutions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RemoteFieldValue }
     * 
     * 
     */
    public List<RemoteFieldValue> getTestStepExecutions() {
        if (testStepExecutions == null) {
            testStepExecutions = new ArrayList<RemoteFieldValue>();
        }
        return this.testStepExecutions;
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
