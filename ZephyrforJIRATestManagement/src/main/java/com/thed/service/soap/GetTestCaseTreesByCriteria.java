
package com.thed.service.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getTestCaseTreesByCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getTestCaseTreesByCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="searchCriterias" type="{http://soap.service.thed.com/}remoteCriteria" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="returnAllDataFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "getTestCaseTreesByCriteria", propOrder = {
    "searchCriterias",
    "returnAllDataFlag",
    "token"
})
public class GetTestCaseTreesByCriteria {

    protected List<RemoteCriteria> searchCriterias;
    protected Boolean returnAllDataFlag;
    protected String token;

    /**
     * Gets the value of the searchCriterias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchCriterias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchCriterias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RemoteCriteria }
     * 
     * 
     */
    public List<RemoteCriteria> getSearchCriterias() {
        if (searchCriterias == null) {
            searchCriterias = new ArrayList<RemoteCriteria>();
        }
        return this.searchCriterias;
    }

    /**
     * Gets the value of the returnAllDataFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnAllDataFlag() {
        return returnAllDataFlag;
    }

    /**
     * Sets the value of the returnAllDataFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnAllDataFlag(Boolean value) {
        this.returnAllDataFlag = value;
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
