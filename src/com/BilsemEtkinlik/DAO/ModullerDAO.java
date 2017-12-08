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
public class ModullerDAO extends DAO
{
     private final String dersIdyeGoreGetir = "SELECT * FROM [moduller] WHERE [dersId]=?";
     public List<Moduller> EtkinlikIdyeGoreGetir(Moduller arananModul) 
        {
		Connection conn = null;
                PreparedStatement stmt = null;
		
		List<Moduller> list = new ArrayList<Moduller>();
		
		try 
                {
			conn = getConnection();                        
			stmt = conn.prepareStatement(dersIdyeGoreGetir);                        
                        stmt.setInt(1, arananModul.getDers().getDersId());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				Moduller modul = new Moduller();
                                modul.setModulId(rs.getInt("modulId"));
                                modul.setModulAdi(rs.getString("modulAdi"));
				list.add(modul);
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
