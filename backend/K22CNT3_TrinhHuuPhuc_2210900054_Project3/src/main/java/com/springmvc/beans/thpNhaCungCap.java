package com.springmvc.beans;

public class thpNhaCungCap {
    private int thpMaNCC;
    private String thpTenNCC;
    private String thpDiaChi;
    private String thpSoDienThoai;

    public thpNhaCungCap() {}

    public thpNhaCungCap(int thpMaNCC, String thpTenNCC, String thpDiaChi, String thpSoDienThoai) {
        this.thpMaNCC = thpMaNCC;
        this.thpTenNCC = thpTenNCC;
        this.thpDiaChi = thpDiaChi;
        this.thpSoDienThoai = thpSoDienThoai;
    }

    public int getThpMaNCC() {
        return thpMaNCC;
    }

    public void setThpMaNCC(int thpMaNCC) {
        this.thpMaNCC = thpMaNCC;
    }

    public String getThpTenNCC() {
        return thpTenNCC;
    }

    public void setThpTenNCC(String thpTenNCC) {
        this.thpTenNCC = thpTenNCC;
    }

    public String getThpDiaChi() {
        return thpDiaChi;
    }

    public void setThpDiaChi(String thpDiaChi) {
        this.thpDiaChi = thpDiaChi;
    }

    public String getThpSoDienThoai() {
        return thpSoDienThoai;
    }

    public void setThpSoDienThoai(String thpSoDienThoai) {
        this.thpSoDienThoai = thpSoDienThoai;
    }
}
