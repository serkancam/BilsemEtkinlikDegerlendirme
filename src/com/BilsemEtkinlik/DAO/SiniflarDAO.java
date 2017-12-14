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
public class SiniflarDAO extends DAO
{
    private final String sil = "DELETE FROM [siniflar] WHERE [sinifId]=?";
    private final String tumListeyiGetir= "SELECT * FROM [siniflar] as s inner join [programlar] as p "
            + "on s.[programId]=p.[programId] order by p.[programId] ";
    private final String programaGoreGetir = "SELECT * FROM [siniflar] WHERE [programId]=?";
    private final String ekle = "INSERT INTO [siniflar]([sinifKodu],[programId]) VALUES(?,?)";
    private final String guncelle = "UPDATE [etkinlikSorulari] SET [etkinlikId]=?, [soru]=? WHERE [soruId]=?";
    
    public List<Siniflar> TumListeyiGetir() 
        {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Siniflar> list = new ArrayList<Siniflar>();
		
		try 
                {
			conn = getConnection();
			stmt = conn.prepareStatement(tumListeyiGetir);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				Siniflar sinif = new Siniflar();
                                Programlar program=new Programlar();
				sinif.setSinifId(rs.getInt("sinifId"));
				sinif.setSinifKodu(rs.getString("sinifKodu"));
                                program.setProgramId(rs.getInt("programId"));
                                program.setProgramAdi(rs.getString("programAdi"));
                                sinif.setProgram(program);
				list.add(sinif);
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
    
    
    public List<Siniflar> ProgramIdyeGoreGetir(int programId) 
        {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Siniflar> list = new ArrayList<Siniflar>();
		
		try 
                {
			conn = getConnection();
			stmt = conn.prepareStatement(programaGoreGetir);
                        stmt.setInt(1, programId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
                        {
				Siniflar sinif = new Siniflar();
                                
				sinif.setSinifId(rs.getInt("sinifId"));
				sinif.setSinifKodu(rs.getString("sinifKodu"));
                                
                                
				list.add(sinif);
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
    
    
    public int SinifEkle(Siniflar sinif)
    {
        Connection conn = null;
            PreparedStatement stmt = null;
		
		try 
                {
                    conn = getConnection();
                    stmt = conn.prepareStatement(ekle);
                    stmt.setString(1, sinif.getSinifKodu());
                    stmt.setInt(2, sinif.getProgram().getProgramId());
                    
                                           
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
