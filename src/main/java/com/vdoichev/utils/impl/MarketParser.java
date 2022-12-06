package com.vdoichev.utils.impl;

import com.vdoichev.Main;
import com.vdoichev.objects.Market;
import com.vdoichev.utils.IParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MarketParser extends Market implements IParser {

    private final String marketHref;
    private List<ModelParser> models;

    public MarketParser(String name, String code, String marketHref) {
        super(name, code);
        this.marketHref = marketHref;
    }

    public String getMarketHref() {
        return marketHref;
    }

    public List<ModelParser> getModels() {
        return models;
    }

    @Override
    public List<ModelParser> parseByUrl(String url, String... filter) {
        Elements modelElements = IParser.getElementsByHref(url, "Выбор модели");
        if (modelElements != null) {
            return this.enumElements(modelElements, filter);
        } else System.out.println("Відсутні моделі");
        return null;
    }

    @Override
    public List<ModelParser> enumElements(Elements listElements, String... filter) {
        List<ModelParser> models = new ArrayList<>();
        for (Element element : listElements) {
            if (element.parent() != null &&
                    element.parent().attr("class").equals("id")) {
                String[] params = prepareParams(element);
                if (filter.length > 2 && !filter[2].equalsIgnoreCase(params[1])) {
                    continue;
                }
                ModelParser model = new ModelParser(params[0], params[1], params[2], params[3]);
                model.setEquipments(model.parseByUrl(model.getModelHref(), filter));
                models.add(model);
            }
        }
        return models;
    }

    /**
     * Підготовка даних для створення об'єкта модель авто
     *
     * @param element елемент HTML з даними
     * @return масив строк, де 0 - код моделі, 1 - назва моделі, 2 - дата виготовлення, 3 - посилання
     */
    @Override
    public String[] prepareParams(Element element) {
        String[] result = new String[4];
        if (element.parent() != null &&
                element.parent().parent() != null &&
                element.parent().parent().parent() != null) {
            result[0] = Objects.requireNonNull(element.parent().parent().parent().
                    previousElementSibling()).children().text();
        }
        result[1] = element.text().trim();
        for (Element sibling : element.parent().nextElementSiblings()) {
            if (sibling.attr("class").equals("dateRange")) {
                result[2] = sibling.text().substring(
                        0,
                        sibling.text().indexOf('-')
                ).trim();
            }
        }
        result[3] = Main.MAIN_URL + element.attr("href").trim();

        return result;
    }

    @Override
    public String toString() {
        return "MarketParser{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", href='" + marketHref + '\'' +
                ", models=" + models +
                '}';
    }

    public void setModels(List<ModelParser> models) {
        this.models = models;
    }

    public boolean isEmpty() {
        return this.getCode().isEmpty();
    }
}
