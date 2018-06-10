package com.ls.soa.game.fantasy.server.models;

import com.ls.soa.game.fantasy.api.server.models.IElement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "public")
public class Element implements Serializable, IElement {
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

    @ManyToOne(targetEntity = User.class)
    private User author;

    public Element() {
    }

    public Element(String param1, int param2, int param3, int param4) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    @Override
    public int getParam2() {
        return param2;
    }

    public void setParam2(int param2) {
        this.param2 = param2;
    }

    @Override
    public int getParam3() {
        return param3;
    }

    public void setParam3(int param3) {
        this.param3 = param3;
    }

    @Override
    public int getParam4() {
        return param4;
    }

    public void setParam4(int param4) {
        this.param4 = param4;
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
        return "Element{" +
                "id=" + id +
                ", param1=" + param1 +
                ", param2=" + param2 +
                ", param3=" + param3 +
                ", param4=" + param4 +
                '}';
    }
}
