package com.vdoichev.objects;

public class Market {
    private int id;
    protected final String name;
    protected final String code;

    public Market(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
