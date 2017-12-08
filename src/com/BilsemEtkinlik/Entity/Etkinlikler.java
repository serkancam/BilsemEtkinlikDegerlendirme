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
public class Etkinlikler 
{
    private int etkinlikId;
    private String etkinlikAdi;
    private Moduller modul;

    /**
     * @return the etkinlikId
     */
    public int getEtkinlikId() {
        return etkinlikId;
    }

    /**
     * @param etkinlikId the etkinlikId to set
     */
    public void setEtkinlikId(int etkinlikId) {
        this.etkinlikId = etkinlikId;
    }

    /**
     * @return the etkinlikAdi
     */
    public String getEtkinlikAdi() {
        return etkinlikAdi;
    }

    /**
     * @param etkinlikAdi the etkinlikAdi to set
     */
    public void setEtkinlikAdi(String etkinlikAdi) {
        this.etkinlikAdi = etkinlikAdi;
    }

    /**
     * @return the modul
     */
    public Moduller getModul() {
        return modul;
    }

    /**
     * @param modul the modul to set
     */
    public void setModul(Moduller modul) {
        this.modul = modul;
    }

   
    
}
