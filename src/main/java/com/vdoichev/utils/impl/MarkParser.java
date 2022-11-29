package com.vdoichev.utils.impl;

import com.vdoichev.Main;
import com.vdoichev.objects.Mark;
import com.vdoichev.utils.IParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MarkParser extends Mark implements IParser {
    private final String href;
    private List<MarketParser> markets;

    public MarkParser(String name, String href, String catalogGroup) {
        super(name, catalogGroup);
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    @Override
    public List<MarketParser> enumElements(Elements listElements, String... filter) {

        List<MarketParser> markets = new ArrayList<>();
        for (Element element : listElements) {
            assert element.parent() != null;
            if (element.parent().attr("class").equals("name")) {

                String href = Main.MAIN_URL + element.attr("href").trim();
                String code = href.substring(href.lastIndexOf("=") + 1);

                if (filter.length > 1 && !filter[1].equalsIgnoreCase(code)) {
                    continue;
                }

                MarketParser market = new MarketParser(
                        element.text().trim(),
                        code,
                        href);

//                Elements marketElements = IParser.getElementsByHref(href);
//                List<Market> market = mark.enumElements(marketElements, filter);

                markets.add(market);
            }
        }
        return markets;
    }

    @Override
    public String toString() {
        return "MarkParse{" +
                " name='" + name + '\'' +
                ", catalogGroup='" + catalogGroup + '\'' +
                ", href='" + href + '\'' +
                ", markets=" + markets +
                '}';
    }

    public void setMarkets(List<MarketParser> markets) {
        this.markets = markets;
    }
}
