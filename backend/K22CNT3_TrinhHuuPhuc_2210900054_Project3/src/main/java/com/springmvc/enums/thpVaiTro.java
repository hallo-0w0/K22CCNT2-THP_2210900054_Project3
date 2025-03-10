package com.springmvc.enums;

import java.util.HashMap;
import java.util.Map;

public enum thpVaiTro {
    NHAN_VIEN("Nhân viên"),
    QUAN_LY("Quản lý");

    private final String value;

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

    public static thpVaiTro fromString(String text) {
        if (text == null || text.trim().isEmpty()) {
            return thpVaiTro.NHAN_VIEN; // ✅ Truy cập đúng cách
        }
        text = text.trim().toLowerCase();

        if (lookupByName.containsKey(text)) {
            return lookupByName.get(text);
        }
        if (lookupByValue.containsKey(text)) {
            return lookupByValue.get(text);
        }
        return thpVaiTro.NHAN_VIEN;
    }
}
