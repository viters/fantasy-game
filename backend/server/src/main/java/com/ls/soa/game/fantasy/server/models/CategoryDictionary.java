package com.ls.soa.game.fantasy.server.models;

import com.ls.soa.game.fantasy.api.server.models.ICategoryDictionary;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "category_dictionaries", schema = "public")
public class CategoryDictionary implements Serializable, ICategoryDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_param1_name", nullable = false)
    private String categoryParam1Name;

    @Column(name = "element_name", nullable = false)
    private String elementName;

    @Column(name = "element_param1_name", nullable = false)
    private String elementParam1Name;

    @Column(name = "element_param2_name", nullable = false)
    private String elementParam2Name;

    @Column(name = "element_param3_name", nullable = false)
    private String elementParam3Name;

    @Column(name = "element_param4_name", nullable = false)
    private String elementParam4Name;

    public CategoryDictionary() {
    }

    public CategoryDictionary(String categoryName, String categoryParam1Name, String elementName, String elementParam1Name, String elementParam2Name, String elementParam3Name, String elementParam4Name) {
        this.categoryName = categoryName;
        this.categoryParam1Name = categoryParam1Name;
        this.elementName = elementName;
        this.elementParam1Name = elementParam1Name;
        this.elementParam2Name = elementParam2Name;
        this.elementParam3Name = elementParam3Name;
        this.elementParam4Name = elementParam4Name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String getCategoryParam1Name() {
        return categoryParam1Name;
    }

    public void setCategoryParam1Name(String categoryParam1Name) {
        this.categoryParam1Name = categoryParam1Name;
    }

    @Override
    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public String getElementParam1Name() {
        return elementParam1Name;
    }

    public void setElementParam1Name(String elementParam1Name) {
        this.elementParam1Name = elementParam1Name;
    }

    @Override
    public String getElementParam2Name() {
        return elementParam2Name;
    }

    public void setElementParam2Name(String elementParam2Name) {
        this.elementParam2Name = elementParam2Name;
    }

    @Override
    public String getElementParam3Name() {
        return elementParam3Name;
    }

    public void setElementParam3Name(String elementParam3Name) {
        this.elementParam3Name = elementParam3Name;
    }

    @Override
    public String getElementParam4Name() {
        return elementParam4Name;
    }

    public void setElementParam4Name(String elementParam4Name) {
        this.elementParam4Name = elementParam4Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;
        Element element = (Element) o;
        return getId() == element.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "CategoryDictionary{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryParam1Name='" + categoryParam1Name + '\'' +
                ", elementName='" + elementName + '\'' +
                ", elementParam1Name='" + elementParam1Name + '\'' +
                ", elementParam2Name='" + elementParam2Name + '\'' +
                ", elementParam3Name='" + elementParam3Name + '\'' +
                ", elementParam4Name='" + elementParam4Name + '\'' +
                '}';
    }
}
