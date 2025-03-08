package com.springmvc.enums;

import java.util.HashMap;
import java.util.Map;

public enum thpVaiTro {
    NHAN_VIEN("Nhân viên"),
    NHAN_VIEN_KHO("Nhân viên kho"),
    QUAN_LY("Quản lý");

    private final String value;
    
    // ✅ Tạo cache để tối ưu hóa tra cứu
    private static final Map<String, thpVaiTro> lookupByValue = new HashMap<>();
    private static final Map<String, thpVaiTro> lookupByName = new HashMap<>();

    static {
        for (thpVaiTro role : thpVaiTro.values()) {
            lookupByValue.put(role.value.toLowerCase(), role);
            lookupByName.put(role.name().toLowerCase(), role);
        }
    }

    thpVaiTro(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // ✅ Cải tiến phương thức `fromString` để tối ưu tra cứu và tránh lỗi
    public static thpVaiTro fromString(String text) {
        if (text == null || text.trim().isEmpty()) {
            return NHAN_VIEN; // ✅ Trả về giá trị mặc định thay vì lỗi
        }
        text = text.trim().toLowerCase();
        
        if (lookupByName.containsKey(text)) {
            return lookupByName.get(text);
        }
        if (lookupByValue.containsKey(text)) {
            return lookupByValue.get(text);
        }
        return NHAN_VIEN; // ✅ Nếu không tìm thấy, trả về mặc định thay vì lỗi
    }
}
