package com.springmvc.beans;

public class thpChiTietPhieuNhap {
    private int thpMaPN;       // Mã phiếu nhập
    private int thpMaSP;       // Mã sản phẩm
    private String thpTenSP;   // 🔹 Thêm thuộc tính Tên sản phẩm
    private int thpSoLuongNhap;// Số lượng nhập

    // ✅ Constructor không tham số
    public thpChiTietPhieuNhap() {}

    // ✅ Constructor có đầy đủ thông tin
    public thpChiTietPhieuNhap(int thpMaPN, int thpMaSP, String thpTenSP, int thpSoLuongNhap) {
        this.thpMaPN = thpMaPN;
        this.thpMaSP = thpMaSP;
        this.thpTenSP = thpTenSP;
        this.thpSoLuongNhap = thpSoLuongNhap;
    }

    // ✅ Getter & Setter
    public int getThpMaPN() {
        return thpMaPN;
    }

    public void setThpMaPN(int thpMaPN) {
        this.thpMaPN = thpMaPN;
    }

    public int getThpMaSP() {
        return thpMaSP;
    }

    public void setThpMaSP(int thpMaSP) {
        this.thpMaSP = thpMaSP;
    }

    public String getThpTenSP() {  // 🔹 Getter mới
        return thpTenSP;
    }

    public void setThpTenSP(String thpTenSP) {  // 🔹 Setter mới
        this.thpTenSP = thpTenSP;
    }

    public int getThpSoLuongNhap() {
        return thpSoLuongNhap;
    }

    public void setThpSoLuongNhap(int thpSoLuongNhap) {
        this.thpSoLuongNhap = thpSoLuongNhap;
    }
}
