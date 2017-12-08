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
public class EtkinliklerDAO extends DAO
{
    private final String modulIdyeGoreGetir = "SELECT * FROM [etkinlikler] WHERE [modulId]=?";
     public List<Etkinlikler> ModulIdyeGoreGetir(Etkinlikler aranan) 
        {
		Connection conn = null;
                PreparedStatement stmt = null;
		
		List<Etkinlikler> list = new ArrayList<Etkinlikler>();
		
		try 
                {
			conn = getConnection();                        
			stmt = conn.prepareStatement(modulIdyeGoreGetir);                        
                        stmt.setInt(1, aranan.getModul().getModulId());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				Etkinlikler etkinlik = new Etkinlikler();
                                etkinlik.setEtkinlikId(rs.getInt("etkinlikId"));
                                etkinlik.setEtkinlikAdi(rs.getString("etkinlikAdi"));
				list.add(etkinlik);
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
