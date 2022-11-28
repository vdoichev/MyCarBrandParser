package com.vdoichev;

public class Mark {
    private final String name;
    private final String href;

    public Mark(String name, String href) {
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
        return "Mark{" +
                "name='" + name + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
