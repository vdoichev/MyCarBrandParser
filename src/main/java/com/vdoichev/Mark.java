package com.vdoichev;

public class Mark {
    private final String name;
    private final String href;
    private final String catalogGroup;

    public Mark(String name, String href, String catalogGroup) {
        this.name = name;
        this.href = href;
        this.catalogGroup = catalogGroup;
    }

    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }

    public String getCatalogGroup() {
        return catalogGroup;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "name='" + name + '\'' +
                ", href='" + href + '\'' +
                ", catalogGroup='" + catalogGroup + '\'' +
                '}';
    }
}
