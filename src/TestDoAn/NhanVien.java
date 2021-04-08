/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//<editor-fold defaultstate="collapsed" desc="Ghi Chú">
//</editor-fold>
package TestDoAn;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tanng
 */
public class NhanVien {
    
    private static NhanVien instance;
    private NhanVien(){
    }
    public static NhanVien getInstance() {
        if (instance == null) {
            instance = new NhanVien();
        }
        return instance;
    }
    
    //<editor-fold defaultstate="collapsed" desc=" Get Set ">
    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getIdchucvu() {
        return idchucvu;
    }

    public void setIdchucvu(String idchucvu) {
        this.idchucvu = idchucvu;
    }
    
//    </editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" Constructor ">
    public void SetNhanVien(String manhanvien, String tennhanvien, String sdt, String email, String diachi, String matkhau, String idchucvu){
        this.manhanvien = manhanvien;
        this.tennhanvien = tennhanvien;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
        this.matkhau = matkhau;
        this.idchucvu = idchucvu;
    }
    public void  SetEmptyNhanVien(){
        manhanvien ="";
        tennhanvien ="";
        sdt ="";
        email ="";
        diachi ="";
        matkhau ="";
        idchucvu ="";
    }
    //</editor-fold>
    // lưu nhân viên đăng nhập từ excute SQL
    public void LuuNhanVien(ResultSet rs) throws SQLException{
        String ma = rs.getString("IDNhanVien");
        String ten = rs.getString("HoTenNhanVien");
        String Sdt = rs.getString("SDT");
        String Email = rs.getString("Email");
        String Diachi = rs.getString("DiaChi");
        String Matkhau = rs.getString("MatKhau");
        String Idchucvu = rs.getString("IDChucvu"); 
        SetNhanVien(ma, ten, Sdt, Email, Diachi, Matkhau, Idchucvu);
    }
    
    String manhanvien = "";
    String tennhanvien = "";
    String sdt = "";
    String email = "";
    String diachi = "";
    String matkhau = "a";
    String idchucvu = "";
}
