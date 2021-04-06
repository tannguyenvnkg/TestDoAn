/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestDoAn;

import  java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tanng
 */
public class ChucNang extends Database{
    public  boolean login(String user, String pass) throws SQLException{
        connect();
        String query = "select * from NHANVIEN where IDNhanVien= '"+user+"' and MatKhau = '"+pass+"'";
        ResultSet rs = stmt.executeQuery(query);
        if(rs.next()){
            return true;
        }
        else return false;
    }
    
    public void shownhanvien(DefaultTableModel model){
        connect();
        String query = "Select * from Nhanvien";
        model.setRowCount(0); // clear jtable
            try {
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    String manhanvien = rs.getString("IDNhanVien");
                    String tennhanvien = rs.getString("HoTenNhanVien");
                    String sdt = rs.getString("SDT");
                    String email = rs.getString("Email");
                    String diachi = rs.getString("DiaChi");

                    String tbData[] = {manhanvien,tennhanvien,sdt,email,diachi};
                    model.addRow(tbData);

                }

            } catch (SQLException ex) {
                Logger.getLogger(FormChinh.class.getName()).log(Level.SEVERE, null, ex);
            }                   
    
    }
}
