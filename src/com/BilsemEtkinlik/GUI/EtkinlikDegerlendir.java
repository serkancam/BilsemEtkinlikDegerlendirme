/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BilsemEtkinlik.GUI;
import com.BilsemEtkinlik.Is.*;
import com.BilsemEtkinlik.DAO.*;
import com.BilsemEtkinlik.Entity.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.lang.Object;
import java.sql.Date;


/**
 *
 * @author serkancam
 */
public class EtkinlikDegerlendir extends javax.swing.JInternalFrame {
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
        pnlRaporlama = new javax.swing.JPanel();
        btnRaporla = new javax.swing.JButton();
        lblRaporlamaBilgi = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1062, 485));
        setMinimumSize(new java.awt.Dimension(1062, 485));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
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
        tblDegerlendirme.setShowHorizontalLines(true);
        tblDegerlendirme.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tblDegerlendirme);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 175, 1270, 280));

        pnlSecim.setBackground(new java.awt.Color(204, 204, 255));
        pnlSecim.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbDersler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ders Seç" }));
        cbDersler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDerslerActionPerformed(evt);
            }
        });
        pnlSecim.add(cbDersler, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 280, 30));

        cbModuller.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Modül Seç" }));
        cbModuller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbModullerActionPerformed(evt);
            }
        });
        pnlSecim.add(cbModuller, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 280, 30));

        cbEtkinlikler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Etkinlik Seç" }));
        cbEtkinlikler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEtkinliklerActionPerformed(evt);
            }
        });
        pnlSecim.add(cbEtkinlikler, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 280, 30));

        cbSiniflar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sınıf Seç" }));
        cbSiniflar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSiniflarActionPerformed(evt);
            }
        });
        pnlSecim.add(cbSiniflar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 280, 30));

        btnPuanlariAktar.setText("Paunları Kaydet");
        btnPuanlariAktar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPuanlariAktarActionPerformed(evt);
            }
        });
        pnlSecim.add(btnPuanlariAktar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 150, 50));

        lblBilgi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBilgi.setText("İşlem Bilgisi:");
        lblBilgi.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlSecim.add(lblBilgi, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 150, 70));

        getContentPane().add(pnlSecim, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 170));

        pnlRaporlama.setBackground(new java.awt.Color(204, 204, 255));
        pnlRaporlama.setToolTipText("");
        pnlRaporlama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRaporla.setText("Tablodaki Notları Raporla");
        btnRaporla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRaporlaActionPerformed(evt);
            }
        });
        pnlRaporlama.add(btnRaporla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, 50));

        lblRaporlamaBilgi.setText("Raporlama Bilgisi");
        lblRaporlamaBilgi.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlRaporlama.add(lblRaporlamaBilgi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 730, 70));

        getContentPane().add(pnlRaporlama, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 760, 170));

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
                        not.setPuan(Integer.parseInt(model.getValueAt(i, j).toString().trim()));
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

    private void btnRaporlaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRaporlaActionPerformed
        // TODO add your handling code here:
        
        //Raporlama burada olacak.
        
        boolean islem=true;
        DefaultTableModel model= (DefaultTableModel) tblDegerlendirme.getModel();
        if(cbSiniflar.getSelectedIndex()<1)
        {
            lblRaporlamaBilgi.setText("<html>Raporlama Bilgisi:Raporlama için gerkeli seçimleri yapınız</html>");
            return;
            
        } 
        for (int i = 1; i < model.getRowCount(); i++) 
        {
            for(int j=1;j<model.getColumnCount();j++)
            {
                if(  model.getValueAt(i,j).toString().isEmpty())
                {
                    lblRaporlamaBilgi.setText("<html>Raporlama Bilgisi:Notu olmayan öğrenci mevcut.</html>");
                    islem=false;
                    return;
                }
                
            }
        }
             
        Object[] ilkSatir=new Object[model.getColumnCount()];
        int soruId,ogrenciNo;
        int toplamİslem=(model.getRowCount()-1)*(model.getColumnCount()-1);
        int sayac=0;
        final String yol = System.getProperty("user.dir");//programın çalışma yolunuz alıyoruz
        
       
        String dosyaYolu=cbSiniflar.getSelectedItem().toString()+"/"+cbEtkinlikler.getSelectedItem().toString().split("-")[0]+"/";
        String[] yollar=dosyaYolu.split("/");
        String olusan=yol;
        
        System.out.println(dosyaYolu);
        
        try
        {
            for(String ac:yollar)
            {
                olusan+="/"+ac+"/";
                File dir = new File(olusan);
                if(dir.exists())
                {
                    continue;
                }
                dir.mkdir();
            
            }
            
            for (int t = 1; t < model.getRowCount(); t++) 
            {
                
               Document dosya=new Document();

               PdfWriter.getInstance(dosya, new FileOutputStream(dosyaYolu+model.getValueAt(t, 0).toString().split("-")[1]+".pdf"));
               dosya.open();
               //PdfContentByte cb = pdfWr.getDirectContent();
               BaseFont bf =BaseFont.createFont(BaseFont.TIMES_ROMAN,"iso-8859-9",BaseFont.EMBEDDED);
               //cb.saveState();
                //cb.setFontAndSize(bf, 12);
                Font fontNormal = new Font(bf, 10, Font.NORMAL);

                int[] boyut={80,4,4,4,4,4};
                dosya.add(new Paragraph("Adı Soyadı:"+model.getValueAt(t, 0).toString().split("-")[1],fontNormal));

                dosya.add(new Paragraph("Sınıfı:"+cbSiniflar.getSelectedItem().toString().split("-")[1],fontNormal));
                dosya.add(new Paragraph(""));
                dosya.add(new Paragraph("tarih:",fontNormal));
                dosya.add(new Paragraph(""));
                dosya.add(new Paragraph("Yönerge:Her bir Öğrenci için ders içi genel preformans değerlendirme amaçlı olarak 10 ",fontNormal));
                dosya.add(new Paragraph("değerlendime ölçütü bulunmaktadır. Her bir ölçüt için 1’den 5’e kadar derecelendirme yapılır.",fontNormal));
                dosya.add(new Paragraph(" "));
                dosya.add(new Paragraph(" "));
                PdfPTable tablo=new PdfPTable(6);
                tablo.setWidths(boyut);
               
                PdfPCell hucre1=new PdfPCell(new Paragraph(CENTER_ALIGNMENT, "Etkinlik Sonu Değerlendirme Ölçeği",fontNormal));
                 hucre1.setColspan(6);
                tablo.addCell(hucre1);
                PdfPCell hucre2=new PdfPCell(new Paragraph(CENTER_ALIGNMENT, ""));
                hucre2.setColspan(6);
                tablo.addCell(hucre2);
                PdfPCell hucre3=new PdfPCell(new Paragraph(CENTER_ALIGNMENT, "1:Çok    zayıf    2:zayıf    3:Orta   4:İyi    5:Çok iyi",fontNormal));
                hucre3.setColspan(6);
                tablo.addCell(hucre3);
                tablo.addCell("");     
                tablo.addCell("1"); 
                tablo.addCell("2"); 
                tablo.addCell("3"); 
                tablo.addCell("4"); 
                tablo.addCell("5");
               for (int i = 1; i < model.getColumnCount(); i++) 
                {
                    PdfPCell soruHucresi=new PdfPCell(new Paragraph(CENTER_ALIGNMENT, model.getValueAt(0, i).toString().split("-")[1],fontNormal));
                    tablo.addCell(soruHucresi);
                    int deger=Integer.parseInt(model.getValueAt(t, i).toString().trim());
                    if(deger==1)
                    {
                     tablo.addCell("X");   
                    }
                    else
                    {
                        tablo.addCell(" ");
                    }
                    if(deger==2)
                    {
                     tablo.addCell("X");   
                    }
                    else
                    {
                        tablo.addCell(" ");
                    }
                    if(deger==3)
                    {
                     tablo.addCell("X");   
                    }
                    else
                    {
                        tablo.addCell(" ");
                    }
                    if(deger==4)
                    {
                     tablo.addCell("X");   
                    }
                    else
                    {
                        tablo.addCell(" ");
                    }
                    if(deger==5)
                    {
                     tablo.addCell("X");   
                    }
                    else
                    {
                        tablo.addCell(" ");
                    }
                    
                    
                
                }
                dosya.add(tablo);
                dosya.close();
                
            }
            
            
            
            
           
           
            
            
        }
        catch(Exception ex)
        {
            lblRaporlamaBilgi.setText("<html>"+"Raporlama Bilgisi: Hata-->"+ex.getMessage()+"</html>");
        }
        
       
       

        
    }//GEN-LAST:event_btnRaporlaActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        DerslerCbDoldur();
    }//GEN-LAST:event_formInternalFrameOpened

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
    private javax.swing.JButton btnRaporla;
    private javax.swing.JComboBox<String> cbDersler;
    private javax.swing.JComboBox<String> cbEtkinlikler;
    private javax.swing.JComboBox<String> cbModuller;
    private javax.swing.JComboBox<String> cbSiniflar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBilgi;
    private javax.swing.JLabel lblRaporlamaBilgi;
    private javax.swing.JPanel pnlRaporlama;
    private javax.swing.JPanel pnlSecim;
    private javax.swing.JTable tblDegerlendirme;
    // End of variables declaration//GEN-END:variables
}
