package com.vdoichev.utils.impl;

import com.vdoichev.Main;
import com.vdoichev.objects.Mark;
import com.vdoichev.utils.IParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MarkParser extends Mark implements IParser {
    private final String markHref;
    private List<MarketParser> markets;

    public MarkParser(String name, String catalogGroup, String markHref) {
        super(name, catalogGroup);
        this.markHref = markHref;
    }

    public String getMarkHref() {
        return markHref;
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
            if (element.parent() != null &&
                    element.parent().attr("class").equals("name")) {
                String[] params = prepareParams(element);
                if (filter.length > 1 && !filter[1].equalsIgnoreCase(params[1])) {
                    continue;
                }
                MarketParser market = new MarketParser(params[0], params[1], params[2]);
                market.setModels(market.parseByUrl(market.getMarketHref(), filter));
                markets.add(market);
            }
        }
        return markets;
    }

    /**
     * Підготовка даних для створення об'єкта країна збуту
     * @param element елемент HTML з даними
     * @return масив строк, де 0 - назва країни збуту, 1 - код країни, 2 - посилання
     */
    @Override
    public String[] prepareParams(Element element) {
        String[] result = new String[3];
        result[0] = element.text().trim();
        String href = Main.MAIN_URL + element.attr("href").trim();
        result[1] = href.substring(href.lastIndexOf("=") + 1);
        result[2] = href;
        return result;
    }

    @Override
    public String toString() {
        return "MarkParse{" +
                " name='" + name + '\'' +
                ", catalogGroup='" + catalogGroup + '\'' +
                ", href='" + markHref + '\'' +
                ", markets=" + markets +
                '}';
    }

    public void setMarkets(List<MarketParser> markets) {
        this.markets = markets;
    }

    public List<MarketParser> addEmptyMarket(String... filter) {
        List<MarketParser> markets = new ArrayList<>();
        MarketParser emptyMarket = new MarketParser("", "", this.getMarkHref());
        emptyMarket.setModels(emptyMarket.parseByUrl(emptyMarket.getMarketHref(), filter));
        markets.add(emptyMarket);
        return markets;
    }

    public boolean isNullMarkets() {
        return this.markets == null;
    }

    public boolean isEmptyMarkets() {
        return this.markets.size() == 1 && this.markets.get(0).getCode().isEmpty();
    }
}
