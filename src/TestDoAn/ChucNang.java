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
import javax.swing.DefaultComboBoxModel;
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
    public boolean checktrangthai() {
        return NhanVien.getInstance().getTrangthai();
    }
//</editor-fold>
    //==========================================================================================================
    //<editor-fold defaultstate="collapsed" desc=" show nhân viên lên jtable">
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
                String matkhau = rs.getString("MatKhau");
                String chucvu = "";
                String trangthai = "";
                
                if(rs.getString("IDChucvu").equals("1")) chucvu = "Admin";
                else if(rs.getString("IDChucvu").equals("2")) chucvu = "Nhân Viên";
                if(rs.getBoolean("TrangThai")) trangthai = "Active";
                else trangthai = "Deactive";
                
                String tbData[] = {ma,ten,SDT,Email,address,matkhau,chucvu,trangthai}; 
                model.addRow(tbData);
                }

            } catch (SQLException ex) {
                Logger.getLogger(FormChinh.class.getName()).log(Level.SEVERE, null, ex);
            }                   
    
    }
    // show nhân viên lên jtable
   
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
    //<editor-fold defaultstate="collapsed" desc="Thêm Xóa Sửa Nhân Viên">
    //Thêm Xóa Sửa Nhân Viên
    public boolean checkma(String ma) throws SQLException{
        connect();
        String query = "Select* from NhanVien";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            if(rs.getString("IDNhanVien").equals(ma)) return true; // tồn tại mã nhân viên thì update
        }
        return false; // không tồn tại thì add
    }
    
    public void UpdateNhanVien(String ma,String ten, String sdt,String email,String diachi,String matkhau, String chucvu) throws SQLException{
        connect();
        String manv = ma;
        int machucvu = 0,machucvunhanvien = 0;
        boolean check = false;
        
        String getidchucvu = "select idchucvu from chucvu where tenchucvu = N'"+chucvu+"'";
        ResultSet rs = stmt.executeQuery(getidchucvu);
        while(rs.next()){
            machucvu = rs.getInt(1);
        }
        String getidchucvunhanvien = "select idchucvu from nhanvien where IDNhanVien = '"+ma+"'";
        rs = stmt.executeQuery(getidchucvunhanvien);
        while (rs.next()) {            
            machucvunhanvien = rs.getInt(1);
        }
        
        if(!(machucvu == machucvunhanvien)){ // kiểm tra mã chức vụ khác nhau thì đặt lại mã nhân viên
            check = true;
            String firstma = "", secondma = "";
            int count = 0;
            if(machucvu == 1 ) firstma = "AD";
            else firstma = "NV";

            String demnhanvien = "Select count(*) from nhanvien where idchucvu = N'"+machucvu+"'";
            ResultSet rs1 = stmt.executeQuery(demnhanvien);
            while(rs1.next()){
                count = rs1.getInt(1);
            }
            secondma = String.valueOf(count+1); // số báo danh
            manv = firstma + secondma; // khởi tạo tài khoản
            matkhau = manv;
        }
        //======================================================================================
        String query = 
            "Update nhanvien set IDNhanVien = '"+manv+"', idchucvu = '"+machucvu+"',"
                + "HoTenNhanVien = N'"+ten+"' , SDT='"+sdt+"',Email='"+email+"',"
                + "DiaChi=N'"+diachi+"',MatKhau='"+matkhau+"' where IDNhanVien='"+ma+"'";
        stmt.execute(query);
        if(!check)
            JOptionPane.showMessageDialog(null, "Update Nhân Viên Thành Công");
        else JOptionPane.showMessageDialog(null, "Update Nhân Viên Thành Công \n "
                + "Tài Khoản Mới của bạn là:\n "
                + "Tài Khoản: "+manv+"\n "
                + "Mật Khẩu: "+matkhau+"");
    }
    
    public void AddNhanVien(String ten, String sdt,String email,String diachi, String chucvu) throws SQLException{
        connect();
        // tạo tài khoản=========================================================================================
        int count = 0;
        int machucvu = 0;
        String firstma = "", secondma = "";
        
        String getidchucvu = "select idchucvu from chucvu where tenchucvu = N'"+chucvu+"'";
        ResultSet rs = stmt.executeQuery(getidchucvu);
        while(rs.next()){
            machucvu = rs.getInt(1);
        }
        if(machucvu == 1 ) firstma = "AD";
        else firstma = "NV";
        
        
        String demnhanvien = "Select count(*) from nhanvien where idchucvu = N'"+machucvu+"'";
        ResultSet rs1 = stmt.executeQuery(demnhanvien);
        while(rs1.next()){
            count = rs1.getInt(1);
        }
        secondma = String.valueOf(count+1); // số báo danh
        
        
        String ma = firstma + secondma; // khởi tạo tài khoản
        //======================================================================================
        String matkhau = ma; // cho mật khẩu và tài khoản trùng nhau
        String query = "insert into NHANVIEN values('"+ma+"',N'"+ten+"','"+sdt+"','"+email+"',N'"+diachi+"','"+matkhau+"','"+machucvu+"',1)";
        stmt.execute(query);
        JOptionPane.showMessageDialog(null, "Add Nhân Viên Thành Công \n Tài Khoản : "+ma+"\n Mật Khẩu : "+matkhau+"");
    }
    public void DeleteNhanVien(String ma) throws SQLException{
        connect();
        String query = "update nhanvien set trangthai = 0 where IDNhanVien = '"+ma+"'";
        stmt.execute(query);
        JOptionPane.showMessageDialog(null, "Delete Nhân Viên Thành Công");
    }
    public void ActiveNhanVien(String ma) throws SQLException{
        connect();
        String query = "update nhanvien set trangthai = 1 where IDNhanVien = '"+ma+"'";
        stmt.execute(query);
        JOptionPane.showMessageDialog(null, "Active Nhân Viên Thành Công");
    }
    
    // add combobox tại quản lý
    public void showcombobox(DefaultComboBoxModel aBoxModel) throws SQLException{
            connect();
            String query = "Select * from chucvu";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {                
                aBoxModel.addElement(rs.getString("Tenchucvu"));
                System.out.println(rs.getString("Tenchucvu"));
            }
        
    //</editor-fold>
    //==========================================================================================================
    }
}