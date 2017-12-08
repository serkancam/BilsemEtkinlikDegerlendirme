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
public class EtkinlikSorulari 
{
    private int soruId;
    private Etkinlikler etkinlik;
    private String soru;

    /**
     * @return the soruId
     */
    public int getSoruId() {
        return soruId;
    }

    /**
     * @param soruId the soruId to set
     */
    public void setSoruId(int soruId) {
        this.soruId = soruId;
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

    /**
     * @return the soru
     */
    public String getSoru() {
        return soru;
    }

    /**
     * @param soru the soru to set
     */
    public void setSoru(String soru) {
        this.soru = soru;
    }

   
    
    
}
