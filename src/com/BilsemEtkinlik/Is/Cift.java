/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BilsemEtkinlik.Is;

/**
 *
 * @author serkancam
 */
public class Cift 
{
    public int key;
    public String value;
    
    public Cift(int k, java.lang.String v)
    {
        this.key=k;
        this.value=v;
    }

   

    @Override
    public String toString() 
    {
        return key+"-"+value; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
