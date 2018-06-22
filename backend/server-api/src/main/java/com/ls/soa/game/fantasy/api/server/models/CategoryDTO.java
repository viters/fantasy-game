package com.ls.soa.game.fantasy.api.server.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CategoryDTO implements Serializable {
    private long id;
    private int param1;
    private Long authorId;
    private Long categoryDictionaryId;
    private UserDTO authorDTO;
    private CategoryDictionaryDTO categoryDictionaryDTO;
    private List<ElementDTO> elementDTOList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getParam1() {
        return param1;
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getCategoryDictionaryId() {
        return categoryDictionaryId;
    }

    public void setCategoryDictionaryId(long categoryDictionaryId) {
        this.categoryDictionaryId = categoryDictionaryId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setCategoryDictionaryId(Long categoryDictionaryId) {
        this.categoryDictionaryId = categoryDictionaryId;
    }

    public UserDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(UserDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public CategoryDictionaryDTO getCategoryDictionaryDTO() {
        return categoryDictionaryDTO;
    }

    public void setCategoryDictionaryDTO(CategoryDictionaryDTO categoryDictionaryDTO) {
        this.categoryDictionaryDTO = categoryDictionaryDTO;
    }

    public List<ElementDTO> getElementDTOList() {
        return elementDTOList;
    }

    public void setElementDTOList(List<ElementDTO> elementDTOList) {
        this.elementDTOList = elementDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDTO)) return false;
        CategoryDTO that = (CategoryDTO) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", param1=" + param1 +
                ", authorId=" + authorId +
                ", categoryDictionaryId=" + categoryDictionaryId +
                '}';
    }
}
