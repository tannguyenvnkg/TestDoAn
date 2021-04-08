/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestDoAn;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tanng
 */
public class hamtestchucnang extends  Database{
        public void run() throws SQLException{
            connect();
            String queryString = "Select * from Chucvu";
            ResultSet rs = stmt.executeQuery(queryString);
            while(rs.next()){
                System.out.println(rs.getString("TenChucVu"));
            }    
        }
        
}
