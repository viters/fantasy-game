package com.ls.soa.game.fantasy.api.server.models;

public class CategoryDictionaryDTO {
    private long id;
    private String categoryName;
    private String categoryParam1Name;
    private String elementName;
    private String elementParam1Name;
    private String elementParam2Name;
    private String elementParam3Name;
    private String elementParam4Name;

    public long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryParam1Name() {
        return categoryParam1Name;
    }

    public String getElementName() {
        return elementName;
    }

    public String getElementParam1Name() {
        return elementParam1Name;
    }

    public String getElementParam2Name() {
        return elementParam2Name;
    }

    public String getElementParam3Name() {
        return elementParam3Name;
    }

    public String getElementParam4Name() {
        return elementParam4Name;
    }
}
