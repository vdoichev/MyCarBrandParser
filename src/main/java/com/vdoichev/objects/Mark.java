package com.vdoichev.objects;

public class Mark {
    protected final String name;
    protected final String catalogGroup;

    private Market market;

    public Mark(String name, String catalogGroup) {
        this.name = name;
        this.catalogGroup = catalogGroup;
    }

}
