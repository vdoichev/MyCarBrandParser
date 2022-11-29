package com.vdoichev.objects;

public class Market {
    protected final String name;

    protected final String code;

    public Market(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }
}
