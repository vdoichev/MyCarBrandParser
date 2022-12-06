package com.vdoichev.utils.impl;

import com.vdoichev.objects.Equipment;
import com.vdoichev.utils.IParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class EquipmentParser extends Equipment implements IParser {
    private final String equipmentHref;

    public EquipmentParser(String code, String equipmentHref) {
        super(code);
        this.equipmentHref = equipmentHref;
    }

//    public String getEquipmentHref() {
//        return equipmentHref;
//    }

    public void setFieldByCssClass(String cssClass, String value) {
        switch (cssClass) {
            case "dateRange":
                setDateRange(value);
                break;
            case "01":
                setEngine1(value);
                break;
            case "03":
                setBody(value);
                break;
            case "04":
                setGrade(value);
                break;
            case "05":
                setAtmMtm(value);
                break;
            case "06":
                setGearShiftType(value);
                break;
            case "07":
                setCab(value);
                break;
            case "08":
                setTransmissionModel(value);
                break;
            case "09":
                setLoadingCapacity(value);
                break;
            case "10":
                setRearTire(value);
                break;
            case "11":
                setDestination(value);
                break;
            case "12":
                setFuelInduction(value);
                break;
            default:
                System.out.println("Не визначений CSS Class "+cssClass+" для запису "+value);
        }
    }

    @Override
    public List<?> parseByUrl(String url, String... filter) {
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
        return "EquipmentParser{" +
                "code='" + code + '\'' +
                ", dateRange='" + dateRange + '\'' +
                ", equipmentHref='" + equipmentHref + '\'' +
                '}';
    }
}
