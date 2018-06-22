
package com.ls.soa.game.fantasy.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for categoryDTO complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="categoryDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authorDTO" type="{http://soap.service.fantasy.game.soa.ls.com/}userDTO" minOccurs="0"/>
 *         &lt;element name="authorId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="categoryDictionaryDTO" type="{http://soap.service.fantasy.game.soa.ls.com/}categoryDictionaryDTO" minOccurs="0"/>
 *         &lt;element name="categoryDictionaryId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="elementDTOList" type="{http://soap.service.fantasy.game.soa.ls.com/}elementDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="param1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categoryDTO", propOrder = {
        "authorDTO",
        "authorId",
        "categoryDictionaryDTO",
        "categoryDictionaryId",
        "elementDTOList",
        "id",
        "param1"
})
public class CategoryDTO {

    protected UserDTO authorDTO;
    protected long authorId;
    protected CategoryDictionaryDTO categoryDictionaryDTO;
    protected long categoryDictionaryId;
    @XmlElement(nillable = true)
    protected List<ElementDTO> elementDTOList;
    protected long id;
    protected int param1;

    /**
     * Gets the value of the authorDTO property.
     *
     * @return possible object is
     * {@link UserDTO }
     */
    public UserDTO getAuthorDTO() {
        return authorDTO;
    }

    /**
     * Sets the value of the authorDTO property.
     *
     * @param value allowed object is
     *              {@link UserDTO }
     */
    public void setAuthorDTO(UserDTO value) {
        this.authorDTO = value;
    }

    /**
     * Gets the value of the authorId property.
     */
    public long getAuthorId() {
        return authorId;
    }

    /**
     * Sets the value of the authorId property.
     */
    public void setAuthorId(long value) {
        this.authorId = value;
    }

    /**
     * Gets the value of the categoryDictionaryDTO property.
     *
     * @return possible object is
     * {@link CategoryDictionaryDTO }
     */
    public CategoryDictionaryDTO getCategoryDictionaryDTO() {
        return categoryDictionaryDTO;
    }

    /**
     * Sets the value of the categoryDictionaryDTO property.
     *
     * @param value allowed object is
     *              {@link CategoryDictionaryDTO }
     */
    public void setCategoryDictionaryDTO(CategoryDictionaryDTO value) {
        this.categoryDictionaryDTO = value;
    }

    /**
     * Gets the value of the categoryDictionaryId property.
     */
    public long getCategoryDictionaryId() {
        return categoryDictionaryId;
    }

    /**
     * Sets the value of the categoryDictionaryId property.
     */
    public void setCategoryDictionaryId(long value) {
        this.categoryDictionaryId = value;
    }

    /**
     * Gets the value of the elementDTOList property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elementDTOList property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElementDTOList().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ElementDTO }
     */
    public List<ElementDTO> getElementDTOList() {
        if (elementDTOList == null) {
            elementDTOList = new ArrayList<ElementDTO>();
        }
        return this.elementDTOList;
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

    /**
     * Gets the value of the param1 property.
     */
    public int getParam1() {
        return param1;
    }

    /**
     * Sets the value of the param1 property.
     */
    public void setParam1(int value) {
        this.param1 = value;
    }

}
