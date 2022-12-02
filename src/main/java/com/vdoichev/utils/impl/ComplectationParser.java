package com.vdoichev.utils.impl;

import com.vdoichev.objects.Complectation;
import com.vdoichev.utils.IParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class ComplectationParser extends Complectation implements IParser {
    private String complectationHref;

    public ComplectationParser(String code, String dateRange, String complectationHref) {
        super(code, dateRange);
        this.complectationHref = complectationHref;
    }

    @Override
    public List<?> parseByUrl(String url, String... filter) {
        return null;
    }

    @Override
    public List<?> enumElements(Elements listElements, String... filter) {
        return null;
    }

    @Override
    public String[] prepareParams(Element element) {
        return new String[0];
    }
}
