package com.vdoichev.utils.impl;

import com.vdoichev.objects.Model;
import com.vdoichev.utils.IParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class ModelParser extends Model implements IParser {
    private final String modelHref;
//    private List<ComplectationsParser> comp;

    public ModelParser(String name, String code, String productionDate, String modelHref) {
        super(name, code, productionDate);
        this.modelHref = modelHref;
    }

    public String getModelHref() {
        return modelHref;
    }

    @Override
    public List<?> parseByUrl(String url, String... filter) {
        Elements modelElements = IParser.getElementsByHref(url, "Выбор комплектации автомобиля");
        if (modelElements != null) {
            return this.enumElements(modelElements, filter);
        } else System.out.println("Відсутні моделі");
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

    @Override
    public String toString() {
        return "ModelParser{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", href='" + modelHref + '\'' +
                '}';
    }
}
