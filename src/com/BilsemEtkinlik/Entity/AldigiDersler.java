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
public class AldigiDersler 
{
    private int aldigiId;
    private Ogrenciler ogrenci;
    private Dersler ders;

    /**
     * @return the aldigiId
     */
    public int getAldigiId() {
        return aldigiId;
    }

    /**
     * @param aldigiId the aldigiId to set
     */
    public void setAldigiId(int aldigiId) {
        this.aldigiId = aldigiId;
    }

    /**
     * @return the ogrenci
     */
    public Ogrenciler getOgrenci() {
        return ogrenci;
    }

    /**
     * @param ogrenci the ogrenci to set
     */
    public void setOgrenci(Ogrenciler ogrenci) {
        this.ogrenci = ogrenci;
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
