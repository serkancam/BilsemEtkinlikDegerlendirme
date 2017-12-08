/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BilsemEtkinlik.DAO;

/**
 *
 * @author serkancam
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.options.LoggingOption;





/**
 *
 * @author serkancam
 */
public class DAO 
{
        
    private static final String DRIVER_NAME = "net.ucanaccess.jdbc.UcanaccessDriver";
    private static final String DB_URL = "jdbc:ucanaccess://G:\\Calismalar\\msaccess\\Bilsem.accdb";
    public Connection getConnection()
    {    
        try {
 
            Class.forName(DRIVER_NAME);
        }
        catch(ClassNotFoundException cnfex) {
 
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
        try 
        {
           Logger l= Logger.getAnonymousLogger();
           l.config("bağlantı tamam");
            System.out.println("bağlantı tamam");
           return DriverManager.getConnection(DB_URL);            
	}
        catch (SQLException e ) 
        {                        
           throw new RuntimeException(e); 
            
	}
        
    }
    public void close(Connection con)
    {
        if (con != null) 
        {
            try 
            {
                  con.close();
            } 
            catch (SQLException e) 
            {
            // e.printStackTrace();
             throw new RuntimeException(e);
            }
	}
    }
    public void close(Statement stmt)
    {
		if (stmt != null) 
                {
			try 
                        {
				stmt.close();
			} 
                        catch (SQLException e) 
                        {
				// e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
    }
  
    
    
}