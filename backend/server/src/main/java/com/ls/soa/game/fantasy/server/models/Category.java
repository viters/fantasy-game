package com.ls.soa.game.fantasy.server.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "public")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllCategories",
                query = "SELECT * FROM categories",
                resultClass = Category.class
        ),
        @NamedNativeQuery(
                name = "findCategoryById",
                query = "SELECT * FROM categories WHERE id = :id",
                resultClass = Category.class
        )
})
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "param1")
    private int param1;

    @ManyToOne(targetEntity = CategoryDictionary.class, fetch = FetchType.LAZY)
    private CategoryDictionary categoryDictionary;

    @Column(name = "categorydictionary_id", insertable = false, updatable = false)
    private long categoryDictionaryId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User author;

    @Column(name = "author_id", insertable = false, updatable = false)
    private long authorId;

    @OneToMany(targetEntity = Element.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "category_id")
    private List<Element> elements;

    public Category() {
    }

    public Category(int param1) {
        this.param1 = param1;
    }

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public CategoryDictionary getCategoryDictionary() {
        return categoryDictionary;
    }

    public void setCategoryDictionary(CategoryDictionary categoryDictionary) {
        this.categoryDictionary = categoryDictionary;
    }

    public List<Element> getElements() {
        return elements;
    }

    public long getCategoryDictionaryId() {
        return categoryDictionaryId;
    }

    public void setCategoryDictionaryId(long categoryDictionaryId) {
        this.categoryDictionaryId = categoryDictionaryId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void merge(Category category) {
        setParam1(category.param1);
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
        return "Category{" +
                "id=" + id +
                ", param1=" + param1 +
                '}';
    }
}
