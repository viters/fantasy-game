package com.ls.soa.game.fantasy.server.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "elements", schema = "public")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllElements",
                query = "SELECT * FROM elements",
                resultClass = Element.class
        ),
        @NamedNativeQuery(
                name = "findElementById",
                query = "SELECT * FROM elements WHERE id = :id",
                resultClass = Category.class
        )
})
public class Element implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "param1")
    private String param1;

    @Column(name = "param2")
    private int param2;

    @Column(name = "param3")
    private int param3;

    @Column(name = "param4")
    private int param4;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User author;

    @Column(name = "author_id", insertable = false, updatable = false)
    private long authorId;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    private Category category;

    @Column(name = "category_id", insertable = false, updatable = false)
    private long categoryId;

    public Element() {
    }

    public Element(String param1, int param2, int param3, int param4) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
    }

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public void merge(Element element) {
        setParam1(element.param1);
        setParam2(element.param2);
        setParam3(element.param3);
        setParam4(element.param4);
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
        return "Element{" +
                "id=" + id +
                ", param1=" + param1 +
                ", param2=" + param2 +
                ", param3=" + param3 +
                ", param4=" + param4 +
                '}';
    }
}
