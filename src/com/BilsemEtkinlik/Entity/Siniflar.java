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
public class Siniflar 
{
    private int sinifId;
    private String sinifKodu;
    private Programlar program;

    /**
     * @return the sinifId
     */
    public int getSinifId() {
        return sinifId;
    }

    /**
     * @param sinifId the sinifId to set
     */
    public void setSinifId(int sinifId) {
        this.sinifId = sinifId;
    }

    /**
     * @return the sinifKodu
     */
    public String getSinifKodu() {
        return sinifKodu;
    }

    /**
     * @param sinifKodu the sinifKodu to set
     */
    public void setSinifKodu(String sinifKodu) {
        this.sinifKodu = sinifKodu;
    }

    /**
     * @return the program
     */
    public Programlar getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(Programlar program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return this.sinifKodu; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
