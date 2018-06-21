package com.ls.soa.game.fantasy.api.server.models;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private long id;
    private int param1;
    private long authorId;
    private long categoryDictionaryId;

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
}
