package com.vdoichev.utils.impl;

import com.vdoichev.objects.Market;
import com.vdoichev.utils.IParser;
import org.jsoup.select.Elements;

import java.util.List;

public class MarketParser extends Market implements IParser {

    private final String href;

    public MarketParser(String name, String code, String href) {
        super(name, code);
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    @Override
    public List<?> enumElements(Elements listElements, String... filter) {
        return null;
    }

    @Override
    public String toString() {
        return "MarketParser{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
