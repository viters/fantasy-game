
package com.ls.soa.game.fantasy.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for categoryDictionaryDTO complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="categoryDictionaryDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categories" type="{http://soap.service.fantasy.game.soa.ls.com/}categoryDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="categoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryParam1Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="elementName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="elementParam1Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="elementParam2Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="elementParam3Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="elementParam4Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="elements" type="{http://soap.service.fantasy.game.soa.ls.com/}elementDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categoryDictionaryDTO", propOrder = {
        "categories",
        "categoryName",
        "categoryParam1Name",
        "elementName",
        "elementParam1Name",
        "elementParam2Name",
        "elementParam3Name",
        "elementParam4Name",
        "elements",
        "id"
})
public class CategoryDictionaryDTO {

    @XmlElement(nillable = true)
    protected List<CategoryDTO> categories;
    protected String categoryName;
    protected String categoryParam1Name;
    protected String elementName;
    protected String elementParam1Name;
    protected String elementParam2Name;
    protected String elementParam3Name;
    protected String elementParam4Name;
    @XmlElement(nillable = true)
    protected List<ElementDTO> elements;
    protected long id;

    /**
     * Gets the value of the categories property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categories property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategories().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CategoryDTO }
     */
    public List<CategoryDTO> getCategories() {
        if (categories == null) {
            categories = new ArrayList<CategoryDTO>();
        }
        return this.categories;
    }

    /**
     * Gets the value of the categoryName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Gets the value of the categoryParam1Name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCategoryParam1Name() {
        return categoryParam1Name;
    }

    /**
     * Sets the value of the categoryParam1Name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCategoryParam1Name(String value) {
        this.categoryParam1Name = value;
    }

    /**
     * Gets the value of the elementName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * Sets the value of the elementName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setElementName(String value) {
        this.elementName = value;
    }

    /**
     * Gets the value of the elementParam1Name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getElementParam1Name() {
        return elementParam1Name;
    }

    /**
     * Sets the value of the elementParam1Name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setElementParam1Name(String value) {
        this.elementParam1Name = value;
    }

    /**
     * Gets the value of the elementParam2Name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getElementParam2Name() {
        return elementParam2Name;
    }

    /**
     * Sets the value of the elementParam2Name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setElementParam2Name(String value) {
        this.elementParam2Name = value;
    }

    /**
     * Gets the value of the elementParam3Name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getElementParam3Name() {
        return elementParam3Name;
    }

    /**
     * Sets the value of the elementParam3Name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setElementParam3Name(String value) {
        this.elementParam3Name = value;
    }

    /**
     * Gets the value of the elementParam4Name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getElementParam4Name() {
        return elementParam4Name;
    }

    /**
     * Sets the value of the elementParam4Name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setElementParam4Name(String value) {
        this.elementParam4Name = value;
    }

    /**
     * Gets the value of the elements property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elements property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElements().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ElementDTO }
     */
    public List<ElementDTO> getElements() {
        if (elements == null) {
            elements = new ArrayList<ElementDTO>();
        }
        return this.elements;
    }

    /**
     * Gets the value of the id property.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     */
    public void setId(long value) {
        this.id = value;
    }

}
