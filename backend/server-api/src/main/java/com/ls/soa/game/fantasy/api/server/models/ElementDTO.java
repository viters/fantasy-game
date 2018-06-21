package com.ls.soa.game.fantasy.api.server.models;

import java.io.Serializable;
import java.util.Objects;

public class ElementDTO implements Serializable {
    private long id;
    private String param1;
    private int param2;
    private int param3;
    private int param4;
    private long authorId;
    private long categoryId;
    private long categoryDictionaryId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public int getParam2() {
        return param2;
    }

    public void setParam2(int param2) {
        this.param2 = param2;
    }

    public int getParam3() {
        return param3;
    }

    public void setParam3(int param3) {
        this.param3 = param3;
    }

    public int getParam4() {
        return param4;
    }

    public void setParam4(int param4) {
        this.param4 = param4;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getCategoryDictionaryId() {
        return categoryDictionaryId;
    }

    public void setCategoryDictionaryId(long categoryDictionaryId) {
        this.categoryDictionaryId = categoryDictionaryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElementDTO)) return false;
        ElementDTO that = (ElementDTO) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "ElementDTO{" +
                "id=" + id +
                ", param1='" + param1 + '\'' +
                ", param2=" + param2 +
                ", param3=" + param3 +
                ", param4=" + param4 +
                ", authorId=" + authorId +
                ", categoryId=" + categoryId +
                ", categoryDictionaryId=" + categoryDictionaryId +
                '}';
    }
}
