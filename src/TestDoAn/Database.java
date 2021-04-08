package TestDoAn;

import static java.lang.Character.UnicodeBlock.forName;
import java.sql.*;
import java.util.logging.Level; 
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tanng
 */
public class Database {
      Connection conn = null; // khỏi tạo global conn
      Statement stmt = null; // khỏi tạo global stmt
    public void connect(){
        
        try {
            String ServerDBName = "localhost";
            String databaseName = "QuanLyCaPhe";
            String user = "sa";
            String password = "123456";
            String dbURL = "jdbc:sqlserver://"+ ServerDBName +";databaseName="+ databaseName + ";user=" + user + ";password="+password; 
            
            conn = DriverManager.getConnection(dbURL); // nối database
            stmt = conn.createStatement(); // tạo stmt
           // if(conn != null) System.out.println("Connect Database Successful");
       } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    /**
     *
     * @return
     */
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       
    }

    
    

}
