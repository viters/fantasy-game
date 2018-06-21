package com.ls.soa.game.fantasy.server.models;

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
                name = "findUserById",
                query = "SELECT * FROM users WHERE id = :id",
                resultClass = User.class
        ),
        @NamedNativeQuery(
                name = "findUserByUsername",
                query = "SELECT * FROM users WHERE username = :username",
                resultClass = User.class
        ),
        @NamedNativeQuery(
                name = "getAllUsers",
                query = "SELECT * FROM users",
                resultClass = User.class
        )
})
public class User implements Serializable {
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

    @OneToMany(targetEntity = Category.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "author_id")
    private List<Category> categories;

    @OneToMany(targetEntity = Element.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "author_id")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void merge(User user) {
        this.setUsername(user.username);
        this.setRole(user.role);
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
