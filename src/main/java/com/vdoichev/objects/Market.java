package com.vdoichev.objects;

public class Market {
    private final String name;
    private final String href;

    public Market(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }

    @Override
    public String toString() {
        return "Market{" +
                "name='" + name + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
