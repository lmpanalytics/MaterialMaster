/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tetrapak.processing.parts_control.materialmaster;

/**
 * Models the Material report from BO 'Materials_raw.xlsx', and the Global Price
 * List.
 *
 * @author SEPALMM
 */
public class Data {

    private String materialNumberNUM;
    private String materialNumberBW;
    private String materialNumberTP;
    private String description;
    private String mpg;
    private String assortmentGroup;
    private double pg;
    private String refName;

    /**
     * Constructor
     *
     * @param materialNumberNUM
     * @param materialNumberBW
     * @param materialNumberTP
     * @param description
     * @param mpg
     * @param assortmentGroup
     * @param pg
     * @param refName
     */
    public Data(String materialNumberNUM, String materialNumberBW, String materialNumberTP, String description, String mpg, String assortmentGroup, double pg, String refName) {
        this.materialNumberNUM = materialNumberNUM;
        this.materialNumberBW = materialNumberBW;
        this.materialNumberTP = materialNumberTP;
        this.description = description;
        this.mpg = mpg;
        this.assortmentGroup = assortmentGroup;
        this.pg = pg;
        this.refName = refName;
    }

    public String getMaterialNumberNUM() {
        return materialNumberNUM;
    }

    public void setMaterialNumberNUM(String materialNumberNUM) {
        this.materialNumberNUM = materialNumberNUM;
    }

    public String getMaterialNumberBW() {
        return materialNumberBW;
    }

    public void setMaterialNumberBW(String materialNumberBW) {
        this.materialNumberBW = materialNumberBW;
    }

    public String getMaterialNumberTP() {
        return materialNumberTP;
    }

    public void setMaterialNumberTP(String materialNumberTP) {
        this.materialNumberTP = materialNumberTP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMpg() {
        return mpg;
    }

    public void setMpg(String mpg) {
        this.mpg = mpg;
    }

    public String getAssortmentGroup() {
        return assortmentGroup;
    }

    public void setAssortmentGroup(String assortmentGroup) {
        this.assortmentGroup = assortmentGroup;
    }

    public double getPg() {
        return pg;
    }

    public void setPg(double pg) {
        this.pg = pg;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

}
