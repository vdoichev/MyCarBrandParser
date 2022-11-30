package com.vdoichev.objects;

public class Mark {
    protected final String name;
    protected final String catalogGroup;

    public Mark(String name, String catalogGroup) {
        this.name = name;
        this.catalogGroup = catalogGroup;
    }

    public String getName() {
        return name;
    }

    public String getCatalogGroup() {
        return catalogGroup;
    }
}
