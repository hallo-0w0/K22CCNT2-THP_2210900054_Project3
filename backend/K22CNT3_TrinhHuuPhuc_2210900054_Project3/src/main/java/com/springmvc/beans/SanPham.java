package com.springmvc.beans;

public class SanPham {
    private int maSP;
    private String tenSP;
    private String loaiSP;
    private String donViTinh;
    private double giaNhap;
    private int soLuongTon;

    // Constructor
    public SanPham() {}

    public SanPham(int maSP, String tenSP, String loaiSP, String donViTinh, double giaNhap, int soLuongTon) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.donViTinh = donViTinh;
        this.giaNhap = giaNhap;
        this.soLuongTon = soLuongTon;
    }

    // Getters và Setters
    public int getMaSP() { return maSP; }
    public void setMaSP(int maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public String getLoaiSP() { return loaiSP; }
    public void setLoaiSP(String loaiSP) { this.loaiSP = loaiSP; }

    public String getDonViTinh() { return donViTinh; }
    public void setDonViTinh(String donViTinh) { this.donViTinh = donViTinh; }

    public double getGiaNhap() { return giaNhap; }
    public void setGiaNhap(double giaNhap) { this.giaNhap = giaNhap; }

    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }
}
