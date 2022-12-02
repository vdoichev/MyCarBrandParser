package com.vdoichev.utils.impl;

import com.vdoichev.objects.Equipment;
import com.vdoichev.utils.IParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class EquipmentParser extends Equipment implements IParser {
    private String equipmentHref;

    public EquipmentParser(String code, String dateRange, String equipmentHref) {
        super(code, dateRange);
        this.equipmentHref = equipmentHref;
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
