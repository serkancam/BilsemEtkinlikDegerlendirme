/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BilsemEtkinlik.Entity;

/**
 *
 * @author serkancam
 */
public class Moduller 
{
    private int modulId;
    private String modulAdi;
    private Dersler ders;

    /**
     * @return the modulId
     */
    public int getModulId() {
        return modulId;
    }

    /**
     * @param modulId the modulId to set
     */
    public void setModulId(int modulId) {
        this.modulId = modulId;
    }

    /**
     * @return the modulAdi
     */
    public String getModulAdi() {
        return modulAdi;
    }

    /**
     * @param modulAdi the modulAdi to set
     */
    public void setModulAdi(String modulAdi) {
        this.modulAdi = modulAdi;
    }

    /**
     * @return the ders
     */
    public Dersler getDers() {
        return ders;
    }

    /**
     * @param ders the ders to set
     */
    public void setDers(Dersler ders) {
        this.ders = ders;
    }

   
    
}
