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
public class EtkinlikSorulariDAO extends DAO
{
    private final String sil = "DELETE FROM [etkinlikSorulari] WHERE [soruId]=?";
    private final String tumListeyiGetir= "SELECT * FROM [etkinlikSorulari] ORDER BY [soruId]";
    private final String etkinlikIdyeGoreGetir = "SELECT * FROM [etkinlikSorulari] WHERE [etkinlikId]=? order by [soruId] asc";
    private final String sinifEtkinligiSorulariniGetir="select * from [etkinlikSorulari] as es inner join [etkinlikler] as e "
            + "on es.[etkinlikId]=e.[etkinlikId] inner join [sinifinEtkinklileri] as se on e.[etkinlikId]=se.[etkinlikId] "
            + "inner join [sinilar] as s on se.[sinifId]=s.[sinifId] inner join [ogrenciler] as o "
            + "on s.[sinifId]=o.sinifId where e.[etkinlikId]=? and s.[sinifId]=?";
    private final String ekle = "INSERT INTO [etkinlikSorulari]([etkinlikId],[soru]) VALUES(?,?)";
    private final String guncelle = "UPDATE [etkinlikSorulari] SET [etkinlikId]=?, [soru]=? WHERE [soruId]=?";
    
     public int Sil(int soruId) 
        {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try 
                {
			conn = getConnection();
			stmt = conn.prepareStatement(sil);
			stmt.setInt(1, soruId);			
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
        
        public List<EtkinlikSorulari> TumListeyiGetir() 
        {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<EtkinlikSorulari> list = new ArrayList<EtkinlikSorulari>();
		
		try 
                {
			conn = getConnection();
			stmt = conn.prepareStatement(tumListeyiGetir);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				EtkinlikSorulari soru = new EtkinlikSorulari();
                                Etkinlikler etkinlik=new Etkinlikler();
				soru.setSoruId(rs.getInt("soruId"));
				soru.setSoru(rs.getString("soru"));
                                etkinlik.setEtkinlikId(rs.getInt("etkinlikId"));
                                soru.setEtkinlik(etkinlik);
				list.add(soru);
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
        
        public List<EtkinlikSorulari> EtkinlikIdyeGoreGetir(int aranan) 
        {
		Connection conn = null;
                PreparedStatement stmt = null;
		
		List<EtkinlikSorulari> list = new ArrayList<EtkinlikSorulari>();
		
		try 
                {
			conn = getConnection();                        
			stmt = conn.prepareStatement(etkinlikIdyeGoreGetir);                        
                        stmt.setInt(1, aranan);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				EtkinlikSorulari soru = new EtkinlikSorulari();
                                Etkinlikler etkinlik=new Etkinlikler();
				soru.setSoruId(rs.getInt("soruId"));
				soru.setSoru(rs.getString("soru"));
                                etkinlik.setEtkinlikId(rs.getInt("etkinlikId"));
                                soru.setEtkinlik(etkinlik);
				list.add(soru);
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
