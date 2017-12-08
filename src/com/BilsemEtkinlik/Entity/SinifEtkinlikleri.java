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
public class SinifEtkinlikleri 
{
    private int sinifEtkinlikleriId;
    private Siniflar sinif;
    private Etkinlikler etkinlik;

    /**
     * @return the sinifEtkinlikleriId
     */
    public int getSinifEtkinlikleriId() {
        return sinifEtkinlikleriId;
    }

    /**
     * @param sinifEtkinlikleriId the sinifEtkinlikleriId to set
     */
    public void setSinifEtkinlikleriId(int sinifEtkinlikleriId) {
        this.sinifEtkinlikleriId = sinifEtkinlikleriId;
    }

    /**
     * @return the sinif
     */
    public Siniflar getSinif() {
        return sinif;
    }

    /**
     * @param sinif the sinif to set
     */
    public void setSinif(Siniflar sinif) {
        this.sinif = sinif;
    }

    /**
     * @return the etkinlik
     */
    public Etkinlikler getEtkinlik() {
        return etkinlik;
    }

    /**
     * @param etkinlik the etkinlik to set
     */
    public void setEtkinlik(Etkinlikler etkinlik) {
        this.etkinlik = etkinlik;
    }
    
}
