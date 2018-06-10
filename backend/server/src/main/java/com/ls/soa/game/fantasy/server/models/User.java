package com.ls.soa.game.fantasy.server.models;

import com.ls.soa.game.fantasy.api.server.models.IUser;
import com.ls.soa.game.fantasy.api.server.models.Role;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "findUserByUsername",
                query = "SELECT * FROM users WHERE username = :username"
        )
})
public class User implements Serializable, IUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(targetEntity = Category.class)
    private List<Category> categories;

    @OneToMany(targetEntity = Element.class)
    private List<Element> elements;

    @PrePersist()
    private void hashPassword() {
        password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.USER.toString();
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role.toString();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public List<Element> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
