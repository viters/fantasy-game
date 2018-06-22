
package com.ls.soa.game.fantasy.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for create complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="create">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://soap.service.fantasy.game.soa.ls.com/}categoryDictionaryDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "create", propOrder = {
        "arg0",
        "arg1"
})
public class Create {

    protected String arg0;
    protected CategoryDictionaryDTO arg1;

    /**
     * Gets the value of the arg0 property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setArg0(String value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     *
     * @return possible object is
     * {@link CategoryDictionaryDTO }
     */
    public CategoryDictionaryDTO getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     *
     * @param value allowed object is
     *              {@link CategoryDictionaryDTO }
     */
    public void setArg1(CategoryDictionaryDTO value) {
        this.arg1 = value;
    }

}
