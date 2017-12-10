/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BilsemEtkinlik.Is;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JComboBox;
import javax.swing.table.TableModel;

/**
 *
 * @author serkancam
 */
public class TabloIslemleri 
{
    public static void KolonBoyutuAyarla(JTable tablo,int[] boyut)
    {
        TableColumn kolon=null;
        for (int i = 0; i < boyut.length; i++) 
        {
            kolon = tablo.getColumnModel().getColumn(i);
           
                
                kolon.setPreferredWidth(boyut[i]);
            
        }
        
        
    }
    
    public static void TabloTemizle(JTable tablo)
    {
        DefaultTableModel model= (DefaultTableModel) tablo.getModel();
        int rowCount = model.getRowCount();
//Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--)
        {
             model.removeRow(i);
        }
    }
    
    public static List<Integer> SecilenListesiDondur(JTable tablo)
    {
        List<Integer> secilenler=new ArrayList<>();
        DefaultTableModel model= (DefaultTableModel) tablo.getModel();
        int rowCount = model.getRowCount();
        
//Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--)
        {
            if ((boolean)model.getValueAt(i, 0)==true && (int)model.getValueAt(i, 1)>0)
            {
                secilenler.add((int)model.getValueAt(i, 1));
            }
        }
        
        return secilenler;
    }

       
    public static void ComboBoxTemizle(JComboBox kombo,String baslik)
    {
        
        kombo.removeAllItems();
        kombo.addItem(baslik);
        kombo.setSelectedIndex(0);
        
    }
    
    public static void TabloModeliAyarla(JTable tablo,Object[] satir )
    {
        TableModel model =new DefaultTableModel(satir, 0)
        {
             @Override
            public boolean isCellEditable(int row, int column) 
            {
       //all cells false
                    if(row==0 || column==0)
                        return false;
                    else
                        return true;
            }
        };
        
        tablo.setModel(model);
    }
}
