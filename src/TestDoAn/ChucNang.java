/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//<editor-fold defaultstate="collapsed" desc="Ghi Chú">
//</editor-fold>
package TestDoAn;

import  java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tanng
 */
public class ChucNang extends Database{
    
    //<editor-fold defaultstate="collapsed" desc="Login">
    public  boolean login(String user, String pass) throws SQLException{
        connect(); // kết nối database
        String query = "select * from NHANVIEN where IDNhanVien= '"+user+"' and MatKhau = '"+pass+"'";
        ResultSet rs = stmt.executeQuery(query);
        if(rs.next()){
            NhanVien.getInstance().LuuNhanVien(rs);
            return true;
        }
        else return false;
    }
//</editor-fold>
    //==========================================================================================================
    //<editor-fold defaultstate="collapsed" desc=" show nhân viên lên jtable">
    // show nhân viên lên jtable
    public void shownhanvien(DefaultTableModel model){
        connect();
        String query = "Select * from Nhanvien";
        model.setRowCount(0); // clear jtable
            try {
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                String ma = rs.getString("IDNhanVien");
                String ten = rs.getString("HoTenNhanVien");
                String SDT = rs.getString("SDT");
                String Email = rs.getString("Email");
                String address = rs.getString("DiaChi");

                String tbData[] = {ma,ten,SDT,Email,address};
                model.addRow(tbData);

                }

            } catch (SQLException ex) {
                Logger.getLogger(FormChinh.class.getName()).log(Level.SEVERE, null, ex);
            }                   
    
    }
//</editor-fold>
    //==========================================================================================================
    //<editor-fold defaultstate="collapsed" desc="Đổi Pass">
    // đổi pass
    public boolean checkpass(String passString){
        return passString.equals(NhanVien.getInstance().matkhau);
    }
    public void ChangePass(String newpass,String confirmpass) throws SQLException{
        if(newpass.equals(confirmpass)){
            if (newpass.equals("") || confirmpass.equals("") ) {
                JOptionPane.showMessageDialog(null, "Mật Khẩu Không Được Để Trống !!!");
            } else {
                connect();
                String query = "update NHANVIEN set MatKhau = '"+newpass+"' where IDNhanVien = '"+NhanVien.getInstance().manhanvien+"'";
                stmt.execute(query);
                NhanVien.getInstance().setMatkhau(newpass);
                JOptionPane.showMessageDialog(null, "Đổi Mật Khẩu Thành Công");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Mật Khẩu Không Khớp!!!");
        }
    }
//</editor-fold>
    //==========================================================================================================
}