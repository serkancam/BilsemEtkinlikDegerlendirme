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
public class ProgramlarDAO extends DAO 
{
    private final String tumProgramlariGetir="select * from [programlar]";
    
    public List<Programlar> TumProgramlarGetir()
    {
        Connection conn = null;
		PreparedStatement stmt = null;
		List<Programlar> list = new ArrayList<>();
		
		try 
                {
			conn = getConnection();
			stmt = conn.prepareStatement(tumProgramlariGetir);
                        ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				
                            Programlar program=new Programlar();
                                
                            program.setProgramId(rs.getInt("programId"));
                            program.setProgramAdi(rs.getString("programAdi"));
                                
                                                            
                                
				list.add(program);
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
