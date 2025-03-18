package com.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class thpRegisterDao {
    
    @Autowired
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // ✅ Kiểm tra email đã tồn tại chưa
    public boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM thp_TAI_KHOAN WHERE thp_Email = ?";
        Integer count = template.queryForObject(sql, new Object[]{email}, Integer.class);
        return count != null && count > 0;
    }

    // ✅ Thêm nhân viên mới và trả về ID nhân viên vừa tạo
    public Integer addNhanVien(String hoTen) {
        String sql = "INSERT INTO thp_NHAN_VIEN (thp_HoTen, thp_PhongBan, thp_VaiTro) VALUES (?, '', 'NHAN_VIEN')";
        int rowsAffected = template.update(sql, hoTen);
        
        if (rowsAffected > 0) {
            Integer newId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            System.out.println("🆕 Nhân viên mới có ID: " + newId);
            return newId;
        } else {
            System.out.println("❌ Lỗi khi thêm nhân viên.");
            return null; // Trả về null nếu nhân viên không được thêm thành công
        }
    }

    // ✅ Thêm tài khoản mới sau khi có nhân viên
    public boolean saveTaiKhoan(String email, String matKhau, Integer thpMaNV) {
        if (thpMaNV == null) {
            System.out.println("❌ Không thể tạo tài khoản vì thpMaNV không hợp lệ.");
            return false;
        }
        String sql = "INSERT INTO thp_TAI_KHOAN (thp_Email, thp_MatKhau, thp_MaNV) VALUES (?, ?, ?)";
        int rowsAffected = template.update(sql, email, matKhau, thpMaNV);
        System.out.println(rowsAffected > 0 ? "✅ Tạo tài khoản thành công!" : "❌ Lỗi khi tạo tài khoản.");
        return rowsAffected > 0;
    }
}
