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

    public String getProductionDate() {
        return productionDate;
    }

    public LocalDate getProductionDateFormat(){
        //LocalDate localDate;
//        if (this.getProductionDate().length()==7){
//            localDate
//        }
        return LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


