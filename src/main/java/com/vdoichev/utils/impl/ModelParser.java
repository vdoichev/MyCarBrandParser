package com.vdoichev.utils.impl;

import com.vdoichev.objects.Model;
import com.vdoichev.utils.IParser;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.List;

public class ModelParser extends Model implements IParser {
    private final String href;
//    private List<ComplectationsParser> markets;

    public ModelParser(String name, String code, Date productionDate, String href) {
        super(name, code, productionDate);
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    @Override
    public List<?> parseByUrl(String url, String... filter) {
        return null;
    }

    @Override
    public List<?> enumElements(Elements listElements, String... filter) {
        return null;
    }
}
