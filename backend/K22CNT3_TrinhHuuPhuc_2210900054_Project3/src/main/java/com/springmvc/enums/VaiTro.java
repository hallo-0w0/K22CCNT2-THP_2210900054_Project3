package com.springmvc.enums;

public enum VaiTro {
    NHAN_VIEN("Nhân viên"),
    NHAN_VIEN_KHO("Nhân viên kho"),
    QUAN_LY("Quản lý");

    private final String value;

    VaiTro(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Chuyển đổi từ String sang Enum an toàn
    public static VaiTro fromString(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Giá trị vai trò không được để trống!");
        }
        for (VaiTro v : VaiTro.values()) {
            if (v.value.equalsIgnoreCase(text.trim()) || v.name().equalsIgnoreCase(text.trim())) {
                return v;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy VaiTro tương ứng với: " + text);
    }
}
