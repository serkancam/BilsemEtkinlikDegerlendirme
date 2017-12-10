/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BilsemEtkinlik.GUI;
import com.BilsemEtkinlik.Is.*;
import com.BilsemEtkinlik.DAO.*;
import com.BilsemEtkinlik.Entity.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.lang.Object;
import java.util.Vector;
import javax.swing.table.TableModel;
import javax.swing.text.TableView.TableRow;
/**
 *
 * @author serkancam
 */
public class EtkinlikDegerlendir extends javax.swing.JFrame {
    int secilenDers=-1,secilenModul=-1,secilenEtkinlik=-1,secilenSinif=-1,etkinklikSoruSayisi=-1;

    /**
     * Creates new form EtkinlikDegerlendir
     */
    public EtkinlikDegerlendir() {
        initComponents();
    }
     void DerslerCbDoldur()
    {
      
        List<Dersler> dersler =null;
       Dersler d=new Dersler();
       DerslerDAO islem=new DerslerDAO();
       dersler=islem.TumDersleriGetir();
      
        for (Dersler ders : dersler) 
        {
            
           // Cift c=;
            cbDersler.addItem(new Cift(ders.getDersId(),ders.getDersAdi()).toString());
           
        }
        
    }
    void EtkinliklerCbDoldur()
    {
     
       List<Etkinlikler> etkinlikler =null;
       Etkinlikler et=new Etkinlikler();
       Moduller mdl=new Moduller();
       mdl.setModulId(secilenModul);
       et.setModul(mdl);
       EtkinliklerDAO islem=new EtkinliklerDAO();
       etkinlikler=islem.ModulIdyeGoreGetir(et);
      
        for (Etkinlikler etl : etkinlikler) 
        {
            
           // Cift c=;
            cbEtkinlikler.addItem(new Cift(etl.getEtkinlikId(),etl.getEtkinlikAdi()).toString());
            
            
        }
        
    }
    void ModullerCbDoldur()
    {
     
      
       List<Moduller> moduller =null;
       Moduller m=new Moduller();
       Dersler ders=new Dersler();
       ders.setDersId(secilenDers);
       m.setDers(ders);
       ModullerDAO islem=new ModullerDAO();
       moduller=islem.EtkinlikIdyeGoreGetir(m);
      
        for (Moduller mdl : moduller) 
        {
            
           // Cift c=;
            cbModuller.addItem(new Cift(mdl.getModulId(),mdl.getModulAdi()).toString());
            
            
        }
        
    }
    void SiniflarCbDoldur()
    {
        List<SinifEtkinlikleri> sinifEtkinlik =null;
        SinifEtkinlikleriDAO islem=new SinifEtkinlikleriDAO();
            
       sinifEtkinlik=islem.EtkinlikIdyeGoreGetir(secilenEtkinlik);
       
       for (SinifEtkinlikleri sinifEtk : sinifEtkinlik) 
        {
            
           cbSiniflar.addItem(new Cift(sinifEtk.getSinif().getSinifId(),sinifEtk.getSinif().getSinifKodu()).toString());
            
            
        }
        //
     
               
    }
    
    void EtkinligeAitSorulariGetir()
    {
        TabloIslemleri.TabloTemizle(tblDegerlendirme);
        
        if(secilenSinif>0)
        {
            
            List<EtkinlikSorulari> sorular=null;
            EtkinlikSorulariDAO islem=new EtkinlikSorulariDAO();
            sorular=islem.EtkinlikIdyeGoreGetir(secilenEtkinlik);
            etkinklikSoruSayisi=sorular.size();
            Object[] satirModeli=new Object[etkinklikSoruSayisi+1];
            Object[] eklenen=new Object[sorular.size()+1];
            TabloIslemleri.TabloModeliAyarla(tblDegerlendirme, satirModeli);
            DefaultTableModel model= (DefaultTableModel) tblDegerlendirme.getModel();
            eklenen[0]="";
            int i=1;
            if(sorular.size()<1){lblBilgi.setText("<html>işlem Bilsi: Etkinliğe ait \nsoru yok.</html>");}
            for(EtkinlikSorulari soru:sorular)
            {
                eklenen[i]=new Cift(soru.getSoruId(), soru.getSoru()).toString();
                i++;
            }
            model.addRow(eklenen);
            
            
            
            
        }
    }
    
