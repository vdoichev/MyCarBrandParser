package com.vdoichev.utils.impl;

import com.vdoichev.Main;
import com.vdoichev.utils.IParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MainParser implements IParser {

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
            assert element.parent() != null;
            if (element.parent().attr("class").equals("CatalogGroup")) {
                Element groupMark = element.parent().previousElementSibling();
                assert groupMark != null;

                if (filter.length > 0 && !filter[0].equalsIgnoreCase(element.text().trim())) {
                    continue;
                }

                String href = Main.MAIN_URL + element.attr("href").trim();

                MarkParser mark = new MarkParser(
                        element.text().trim(),
                        href,
                        groupMark.text());
                mark.setMarkets(mark.parseByUrl(mark.getHref(), filter));
//                if (mark.isNullMarkets()){
//                    mark.setModels(mark.parseByUrl(mark.getHref(),filter));
//                }
                marks.add(mark);
//                System.out.println(mark);
            }
        }
        return marks;
    }
}
