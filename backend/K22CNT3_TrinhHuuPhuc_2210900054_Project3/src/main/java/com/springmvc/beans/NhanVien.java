package com.springmvc.beans;

import com.springmvc.enums.VaiTro;

public class NhanVien {
    private int maNV;
    private String hoTen;
    private String phongBan;
    private VaiTro vaiTro;

    public NhanVien() {
        super();
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }

    public VaiTro getVaiTro() {
        return vaiTro;
    }

    // ✅ Chỉnh sửa phương thức setVaiTro để hỗ trợ cả Enum và String
    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = VaiTro.fromString(vaiTro);
    }
}
