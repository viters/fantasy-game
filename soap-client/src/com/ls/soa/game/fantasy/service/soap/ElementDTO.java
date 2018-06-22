
package com.ls.soa.game.fantasy.service.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for elementDTO complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="elementDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authorDTO" type="{http://soap.service.fantasy.game.soa.ls.com/}userDTO" minOccurs="0"/>
 *         &lt;element name="authorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="categoryDTO" type="{http://soap.service.fantasy.game.soa.ls.com/}categoryDTO" minOccurs="0"/>
 *         &lt;element name="categoryDictionaryDTO" type="{http://soap.service.fantasy.game.soa.ls.com/}categoryDictionaryDTO" minOccurs="0"/>
 *         &lt;element name="categoryDictionaryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="categoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="param1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="param2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="param3" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="param4" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "elementDTO", propOrder = {
        "authorDTO",
        "authorId",
        "categoryDTO",
        "categoryDictionaryDTO",
        "categoryDictionaryId",
        "categoryId",
        "id",
        "param1",
        "param2",
        "param3",
        "param4"
})
public class ElementDTO {

    protected UserDTO authorDTO;
    protected Long authorId;
    protected CategoryDTO categoryDTO;
    protected CategoryDictionaryDTO categoryDictionaryDTO;
    protected Long categoryDictionaryId;
    protected Long categoryId;
    protected long id;
    protected String param1;
    protected int param2;
    protected int param3;
    protected int param4;

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
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * Sets the value of the authorId property.
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setAuthorId(Long value) {
        this.authorId = value;
    }

    /**
     * Gets the value of the categoryDTO property.
     *
     * @return possible object is
     * {@link CategoryDTO }
     */
    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    /**
     * Sets the value of the categoryDTO property.
     *
     * @param value allowed object is
     *              {@link CategoryDTO }
     */
    public void setCategoryDTO(CategoryDTO value) {
        this.categoryDTO = value;
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
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getCategoryDictionaryId() {
        return categoryDictionaryId;
    }

    /**
     * Sets the value of the categoryDictionaryId property.
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setCategoryDictionaryId(Long value) {
        this.categoryDictionaryId = value;
    }

    /**
     * Gets the value of the categoryId property.
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the value of the categoryId property.
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setCategoryId(Long value) {
        this.categoryId = value;
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
     *
     * @return possible object is
     * {@link String }
     */
    public String getParam1() {
        return param1;
    }

    /**
     * Sets the value of the param1 property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setParam1(String value) {
        this.param1 = value;
    }

    /**
     * Gets the value of the param2 property.
     */
    public int getParam2() {
        return param2;
    }

    /**
     * Sets the value of the param2 property.
     */
    public void setParam2(int value) {
        this.param2 = value;
    }

    /**
     * Gets the value of the param3 property.
     */
    public int getParam3() {
        return param3;
    }

    /**
     * Sets the value of the param3 property.
     */
    public void setParam3(int value) {
        this.param3 = value;
    }

    /**
     * Gets the value of the param4 property.
     */
    public int getParam4() {
        return param4;
    }

    /**
     * Sets the value of the param4 property.
     */
    public void setParam4(int value) {
        this.param4 = value;
    }

}
