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
public class EtkinlikNotlari
{
    private int notId;
    private EtkinlikSorulari soru;
    private Ogrenciler ogrenci;
    private int puan;

    /**
     * @return the notId
     */
    public int getNotId() {
        return notId;
    }

    /**
     * @param notId the notId to set
     */
    public void setNotId(int notId) {
        this.notId = notId;
    }

    /**
     * @return the soru
     */
    public EtkinlikSorulari getSoru() {
        return soru;
    }

    /**
     * @param soru the soru to set
     */
    public void setSoru(EtkinlikSorulari soru) {
        this.soru = soru;
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
     * @return the puan
     */
    public int getPuan() {
        return puan;
    }

    /**
     * @param puan the puan to set
     */
    public void setPuan(int puan) {
        this.puan = puan;
    }

  
    
}
