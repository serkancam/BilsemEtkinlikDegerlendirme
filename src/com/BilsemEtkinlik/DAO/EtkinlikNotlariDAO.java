/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BilsemEtkinlik.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.BilsemEtkinlik.Entity.*;

/**
 *
 * @author serkancam
 */
public class EtkinlikNotlariDAO extends DAO
{
    private final String sil = "DELETE FROM [etkinlikNotlari] WHERE [notId]=?";
    private final String tumListeyiGetir= "SELECT * FROM [etkinlikNotlari] ORDER BY [notId]";
    private final String etkinlikIdyeGoreGetir = "SELECT * FROM [etkinlikNotlari] as en inner join [etkinlikSorulari] as es on en.[soruId]=es.[soruId]"
            + "inner join etkinlikler as e on es.[etkinlikId]=e.[etkinlikId] inner join [ogrenciler] as o on en.[ogrenciId]=o.[ogrenciId]"
            + "inner join [moduller] as m on e.[modulId]=m.[modulId]"
            + "WHERE [etkinlikId]=?";
    private final String ekle = "INSERT INTO [etkinlikNotlari]([soruId],[ogrenciNo],[paun]) VALUES(?,?,?)";
    private final String guncelle = "UPDATE [etkinlikNotlari] SET [etkinlikId]=?, [soru]=? WHERE [soruId]=?";
    
     public int Sil(int notId) 
     {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try 
                {
			conn = getConnection();
			stmt = conn.prepareStatement(sil);
			stmt.setInt(1, notId);			
			return stmt.executeUpdate();
                        
		} 
                catch (SQLException e) 
                {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} 
                finally 
                {
			close(stmt);
			close(conn);
		}
	}
        
               
        public List<EtkinlikNotlari> EtkinlikIdyeGoreGetir(Etkinlikler arananEtkinlik) 
        {
		Connection conn = null;
                PreparedStatement stmt = null;
		
		List<EtkinlikNotlari> list = new ArrayList<EtkinlikNotlari>();
		
		try 
                {
			conn = getConnection();                        
			stmt = conn.prepareStatement(etkinlikIdyeGoreGetir);                        
                        stmt.setInt(1, arananEtkinlik.getEtkinlikId());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				EtkinlikNotlari not = new EtkinlikNotlari();
                                EtkinlikSorulari soru=new EtkinlikSorulari();
                                Etkinlikler etkinlik = new Etkinlikler();
                                Ogrenciler ogrenci= new Ogrenciler();
                                Moduller modul=new Moduller();
                                modul.setModulId(rs.getInt("modulId"));
                                
                                etkinlik.setEtkinlikAdi("etkinlikAdi");
                                etkinlik.setEtkinlikId(rs.getInt("etkinlikId"));
                                etkinlik.setModul(modul);
                                        
                                soru.setSoruId(rs.getInt("soruId"));
                                soru.setEtkinlik(etkinlik);
                                soru.setSoru(rs.getString("soru"));
                                
                                ogrenci.setOgrenciAdi(rs.getString("ogrenciAdi"));
                                ogrenci.setOgrenciSoyadi(rs.getString("ogrenciSoyadi"));
                                
                                not.setNotId(rs.getInt("notId"));
                                not.setOgrenci(ogrenci);
                                not.setPuan(rs.getInt("puan"));
                                not.setSoru(soru);
                                                           
				
				list.add(not);
			}
		}
                catch (SQLException e) 
                {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} 
                finally 
                {
			close(stmt);
			close(conn);
		}
		
		return list;
	}
        
        
        public int Ekle(EtkinlikSorulari soru)
        {
            Connection conn = null;
            PreparedStatement stmt = null;
		
		try 
                {
                    conn = getConnection();
                    stmt = conn.prepareStatement(ekle);
                    stmt.setInt(1, soru.getEtkinlik().getEtkinlikId());
                    stmt.setString(2, soru.getSoru());                 
                 	                       
                    int result = stmt.executeUpdate();	
                    return result;
		}
                catch (SQLException e) 
                {
			// e.printStackTrace();
                    throw new RuntimeException(e);
		} 
                finally 
                {
                    close(stmt);
                    close(conn);
		}

            
        }
        
         public int Guncelle(EtkinlikSorulari soru)
        {
            Connection conn = null;
            PreparedStatement stmt = null;
		
		try 
                {
                    conn = getConnection();
                    stmt = conn.prepareStatement(guncelle);
                    stmt.setInt(1, soru.getEtkinlik().getEtkinlikId());
                    stmt.setString(2, soru.getSoru());
                    stmt.setInt(3, soru.getSoruId());	
                        
                    int result = stmt.executeUpdate();	
                    return result;
		}
                catch (SQLException e) 
                {
			// e.printStackTrace();
                    throw new RuntimeException(e);
		} 
                finally 
                {
                    close(stmt);
                    close(conn);
		}

            
        }
    
}
