package com.vdoichev.utils.impl;

import com.vdoichev.Main;
import com.vdoichev.utils.IParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parser implements IParser {

    @Override
    public List<MarkParser> parseByUrl(String url, String... filter) {
        Elements markElements = IParser.getElementsByHref(url, "");
        if (markElements != null) {
            return enumElements(markElements, filter);
        } else {
            System.out.println("Марка авто за вказаними параметрами не знайдена!");
        }
        return null;
    }

    @Override
    public List<MarkParser> enumElements(Elements listElements, String... filter) {
        List<MarkParser> marks = new ArrayList<>();
        for (Element element : listElements) {
            if (element.parent() != null &&
                    element.parent().attr("class").equals("CatalogGroup")) {
                String[] params = prepareParams(element);
                if (filter.length > 0 && !filter[0].equalsIgnoreCase(params[0])) {
                    continue;
                }
                MarkParser mark = new MarkParser(params[0],params[1],params[2]);
                mark.setMarkets(mark.parseByUrl(mark.getMarkHref(), filter));
                if (mark.isNullMarkets())
                    mark.setMarkets(mark.addEmptyMarket(filter));
                marks.add(mark);
                System.out.println(mark);
            }
        }
        return marks;
    }

    /**
     * Підготовка даних для створення об'єкта марка авто
     * @param element елемент HTML з даними
     * @return масив строк, де 0 - назва марки авто, 1 - група каталогу, 2 - посилання
     */
    @Override
    public String[] prepareParams(Element element) {
        String[] result = new String[3];
        result[0] = element.text().trim();
        if (element.parent() != null) {
            result[1] = Objects.requireNonNull(element.parent().previousElementSibling()).text();
        }
        result[2] = Main.MAIN_URL + element.attr("href").trim();
        return result;
    }
}
