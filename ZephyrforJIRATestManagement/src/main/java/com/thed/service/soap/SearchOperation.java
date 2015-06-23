
package com.thed.service.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchOperation.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="searchOperation">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LESS_THAN"/>
 *     &lt;enumeration value="EQUALS"/>
 *     &lt;enumeration value="GREATER_THAN"/>
 *     &lt;enumeration value="LIKE"/>
 *     &lt;enumeration value="BETWEEN"/>
 *     &lt;enumeration value="IS_NULL"/>
 *     &lt;enumeration value="NOT_NULL"/>
 *     &lt;enumeration value="IN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "searchOperation")
@XmlEnum
public enum SearchOperation {

    LESS_THAN,
    EQUALS,
    GREATER_THAN,
    LIKE,
    BETWEEN,
    IS_NULL,
    NOT_NULL,
    IN;

    public String value() {
        return name();
    }

    public static SearchOperation fromValue(String v) {
        return valueOf(v);
    }

}
