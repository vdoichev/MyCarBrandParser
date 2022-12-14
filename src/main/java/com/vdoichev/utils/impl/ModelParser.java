package com.vdoichev.utils.impl;

import com.vdoichev.Main;
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
                    element.parent().attr("class").equals("modelCode")) {
                String[] params = prepareParams(element);
                if (filter.length > 3 && !filter[3].equalsIgnoreCase(params[0])) {
                    continue;
                }
                EquipmentParser equipment = new EquipmentParser(params[0], params[1]);
                setDetailParamsForEquipment(element, equipment);
                equipments.add(equipment);
            }
        }
        return equipments;
    }

    private static void setDetailParamsForEquipment(Element element, EquipmentParser equipment) {
        if (element.parent() != null && element.parent().parent() != null &&
                element.parent().parent().parent() != null) {
            Element parentTr = element.parent().parent().parent();
            if (parentTr.tag().getName().equals("tr")) {
                Elements children = parentTr.children();
                for (Element child : children) {
                    if (!child.children().isEmpty()) {
                        equipment.setFieldByCssClass(child.child(0).className(),
                                child.child(0).text().trim());
                    }
                }
            }
        }
    }

    @Override
    public String[] prepareParams(Element element) {
        String[] result = new String[2];
        result[0] = element.text().trim();
        result[1] = Main.MAIN_URL + element.attr("href").trim();
        return result;
    }

    @Override
    public String toString() {
        return "ModelParser{" +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", modelHref='" + modelHref + '\'' +
                ", equipments=" + equipments +
                '}';
    }

    public void setEquipments(List<EquipmentParser> equipments) {
        this.equipments = equipments;
    }
}
