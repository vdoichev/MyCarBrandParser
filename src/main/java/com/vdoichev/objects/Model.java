package com.vdoichev.objects;

import java.util.Date;

public class Model {
    private final String name;
    private final String code;
    private final Date productionDate;

    public Model(String name, String code, Date productionDate) {
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

    public Date getProductionDate() {
        return productionDate;
    }
}


