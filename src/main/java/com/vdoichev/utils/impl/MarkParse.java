package com.vdoichev.utils.impl;

import com.vdoichev.objects.Mark;
import com.vdoichev.objects.Market;
import com.vdoichev.utils.IParser;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MarkParse extends Mark implements IParser {
    private final String href;

    public MarkParse(String name, String href, String catalogGroup) {
        super(name, catalogGroup);
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    @Override
    public ArrayList<Market> enumElements(Elements listElements, String... filter) {
        return null;
    }

    @Override
    public String toString() {
        return "MarkParse{" +
                " name='" + name + '\'' +
                ", catalogGroup='" + catalogGroup + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
