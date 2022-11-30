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

    private List<ModelParser> models;

    public MarkParser(String name, String href, String catalogGroup) {
        super(name, catalogGroup);
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    @Override
    public List<MarketParser> parseByUrl(String url, String... filter) {
        Elements marketElements = IParser.getElementsByHref(url, "Выбор рынка");
        if (marketElements != null) {
            return this.enumElements(marketElements, filter);
        } else System.out.println("Відсутні ринки збуту для марки авто " + this.getName());
        return null;
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

                market.setModels(market.parseByUrl(market.getHref(),filter));
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

    public boolean isNullMarkets(){
        return this.markets==null;
    }

    public void setModels(List<ModelParser> models) {
        this.models = models;
    }
}
