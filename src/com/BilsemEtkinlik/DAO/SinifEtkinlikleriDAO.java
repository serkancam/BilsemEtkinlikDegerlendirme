/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BilsemEtkinlik.DAO;

import com.BilsemEtkinlik.Entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serkancam
 */
public class SinifEtkinlikleriDAO extends DAO
{
    private final String sil = "DELETE FROM [sinifinEtkinklileri] WHERE [sinifId]=? and [etkinlikId]=?";
    private final String tumListeyiGetir= "SELECT * FROM [sinifinEtkinklileri] as s inner join [programlar] as p "
            + "on s.[programId]=p.[programId] order by p.[programId] ";
    private final String etkinlikIdyeGoreGetir = "SELECT * FROM [sinifinEtkinklileri] as se inner join [siniflar] as s "
            + "on se.[sinifId]=s.[sinifId] inner join [etkinlikler] as e "
            + "on se.[etkinlikId]=e.[etkinlikId] WHERE [etkinlikId]=?";
    private final String ekle = "INSERT INTO [sinifinEtkinklileri]([sinifId],[etkinlikId]) VALUES(?,?)";
    private final String guncelle = "UPDATE [sinifinEtkinklileri] SET [sinifId]=?, [etkinlikId]=? WHERE [sinifEtkinlikleriId]=?";
    
    public List<SinifEtkinlikleri> EtkinlikIdyeGoreGetir(int aranan) 
        {
		Connection conn = null;
                PreparedStatement stmt = null;
		
		List<SinifEtkinlikleri> list = new ArrayList<SinifEtkinlikleri>();
		
		try 
                {
			conn = getConnection();                        
			stmt = conn.prepareStatement(etkinlikIdyeGoreGetir);                        
                        stmt.setInt(1, aranan);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				SinifEtkinlikleri sinifEtkinlikleri = new SinifEtkinlikleri();
                                Etkinlikler etkinlik=new Etkinlikler();
                                Siniflar sinif=new Siniflar();
				sinifEtkinlikleri.setSinifEtkinlikleriId(rs.getInt("sinifEtkinlikleriId"));
                                
				sinif.setSinifId(rs.getInt("sinifId"));
                                sinif.setSinifKodu(rs.getString("sinifKodu"));
                                
                                etkinlik.setEtkinlikId(rs.getInt("etkinlikId"));
                                etkinlik.setEtkinlikAdi(rs.getString("etkinlikAdi"));
                                
                                sinifEtkinlikleri.setEtkinlik(etkinlik);
                                sinifEtkinlikleri.setSinif(sinif);
				list.add(sinifEtkinlikleri);
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
    
    public int Ekle(SinifEtkinlikleri sinifEt)
    {
        
        Connection conn = null;
            PreparedStatement stmt = null;
		
		try 
                {
                    conn = getConnection();
                    stmt = conn.prepareStatement(ekle);
                    stmt.setInt(2, sinifEt.getEtkinlik().getEtkinlikId());
                    stmt.setInt(1, sinifEt.getSinif().getSinifId());                 
                 	                       
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
    
    public int Sil(SinifEtkinlikleri sinifEt)
    {
        
        Connection conn = null;
            PreparedStatement stmt = null;
		
		try 
                {
                    conn = getConnection();
                    stmt = conn.prepareStatement(sil);
                    stmt.setInt(2, sinifEt.getEtkinlik().getEtkinlikId());
                    stmt.setInt(1, sinifEt.getSinif().getSinifId());                 
                 	                       
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
