package com.vdoichev.objects;


import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
    private int id;
    protected final String name;
    protected final String code;
    protected final String productionDate;

    public Model(String name, String code, String productionDate) {
        this.name = name;
        this.code = code;
        this.productionDate = productionDate;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    private String getProductionDate() {
        return productionDate;
    }

    public LocalDate getProductionDateFormat() {
        Pattern pattern = Pattern.compile("\\d{2}.\\d{4}");
        Matcher matcher = pattern.matcher(this.getProductionDate());
        if (this.getProductionDate().length() == 7 && matcher.find()) {
            String date = this.getProductionDate();
            String year = date.substring(date.lastIndexOf('.') + 1);
            String month = date.substring(0, date.indexOf('.'));
            String day = "01";
            return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        } else {
            System.out.println("Необроблений формат дати - " + this.getProductionDate());
        }
        return LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


