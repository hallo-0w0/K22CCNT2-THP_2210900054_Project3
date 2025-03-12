package com.springmvc.beans;

public class thpTaiKhoan {
    private int thpMaTK;
    private String thpEmail;
    private String thpMatKhau;
    private int thpMaNV; // Liên kết đến nhân viên
    private thpNhanVien thpNhanVien; // Đối tượng nhân viên

    public thpTaiKhoan() {}

    public thpTaiKhoan(int thpMaTK, String thpEmail, String thpMatKhau, int thpMaNV, thpNhanVien thpNhanVien) {
        this.thpMaTK = thpMaTK;
        this.thpEmail = thpEmail;
        this.thpMatKhau = thpMatKhau;
        this.thpMaNV = thpMaNV;
        this.thpNhanVien = thpNhanVien;
    }

    public int getThpMaTK() {
        return thpMaTK;
    }

    public void setThpMaTK(int thpMaTK) {
        this.thpMaTK = thpMaTK;
    }

    public String getThpEmail() {
        return thpEmail;
    }

    public void setThpEmail(String thpEmail) {
        this.thpEmail = thpEmail;
    }

    public String getThpMatKhau() {
        return thpMatKhau;
    }

    public void setThpMatKhau(String thpMatKhau) {
        this.thpMatKhau = thpMatKhau;
    }

    public int getThpMaNV() {
        return thpMaNV;
    }

    public void setThpMaNV(int thpMaNV) {
        this.thpMaNV = thpMaNV;
    }

    public thpNhanVien getThpNhanVien() {
        return thpNhanVien;
    }

    public void setThpNhanVien(thpNhanVien thpNhanVien) {
        this.thpNhanVien = thpNhanVien;
    }
}
