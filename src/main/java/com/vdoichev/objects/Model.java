package com.vdoichev.objects;


import java.time.LocalDate;

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
        if (getProductionDate().length() == 7) {
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


