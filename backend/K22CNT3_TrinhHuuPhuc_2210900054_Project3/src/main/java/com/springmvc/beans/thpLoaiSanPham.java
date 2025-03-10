package com.springmvc.beans;

public class thpLoaiSanPham {
    private int thpMaLoai;
    private String thpTenLoai;
    

    // 🟢 Constructor không tham số
    public thpLoaiSanPham() {
    }

    // 🟢 Constructor có tham số
    public thpLoaiSanPham(int thpMaLoai, String thpTenLoai) {
        this.thpMaLoai = thpMaLoai;
        this.thpTenLoai = thpTenLoai;
    }

    // 🔹 Getter và Setter
    public int getThpMaLoai() {
        return thpMaLoai;
    }

    public void setThpMaLoai(int thpMaLoai) {
        this.thpMaLoai = thpMaLoai;
    }

    public String getThpTenLoai() {
        return thpTenLoai;
    }

    public void setThpTenLoai(String thpTenLoai) {
        this.thpTenLoai = thpTenLoai;
    }
    
}
