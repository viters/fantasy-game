package com.ls.soa.game.fantasy.api.server.models;

import java.io.Serializable;
import java.util.Objects;

public class CategoryDictionaryDTO implements Serializable {
    private long id;
    private String categoryName;
    private String categoryParam1Name;
    private String elementName;
    private String elementParam1Name;
    private String elementParam2Name;
    private String elementParam3Name;
    private String elementParam4Name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryParam1Name() {
        return categoryParam1Name;
    }

    public void setCategoryParam1Name(String categoryParam1Name) {
        this.categoryParam1Name = categoryParam1Name;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementParam1Name() {
        return elementParam1Name;
    }

    public void setElementParam1Name(String elementParam1Name) {
        this.elementParam1Name = elementParam1Name;
    }

    public String getElementParam2Name() {
        return elementParam2Name;
    }

    public void setElementParam2Name(String elementParam2Name) {
        this.elementParam2Name = elementParam2Name;
    }

    public String getElementParam3Name() {
        return elementParam3Name;
    }

    public void setElementParam3Name(String elementParam3Name) {
        this.elementParam3Name = elementParam3Name;
    }

    public String getElementParam4Name() {
        return elementParam4Name;
    }

    public void setElementParam4Name(String elementParam4Name) {
        this.elementParam4Name = elementParam4Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDictionaryDTO)) return false;
        CategoryDictionaryDTO that = (CategoryDictionaryDTO) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "CategoryDictionaryDTO{" +
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
