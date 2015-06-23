
package com.thed.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for remoteRepositoryTreeTestcase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="remoteRepositoryTreeTestcase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="remoteRepositoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="testcase" type="{http://soap.service.thed.com/}remoteTestcase" minOccurs="0"/>
 *         &lt;element name="original" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="testSteps" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "remoteRepositoryTreeTestcase", propOrder = {
    "id",
    "remoteRepositoryId",
    "testcase",
    "original",
    "testSteps"
})
public class RemoteRepositoryTreeTestcase {

    protected Long id;
    protected Long remoteRepositoryId;
    protected RemoteTestcase testcase;
    protected boolean original;
    protected String testSteps;

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
     * Gets the value of the remoteRepositoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRemoteRepositoryId() {
        return remoteRepositoryId;
    }

    /**
     * Sets the value of the remoteRepositoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRemoteRepositoryId(Long value) {
        this.remoteRepositoryId = value;
    }

    /**
     * Gets the value of the testcase property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteTestcase }
     *     
     */
    public RemoteTestcase getTestcase() {
        return testcase;
    }

    /**
     * Sets the value of the testcase property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteTestcase }
     *     
     */
    public void setTestcase(RemoteTestcase value) {
        this.testcase = value;
    }

    /**
     * Gets the value of the original property.
     * 
     */
    public boolean isOriginal() {
        return original;
    }

    /**
     * Sets the value of the original property.
     * 
     */
    public void setOriginal(boolean value) {
        this.original = value;
    }

    /**
     * Gets the value of the testSteps property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestSteps() {
        return testSteps;
    }

    /**
     * Sets the value of the testSteps property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestSteps(String value) {
        this.testSteps = value;
    }

}
