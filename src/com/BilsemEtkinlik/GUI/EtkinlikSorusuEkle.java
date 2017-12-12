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

//combo seçimlerinde sorun var her seçimde gelen değere göre dolruma işlemi yapıp yapmamaya karar vermek lazım
//itemstatechanged dene

/**
 *
 * @author serkancam
 */
public class EtkinlikSorusuEkle extends javax.swing.JFrame {

    int secilenDers,secilenModul,secilenEtkinlik=-1;
    /**
     * Creates new form EtkinlikSorusuEkle
     */
    public EtkinlikSorusuEkle() 
    {
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
    int OgrenciyeAitSoruPuaniniGetir(int ogrenciNo,int soruNo)
    {
        int puan=-1;
        
        
        return  puan;
    }
    
    void SorularTablosuDoldur()
    {
        TabloIslemleri.TabloTemizle(tblSoruEkle);
        List<EtkinlikSorulari> sorular =null;
        EtkinlikSorulariDAO islem=new EtkinlikSorulariDAO();
            
       sorular=islem.EtkinlikIdyeGoreGetir(secilenEtkinlik);
       DefaultTableModel model= (DefaultTableModel) tblSoruEkle.getModel();
        for (EtkinlikSorulari soru : sorular) 
        {
            
            model.addRow(new Object[]{false,soru.getSoruId(),soru.getSoru()});
            
            
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
        tblSoruEkle = new javax.swing.JTable();
        cbDersler = new javax.swing.JComboBox<>();
        cbModuller = new javax.swing.JComboBox<>();
        cbEtkinlikler = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSorulariEkle = new javax.swing.JButton();
        btnSorulariSil = new javax.swing.JButton();
        btnSorulariGuncelle = new javax.swing.JButton();
        lblBilgi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblSoruEkle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Seç", "SoruId", "Soru"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSoruEkle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblSoruEkleKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSoruEkle);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 166, 629, 336));

        cbDersler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ders Seç" }));
        cbDersler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDerslerActionPerformed(evt);
            }
        });
        getContentPane().add(cbDersler, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 31, 154, 30));

        cbModuller.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modül Seç" }));
        cbModuller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbModullerActionPerformed(evt);
            }
        });
        getContentPane().add(cbModuller, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 31, 220, 30));

        cbEtkinlikler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Etkinlik Seç" }));
        cbEtkinlikler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEtkinliklerActionPerformed(evt);
            }
        });
        getContentPane().add(cbEtkinlikler, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 31, 199, 30));

        jLabel1.setText("Ders Seç");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel2.setText("Modül Seç");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 11, -1, -1));

        jLabel3.setText("Etkinlik Seç");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 11, -1, -1));

        btnSorulariEkle.setText("Soruları Ekle");
        btnSorulariEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSorulariEkleActionPerformed(evt);
            }
        });
        getContentPane().add(btnSorulariEkle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 87, 154, -1));

        btnSorulariSil.setText("Seçilen soruları sil");
        btnSorulariSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSorulariSilActionPerformed(evt);
            }
        });
        getContentPane().add(btnSorulariSil, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 87, 220, -1));

        btnSorulariGuncelle.setText("Seçilen Soruları Güncelle");
        btnSorulariGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSorulariGuncelleActionPerformed(evt);
            }
        });
        getContentPane().add(btnSorulariGuncelle, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 87, 199, -1));

        lblBilgi.setText("işlem Bilgisi:");
        getContentPane().add(lblBilgi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 128, 609, 27));

        pack();
    }// </editor-fold>//GEN-END:initComponents

     
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
       // TabloIslemleri tbl=new TabloIslemleri();
        int[] b={80,80,800};
        TabloIslemleri.KolonBoyutuAyarla(tblSoruEkle, b);
       
        DerslerCbDoldur();
       
    }//GEN-LAST:event_formWindowOpened

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
        TabloIslemleri.TabloTemizle(tblSoruEkle);
        secilenEtkinlik=-1;
        if(cbEtkinlikler.getSelectedIndex()>0)
        { 
            secilenEtkinlik=Integer.parseInt(cbEtkinlikler.getSelectedItem().toString().split("-")[0]);
            SorularTablosuDoldur();
        }
         DefaultTableModel model= (DefaultTableModel) tblSoruEkle.getModel();
         model.addRow(new Object[]{false,-1,""});
        
    }//GEN-LAST:event_cbEtkinliklerActionPerformed

    private void btnSorulariSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSorulariSilActionPerformed
        // TODO add your handling code here:
        List<Integer> secilenler=TabloIslemleri.SecilenListesiDondur(tblSoruEkle);
        EtkinlikSorulariDAO islem=new EtkinlikSorulariDAO();
        int i=0;
        for(Integer sil:secilenler)
        {
            if(islem.Sil(sil)>0)
            {i++;}
        }
        if(i==secilenler.size())
        {lblBilgi.setText("Bilgi: Silme işlemi Başarı ile Gerçekleştirildi");}
        
        SorularTablosuDoldur();
        
       
    }//GEN-LAST:event_btnSorulariSilActionPerformed

    private void tblSoruEkleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSoruEkleKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        {
            DefaultTableModel model= (DefaultTableModel) tblSoruEkle.getModel();
            model.addRow(new Object[]{false,-1,""});
           /** 
           tblSoruEkle.editCellAt(model.getRowCount()-1, 2);
           tblSoruEkle.setSurrendersFocusOnKeystroke(true);
           tblSoruEkle.getEditorComponent().requestFocus();
            **/
            
        }
        
    }//GEN-LAST:event_tblSoruEkleKeyPressed

    private void btnSorulariEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSorulariEkleActionPerformed
        // TODO add your handling code here:
        if(secilenEtkinlik>0)
        {
            List<EtkinlikSorulari> sorular=new ArrayList<>();
            EtkinlikSorulariDAO islem=new EtkinlikSorulariDAO();
                    
            DefaultTableModel model= (DefaultTableModel) tblSoruEkle.getModel();
            int rowCount = model.getRowCount();
            
            int t=0;
            for (int i = 0; i <rowCount; i++)
            {
                EtkinlikSorulari soru=new EtkinlikSorulari();
                Etkinlikler et=new Etkinlikler();
                if ((int)model.getValueAt(i, 1)==-1)
                {
                    
                    et.setEtkinlikId(secilenEtkinlik);
                    soru.setEtkinlik(et);
                    soru.setSoru(model.getValueAt(i, 2).toString());
                   
                    sorular.add(soru);
                }
            }
            for(EtkinlikSorulari soru:sorular )
            {
                if(islem.Ekle(soru)>0)
                {
                    t++;
                }
            }
            if(t==sorular.size())
            {
                lblBilgi.setText("Bilgi: ekleme işlemleri başarılı");
            }
            SorularTablosuDoldur();
            
        }
    }//GEN-LAST:event_btnSorulariEkleActionPerformed

    private void btnSorulariGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSorulariGuncelleActionPerformed
        // TODO add your handling code here:
        if(secilenEtkinlik>0)
        {
            List<EtkinlikSorulari> sorular=new ArrayList<>();
            EtkinlikSorulariDAO islem=new EtkinlikSorulariDAO();
                    
            DefaultTableModel model= (DefaultTableModel) tblSoruEkle.getModel();
            int rowCount = model.getRowCount();
            
            int t=0;
            for (int i = 0; i <rowCount; i++)
            {
                EtkinlikSorulari soru=new EtkinlikSorulari();
                Etkinlikler et=new Etkinlikler();
                if ((boolean)model.getValueAt(i, 0)==true && (int)model.getValueAt(i, 1)>0 )
                {
                    
                    et.setEtkinlikId(secilenEtkinlik);
                    soru.setEtkinlik(et);
                    soru.setSoru(model.getValueAt(i, 2).toString());
                    soru.setSoruId((int)model.getValueAt(i, 1));
                   
                    sorular.add(soru);
                }
            }
            for(EtkinlikSorulari soru:sorular )
            {
                if(islem.Guncelle(soru)>0)
                {
                    t++;
                }
            }
            if(t==sorular.size())
            {
                lblBilgi.setText("Bilgi: Güncelleme işlemleri başarılı");
            }
            SorularTablosuDoldur();
        }
        
    }//GEN-LAST:event_btnSorulariGuncelleActionPerformed

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
            java.util.logging.Logger.getLogger(EtkinlikSorusuEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EtkinlikSorusuEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EtkinlikSorusuEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EtkinlikSorusuEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EtkinlikSorusuEkle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSorulariEkle;
    private javax.swing.JButton btnSorulariGuncelle;
    private javax.swing.JButton btnSorulariSil;
    private javax.swing.JComboBox<String> cbDersler;
    private javax.swing.JComboBox<String> cbEtkinlikler;
    private javax.swing.JComboBox<String> cbModuller;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBilgi;
    private javax.swing.JTable tblSoruEkle;
    // End of variables declaration//GEN-END:variables
}
