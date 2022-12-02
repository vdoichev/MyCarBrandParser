package com.vdoichev.objects;

public class Model {
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
}


