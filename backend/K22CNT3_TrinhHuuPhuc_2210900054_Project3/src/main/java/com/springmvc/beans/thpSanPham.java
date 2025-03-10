package com.springmvc.beans;

public class thpSanPham {
    private int thpMaSP;
    private String thpTenSP;
    private int thpMaLoai; // Lưu ID loại sản phẩm
    private String thpTenLoai; // 🔹 Thêm thuộc tính này
    private String thpDonViTinh;
    private double thpGiaNhap;
    private int thpSoLuongTon;

    // ✅ Constructors
    public thpSanPham() {}

    public thpSanPham(int thpMaSP, String thpTenSP, int thpMaLoai, String thpDonViTinh, double thpGiaNhap, int thpSoLuongTon) {
        this.thpMaSP = thpMaSP;
        this.thpTenSP = thpTenSP;
        this.thpMaLoai = thpMaLoai;
        this.thpDonViTinh = thpDonViTinh;
        this.thpGiaNhap = thpGiaNhap;
        this.thpSoLuongTon = thpSoLuongTon;
    }

    // ✅ Getters và Setters
    public int getThpMaSP() {
        return thpMaSP;
    }

    public void setThpMaSP(int thpMaSP) {
        this.thpMaSP = thpMaSP;
    }

    public String getThpTenSP() {
        return thpTenSP;
    }

    public void setThpTenSP(String thpTenSP) {
        this.thpTenSP = thpTenSP;
    }

    public int getThpMaLoai() {
        return thpMaLoai;
    }

    public void setThpMaLoai(int thpMaLoai) {
        this.thpMaLoai = thpMaLoai;
    }

    public String getThpTenLoai() { // 🔹 Getter mới
        return thpTenLoai;
    }

    public void setThpTenLoai(String thpTenLoai) { // 🔹 Setter mới
        this.thpTenLoai = thpTenLoai;
    }

    public String getThpDonViTinh() {
        return thpDonViTinh;
    }

    public void setThpDonViTinh(String thpDonViTinh) {
        this.thpDonViTinh = thpDonViTinh;
    }

    public double getThpGiaNhap() {
        return thpGiaNhap;
    }

    public void setThpGiaNhap(double thpGiaNhap) {
        this.thpGiaNhap = thpGiaNhap;
    }

    public int getThpSoLuongTon() {
        return thpSoLuongTon;
    }

    public void setThpSoLuongTon(int thpSoLuongTon) {
        this.thpSoLuongTon = thpSoLuongTon;
    }
}
