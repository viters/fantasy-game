package com.ls.soa.game.fantasy.api.server.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserDTO implements Serializable {
    private long id;
    private String username;
    private String role;
    private List<CategoryDTO> categoryDTOList;
    private List<ElementDTO> elementDTOList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<CategoryDTO> getCategoryDTOList() {
        return categoryDTOList;
    }

    public void setCategoryDTOList(List<CategoryDTO> categoryDTOList) {
        this.categoryDTOList = categoryDTOList;
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
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return getId() == userDTO.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
