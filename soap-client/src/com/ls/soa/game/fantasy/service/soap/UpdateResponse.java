
package com.ls.soa.game.fantasy.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateResponse complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="updateResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://soap.service.fantasy.game.soa.ls.com/}elementDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateResponse", propOrder = {
        "_return"
})
public class UpdateResponse {

    @XmlElement(name = "return")
    protected ElementDTO _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is
     * {@link ElementDTO }
     */
    public ElementDTO getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value allowed object is
     *              {@link ElementDTO }
     */
    public void setReturn(ElementDTO value) {
        this._return = value;
    }

}
