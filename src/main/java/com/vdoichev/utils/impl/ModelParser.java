package com.vdoichev.utils.impl;

import com.vdoichev.objects.Model;
import com.vdoichev.utils.IParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ModelParser extends Model implements IParser {
    private final String modelHref;
    private List<EquipmentParser> equipments;

    public ModelParser(String name, String code, String productionDate, String modelHref) {
        super(name, code, productionDate);
        this.modelHref = modelHref;
    }

    public List<EquipmentParser> getEquipments() {
        return equipments;
    }

    public String getModelHref() {
        return modelHref;
    }

    @Override
    public List<EquipmentParser> parseByUrl(String url, String... filter) {
        Elements equipmentElements = IParser.getElementsByHref(url, "Выбор комплектации автомобиля");
        if (equipmentElements != null) {
            return this.enumElements(equipmentElements, filter);
        } else System.out.println("Відсутні комплектації");
        return null;
    }

    @Override
    public List<EquipmentParser> enumElements(Elements listElements, String... filter) {
        List<EquipmentParser> equipments = new ArrayList<>();
        for (Element element : listElements) {
            if (element.parent() != null &&
                    element.parent().attr("class").equals("id")) {
                String[] params = prepareParams(element);
                if (filter.length > 2 && !filter[2].equalsIgnoreCase(params[1])) {
                    continue;
                }
                EquipmentParser equipment = new EquipmentParser(params[0], params[1], params[2]);
//                equipment.setModels(equipment.parseByUrl(equipment.getHref(), filter));
                equipments.add(equipment);
            }
        }
        return equipments;
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

    public void setEquipments(List<EquipmentParser> equipments) {
        this.equipments = equipments;
    }
}
