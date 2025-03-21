package com.springmvc.beans;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class thpPhieuNhap {
    private int thpMaPN;

    @DateTimeFormat(pattern = "yyyy-MM-dd") // ✅ Chỉ giữ annotation này
    private Date thpNgayNhap; 

    private int thpMaNV;
    private int thpMaNCC;
    
    private String tenNhanVien;
    private String tenNhaCungCap;

    public thpPhieuNhap() {
    }

    public thpPhieuNhap(int thpMaPN, Date thpNgayNhap, int thpMaNV, int thpMaNCC) {
        this.thpMaPN = thpMaPN;
        this.thpNgayNhap = thpNgayNhap;
        this.thpMaNV = thpMaNV;
        this.thpMaNCC = thpMaNCC;
    }

    public int getThpMaPN() {
        return thpMaPN;
    }

    public void setThpMaPN(int thpMaPN) {
        this.thpMaPN = thpMaPN;
    }

    public Date getThpNgayNhap() {
        return thpNgayNhap;
    }

    public void setThpNgayNhap(Date thpNgayNhap) {
        this.thpNgayNhap = thpNgayNhap;
    }

    public int getThpMaNV() {
        return thpMaNV;
    }

    public void setThpMaNV(int thpMaNV) {
        this.thpMaNV = thpMaNV;
    }

    public int getThpMaNCC() {
        return thpMaNCC;
    }

    public void setThpMaNCC(int thpMaNCC) {
        this.thpMaNCC = thpMaNCC;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }
}
