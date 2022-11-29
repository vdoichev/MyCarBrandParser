package com.vdoichev.utils.impl;

import com.vdoichev.Main;
import com.vdoichev.objects.Mark;
import com.vdoichev.utils.IParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainParse implements IParser {

    @Override
    public ArrayList<Mark> enumElements(Elements listElements, String... filter) {
        ArrayList<Mark> marks = new ArrayList<>();
        for (Element element : listElements) {
            assert element.parent() != null;
            if (element.parent().attr("class").equals("CatalogGroup")) {
                Element groupMark = element.parent().previousElementSibling();
                assert groupMark != null;

                if (filter.length > 0 && !filter[0].equalsIgnoreCase(element.text().trim())) {
                    continue;
                }

                String href = Main.MAIN_URL + element.attr("href").trim();

                MarkParse mark = new MarkParse(
                        element.text().trim(),
                        href,
                        groupMark.text());

//                Elements marketElements = IParser.getElementsByHref(href);
//                ArrayList<Market> market = mark.enumElements(marketElements, filter);

                marks.add(mark);
            }
        }
        return marks;
    }
}
