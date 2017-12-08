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
public class DerslerDAO extends DAO
{
    private final String tumDersleriGetir="select * from [dersler]";
    
    public List<Dersler> TumDersleriGetir()
    {
        Connection conn = null;
		PreparedStatement stmt = null;
		List<Dersler> list = new ArrayList<>();
		
		try 
                {
			conn = getConnection();
			stmt = conn.prepareStatement(tumDersleriGetir);
                        ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				
                                Dersler ders=new Dersler();
                                
                                ders.setDersId(rs.getInt("dersId"));
                                ders.setDersAdi(rs.getString("dersAdi"));
                                
                                                            
                                
				list.add(ders);
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
    
}
