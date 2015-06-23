
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
 * <p>Java class for remoteReleaseTestSchedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="remoteReleaseTestSchedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="testScheduleId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="assignmentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="estimatedTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="testerId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="remoteRepositoryTestcaseId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="remoteTestcaseId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="cyclePhaseId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="lastTestResult" type="{http://soap.service.thed.com/}testResult" minOccurs="0"/>
 *         &lt;element name="attachmentCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="scriptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scriptId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scriptPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remoteFieldValues" type="{http://soap.service.thed.com/}remoteFieldValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="actualTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="defects" type="{http://soap.service.thed.com/}remoteDefect" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "remoteReleaseTestSchedule", propOrder = {
    "testScheduleId",
    "assignmentDate",
    "estimatedTime",
    "comment",
    "testerId",
    "remoteRepositoryTestcaseId",
    "remoteTestcaseId",
    "cyclePhaseId",
    "lastTestResult",
    "attachmentCount",
    "scriptName",
    "scriptId",
    "scriptPath",
    "remoteFieldValues",
    "actualTime",
    "defects"
})
public class RemoteReleaseTestSchedule {

    protected long testScheduleId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar assignmentDate;
    protected Long estimatedTime;
    protected String comment;
    protected Long testerId;
    protected Long remoteRepositoryTestcaseId;
    protected Long remoteTestcaseId;
    protected Long cyclePhaseId;
    protected TestResult lastTestResult;
    protected Integer attachmentCount;
    protected String scriptName;
    protected String scriptId;
    protected String scriptPath;
    @XmlElement(nillable = true)
    protected List<RemoteFieldValue> remoteFieldValues;
    protected Integer actualTime;
    @XmlElement(nillable = true)
    protected List<RemoteDefect> defects;

    /**
     * Gets the value of the testScheduleId property.
     * 
     */
    public long getTestScheduleId() {
        return testScheduleId;
    }

    /**
     * Sets the value of the testScheduleId property.
     * 
     */
    public void setTestScheduleId(long value) {
        this.testScheduleId = value;
    }

    /**
     * Gets the value of the assignmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAssignmentDate() {
        return assignmentDate;
    }

    /**
     * Sets the value of the assignmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAssignmentDate(XMLGregorianCalendar value) {
        this.assignmentDate = value;
    }

    /**
     * Gets the value of the estimatedTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * Sets the value of the estimatedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstimatedTime(Long value) {
        this.estimatedTime = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
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

    /**
     * Gets the value of the remoteRepositoryTestcaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRemoteRepositoryTestcaseId() {
        return remoteRepositoryTestcaseId;
    }

    /**
     * Sets the value of the remoteRepositoryTestcaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRemoteRepositoryTestcaseId(Long value) {
        this.remoteRepositoryTestcaseId = value;
    }

    /**
     * Gets the value of the remoteTestcaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRemoteTestcaseId() {
        return remoteTestcaseId;
    }

    /**
     * Sets the value of the remoteTestcaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRemoteTestcaseId(Long value) {
        this.remoteTestcaseId = value;
    }

    /**
     * Gets the value of the cyclePhaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCyclePhaseId() {
        return cyclePhaseId;
    }

    /**
     * Sets the value of the cyclePhaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCyclePhaseId(Long value) {
        this.cyclePhaseId = value;
    }

    /**
     * Gets the value of the lastTestResult property.
     * 
     * @return
     *     possible object is
     *     {@link TestResult }
     *     
     */
    public TestResult getLastTestResult() {
        return lastTestResult;
    }

    /**
     * Sets the value of the lastTestResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestResult }
     *     
     */
    public void setLastTestResult(TestResult value) {
        this.lastTestResult = value;
    }

    /**
     * Gets the value of the attachmentCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAttachmentCount() {
        return attachmentCount;
    }

    /**
     * Sets the value of the attachmentCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAttachmentCount(Integer value) {
        this.attachmentCount = value;
    }

    /**
     * Gets the value of the scriptName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScriptName() {
        return scriptName;
    }

    /**
     * Sets the value of the scriptName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScriptName(String value) {
        this.scriptName = value;
    }

    /**
     * Gets the value of the scriptId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScriptId() {
        return scriptId;
    }

    /**
     * Sets the value of the scriptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScriptId(String value) {
        this.scriptId = value;
    }

    /**
     * Gets the value of the scriptPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScriptPath() {
        return scriptPath;
    }

    /**
     * Sets the value of the scriptPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScriptPath(String value) {
        this.scriptPath = value;
    }

    /**
     * Gets the value of the remoteFieldValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the remoteFieldValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemoteFieldValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RemoteFieldValue }
     * 
     * 
     */
    public List<RemoteFieldValue> getRemoteFieldValues() {
        if (remoteFieldValues == null) {
            remoteFieldValues = new ArrayList<RemoteFieldValue>();
        }
        return this.remoteFieldValues;
    }

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
     * Gets the value of the defects property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the defects property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDefects().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RemoteDefect }
     * 
     * 
     */
    public List<RemoteDefect> getDefects() {
        if (defects == null) {
            defects = new ArrayList<RemoteDefect>();
        }
        return this.defects;
    }

}
