package com.springmvc.beans;

import com.springmvc.enums.thpVaiTro;

public class thpNhanVien {
    private int thpMaNV;
    private String thpHoTen;
    private String thpPhongBan;
    private thpVaiTro thpvaiTro;

    public thpNhanVien() {
        super();
        this.thpvaiTro = com.springmvc.enums.thpVaiTro.NHAN_VIEN; // ✅ Truy cập enum đúng cách
    }

    public int getThpMaNV() {
        return thpMaNV;
    }

    public void setThpMaNV(int thpMaNV) {
        this.thpMaNV = thpMaNV;
    }

    public String getThpHoTen() {
        return thpHoTen;
    }

    public void setThpHoTen(String thpHoTen) {
        this.thpHoTen = thpHoTen;
    }

    public String getThpPhongBan() {
        return thpPhongBan;
    }

    public void setThpPhongBan(String thpPhongBan) {
        this.thpPhongBan = thpPhongBan;
    }

    public thpVaiTro getThpVaiTro() {
        return thpvaiTro;
    }

    public void setThpVaiTro(thpVaiTro thpVaiTro) {
        this.thpvaiTro = thpVaiTro;
    }

    public void setThpVaiTro(String thpVaiTro) {
        this.thpvaiTro = com.springmvc.enums.thpVaiTro.fromString(thpVaiTro); // ✅ Truy cập phương thức static đúng cách
    }
}
