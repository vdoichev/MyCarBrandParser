package com.vdoichev.objects;

public class Equipment {
    private int id;
    protected final String code;
    protected String dateRange;
    protected String engine1;
    protected String body;
    protected String grade;
    protected String atmMtm;
    protected String gearShiftType;
    protected String cab;
    protected String transmissionModel;
    protected String loadingCapacity;
    protected String rearTire;
    protected String destination;
    protected String fuelInduction;

    public Equipment(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public void setEngine1(String engine1) {
        this.engine1 = engine1;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAtmMtm(String atmMtm) {
        this.atmMtm = atmMtm;
    }

    public void setGearShiftType(String gearShiftType) {
        this.gearShiftType = gearShiftType;
    }

    public void setCab(String cab) {
        this.cab = cab;
    }

    public void setTransmissionModel(String transmissionModel) {
        this.transmissionModel = transmissionModel;
    }

    public void setLoadingCapacity(String loadingCapacity) {
        this.loadingCapacity = loadingCapacity;
    }

    public void setRearTire(String rearTire) {
        this.rearTire = rearTire;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFuelInduction(String fuelInduction) {
        this.fuelInduction = fuelInduction;
    }

    public String getDateRange() {
        return dateRange;
    }

    public String getEngine1() {
        return engine1;
    }

    public String getBody() {
        return body;
    }

    public String getGrade() {
        return grade;
    }

    public String getAtmMtm() {
        return atmMtm;
    }

    public String getGearShiftType() {
        return gearShiftType;
    }

    public String getCab() {
        return cab;
    }

    public String getTransmissionModel() {
        return transmissionModel;
    }

    public String getLoadingCapacity() {
        return loadingCapacity;
    }

    public String getRearTire() {
        return rearTire;
    }

    public String getDestination() {
        return destination;
    }

    public String getFuelInduction() {
        return fuelInduction;
    }
}
