/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestDoAn;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author tanng
 */
public class hamtestchucnang extends  Database{
        public void run() throws SQLException{
            connect();
            String queryString = "Select count(*) from Chucvu";
            ResultSet rs = stmt.executeQuery(queryString);
            int a = 0;
            while(rs.next()){
                a = rs.getInt(1);
            }
            String b = String.valueOf(a);
            System.out.println(b);
            
        }
        
        public void addcombobox(DefaultComboBoxModel aBoxModel) throws SQLException{
            connect();
            String query = "Select * from chucvu";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {                
                aBoxModel.addElement(rs.getString("Tenchucvu"));
                System.out.println(rs.getString("Tenchucvu"));
            }
        }
        public String layma(String a) throws SQLException{
            connect();
            String query = "Select idchucvu from chucvu where tenchucvu = '"+a+"'";
             ResultSet rs = stmt.executeQuery(query);
             while(rs.next()){
                 return rs.getString(1);
            }
             return "Error";
        }
}