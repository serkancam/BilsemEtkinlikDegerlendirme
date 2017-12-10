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
import java.util.Locale;

/**
 *
 * @author serkancam
 */
public class OgrencilerDAO extends DAO
{
    private final String tumListeyiGetir= "select * from [programlar] as p inner join [siniflar] as s on p.[programId]=s.[programId]"
            + " inner join [ogrenciler] as o on s.[sinifId]=o.[sinifId] where s.[sinifId]=3 order by o.[ogrenciNo] asc";
    private final String sinifOgrencileriniGetir="select * from [ogrenciler] where [sinifId]=? ";
    public List<Ogrenciler> TumListeyiGetir() 
        {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Ogrenciler> list = new ArrayList<>();
		
		try 
                {
			conn = getConnection();
			stmt = conn.prepareStatement(tumListeyiGetir);
                        ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				Ogrenciler ogrenci = new Ogrenciler();
                                Programlar program=new Programlar();
                                Siniflar sinif=new Siniflar();
                                AldigiDersler ad=new AldigiDersler();
                                Dersler ders=new Dersler();
                                
                                program.setProgramAdi(rs.getString("programAdi"));
                                program.setProgramId(rs.getInt("programId"));
                                
                                sinif.setSinifId(rs.getInt("sinifId"));
                                sinif.setProgram(program);
                                sinif.setSinifKodu(rs.getString("sinifKodu"));
                                
                                ogrenci.setOgrenciAdi(rs.getString("ogrenciAdi"));
                                ogrenci.setOgrenciSoyadi(rs.getString("ogrenciSoyadi"));
                                ogrenci.setOgrenciNo(rs.getInt("ogrenciNo"));
                                ogrenci.setSinif(sinif);
                                                            
                                
                                                            
                                
				list.add(ogrenci);
			}
		}
                catch (SQLException e) 
                {
			 e.printStackTrace();
			throw new RuntimeException(e);
		} 
                finally 
                {
			close(stmt);
			close(conn);
		}
		
		return list;
	}
    
    
    public List<Ogrenciler> SinifinOgrencileriniGetir(int sinifId)
    {
        Connection conn = null;
                PreparedStatement stmt = null;
		
		List<Ogrenciler> list = new ArrayList<Ogrenciler>();
		
		try 
                {
			conn = getConnection();                        
			stmt = conn.prepareStatement(sinifOgrencileriniGetir);                        
                        stmt.setInt(1,sinifId );
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				
                            Ogrenciler ogrenci=new Ogrenciler();
                            ogrenci.setOgrenciNo(rs.getInt("ogrenciNo"));
                            ogrenci.setOgrenciAdi(rs.getString("ogrenciAdi"));
                            ogrenci.setOgrenciSoyadi(rs.getString("ogrenciSoyadi"));
                            list.add(ogrenci);
                                
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
    
}