    void OgrencilereNotlariniTabloyaEkle()
    {
        /**
        burada kaldın
        ilk önce örenci listesini getir
        her öğrenci için etkinliğe ait puanları getir ve 
        saır satır tabloya yaz
        **/
        
        if(secilenEtkinlik<1){return;}
        
        List<Ogrenciler> ogrenciler=null;
        OgrencilerDAO ogrenciISlem=new OgrencilerDAO();
        EtkinlikNotlariDAO notIslem=new EtkinlikNotlariDAO();
        ogrenciler=ogrenciISlem.SinifinOgrencileriniGetir(secilenSinif);
        DefaultTableModel model= (DefaultTableModel) tblDegerlendirme.getModel();
        
        
        for(Ogrenciler ogrenci:ogrenciler)
        {
            Object[] eklenen = new Object[etkinklikSoruSayisi+1];
            List<EtkinlikNotlari> notlar=notIslem.OgrenciEtkinligineAitNotlariGetir(ogrenci.getOgrenciNo(), secilenEtkinlik);
            eklenen[0]=new Cift(ogrenci.getOgrenciNo(),ogrenci.getOgrenciAdi()+" "+ogrenci.getOgrenciSoyadi()).toString();
            System.out.println("com.BilsemEtkinlik.GUI.EtkinlikDegerlendir.OgrencilereNotlariniTabloyaEkle()");
           // System.out.println("com.BilsemEtkinlik.GUI.EtkinlikDegerlendir.OgrencilereNotlariniTabloyaEkle()");
           
          
            for (int i = 1; i < eklenen.length; i++) 
            {
                if(i<=notlar.size())
                {eklenen[i]=notlar.get(i-1).getPuan();}
                else
                {eklenen[i]="";}
                
            }
            model.addRow(eklenen);
            
            
        }
                
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDegerlendirme = new javax.swing.JTable();
        pnlSecim = new javax.swing.JPanel();
        cbDersler = new javax.swing.JComboBox<>();
        cbModuller = new javax.swing.JComboBox<>();
        cbEtkinlikler = new javax.swing.JComboBox<>();
        cbSiniflar = new javax.swing.JComboBox<>();
        btnPuanlariAktar = new javax.swing.JButton();
        lblBilgi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1062, 485));
        setMinimumSize(new java.awt.Dimension(1062, 485));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDegerlendirme.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDegerlendirme);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 165, 1270, 320));

        pnlSecim.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbDersler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ders Seç" }));
        cbDersler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDerslerActionPerformed(evt);
            }
        });
        pnlSecim.add(cbDersler, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 249, -1));

        cbModuller.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modül Seç" }));
        cbModuller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbModullerActionPerformed(evt);
            }
        });
        pnlSecim.add(cbModuller, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 249, -1));

        cbEtkinlikler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Etkinlik Seç" }));
        cbEtkinlikler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEtkinliklerActionPerformed(evt);
            }
        });
        pnlSecim.add(cbEtkinlikler, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 249, -1));

        cbSiniflar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sınıf Seç" }));
        cbSiniflar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSiniflarActionPerformed(evt);
            }
        });
        pnlSecim.add(cbSiniflar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 249, -1));

        btnPuanlariAktar.setText("Paunları Kaydet");
        btnPuanlariAktar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPuanlariAktarActionPerformed(evt);
            }
        });
        pnlSecim.add(btnPuanlariAktar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 180, 30));

        lblBilgi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBilgi.setText("İşlem Bilgisi:");
        lblBilgi.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlSecim.add(lblBilgi, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 170, 70));

        getContentPane().add(pnlSecim, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 160));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbDerslerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDerslerActionPerformed
        // TODO add your handling code here:
        TabloIslemleri.ComboBoxTemizle(cbModuller, "Modül Seç");
        secilenDers=-1;               
        
       if(cbDersler.getSelectedIndex()>0)
       {
        secilenDers=Integer.parseInt(cbDersler.getSelectedItem().toString().split("-")[0]);
        ModullerCbDoldur();
       }
    }//GEN-LAST:event_cbDerslerActionPerformed

    private void cbModullerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbModullerActionPerformed
        // TODO add your handling code here:
        System.out.println("com.BilsemEtkinlik.GUI.EtkinlikSorusuEkle.cbModullerActionPerformed()");
       
        TabloIslemleri.ComboBoxTemizle(cbEtkinlikler, "Etkinlik Seç");
        secilenModul=-1;
        
        if(cbModuller.getSelectedIndex()>0)
        {
            secilenModul=Integer.parseInt(cbModuller.getSelectedItem().toString().split("-")[0]);
            EtkinliklerCbDoldur();
        }
    }//GEN-LAST:event_cbModullerActionPerformed

    private void cbEtkinliklerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEtkinliklerActionPerformed
        // TODO add your handling code here:
        System.out.println("com.BilsemEtkinlik.GUI.EtkinlikSorusuEkle.cbEtkinliklerActionPerformed()");
       TabloIslemleri.ComboBoxTemizle(cbSiniflar, "Sınıf Seç");
        secilenEtkinlik=-1;
        if(cbEtkinlikler.getSelectedIndex()>0)
        { 
            secilenEtkinlik=Integer.parseInt(cbEtkinlikler.getSelectedItem().toString().split("-")[0]);
            SiniflarCbDoldur();
        }
         
    }//GEN-LAST:event_cbEtkinliklerActionPerformed

    private void cbSiniflarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSiniflarActionPerformed
        // TODO add your handling code here:
        System.out.println("com.BilsemEtkinlik.GUI.EtkinlikDegerlendir.cbSiniflarActionPerformed()");
        secilenSinif=-1;
        if (cbSiniflar.getSelectedIndex()>0) 
        {
           secilenSinif=Integer.parseInt(cbSiniflar.getSelectedItem().toString().split("-")[0]);
           EtkinligeAitSorulariGetir();
            OgrencilereNotlariniTabloyaEkle();
            
        }
        else
        {
            TabloIslemleri.TabloTemizle(tblDegerlendirme);
        }
        
    }//GEN-LAST:event_cbSiniflarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        DerslerCbDoldur();
    }//GEN-LAST:event_formWindowOpened

    private void btnPuanlariAktarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPuanlariAktarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model= (DefaultTableModel) tblDegerlendirme.getModel();
        
        
        Object[] ilkSatir=new Object[model.getColumnCount()];
        int soruId,ogrenciNo;
        int toplamİslem=(model.getRowCount()-1)*(model.getColumnCount()-1);
        int sayac=0;
        
        if(etkinklikSoruSayisi>0 && model.getRowCount()>1)
        {
            for (int i = 1; i < model.getRowCount(); i++) 
            {
                
                ogrenciNo=Integer.parseInt( model.getValueAt(i, 0).toString().split("-")[0]);
                for (int j = 1; j < model.getColumnCount(); j++) 
                {
                    List<EtkinlikNotlari> notlar=null;
                    EtkinlikNotlari not=new EtkinlikNotlari();
                    EtkinlikSorulari soru=new EtkinlikSorulari();
                    Ogrenciler ogrenci=new Ogrenciler();
                    EtkinlikNotlariDAO islem=new EtkinlikNotlariDAO();
                    soruId=Integer.parseInt( model.getValueAt(0, j).toString().split("-")[0]);
                    
                    notlar=islem.OgrenciyeAitPuaniGetir(ogrenciNo, soruId);
                    if(notlar.size()>0 && (model.getValueAt(i, j).toString())!="")
                    {
                        //güncelle
                        not.setNotId(notlar.get(0).getNotId());
                        not.setPuan(Integer.parseInt(model.getValueAt(i, j).toString()));
                        if(islem.Guncelle(not)>0)
                        {
                            sayac++;
                        }
                    }
                    else if(notlar.size()==0 && (model.getValueAt(i, j).toString())!="")
                    {
                        //ekle
                        ogrenci.setOgrenciNo(ogrenciNo);
                        soru.setSoruId(soruId);
                        not.setPuan(Integer.parseInt(model.getValueAt(i, j).toString()));
                        not.setOgrenci(ogrenci);
                        not.setSoru(soru);
                        if(islem.Ekle(not)>0)
                        {
                            sayac++;
                        }
                        
                    }
                    else if((model.getValueAt(i, j).toString())=="")
                    {
                        sayac++;
                    }
                        
                    
                    
                    
                    
                }
                
            }
            if(sayac==toplamİslem)
            {
                lblBilgi.setText("<html>İşlem Bilgisi: Ekleme ve Güncelleme işlemleri yapıldı</html>");
            }
            
        }
    }//GEN-LAST:event_btnPuanlariAktarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EtkinlikDegerlendir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EtkinlikDegerlendir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EtkinlikDegerlendir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EtkinlikDegerlendir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EtkinlikDegerlendir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPuanlariAktar;
    private javax.swing.JComboBox<String> cbDersler;
    private javax.swing.JComboBox<String> cbEtkinlikler;
    private javax.swing.JComboBox<String> cbModuller;
    private javax.swing.JComboBox<String> cbSiniflar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBilgi;
    private javax.swing.JPanel pnlSecim;
    private javax.swing.JTable tblDegerlendirme;
    // End of variables declaration//GEN-END:variables
}
