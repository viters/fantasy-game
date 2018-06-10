package com.ls.soa.game.fantasy.server.models;


import com.ls.soa.game.fantasy.api.server.models.ICategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "public")
public class Category implements Serializable, ICategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "param1")
    private int param1;

    @ManyToOne(targetEntity = User.class)
    private User author;

    public Category() {
    }

    public Category(int param1) {
        this.param1 = param1;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public int getParam1() {
        return param1;
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
