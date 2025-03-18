package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.springmvc.beans.thpTaiKhoan;
import com.springmvc.beans.thpNhanVien;

@Repository
public class thpLoginDao {

    @Autowired
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // 🔹 Kiểm tra đăng nhập
    public thpTaiKhoan login(String email, String matKhau) {
        String sql = "SELECT tk.*, nv.thp_HoTen, nv.thp_PhongBan, nv.thp_VaiTro " +
                     "FROM thp_TAI_KHOAN tk " +
                     "LEFT JOIN thp_NHAN_VIEN nv ON tk.thp_MaNV = nv.thp_MaNV " +
                     "WHERE tk.thp_Email=? AND tk.thp_MatKhau=?";
        try {
            return template.queryForObject(sql, new Object[]{email, matKhau}, new ThpLoginMapper());
        } catch (Exception e) {
            return null; // Nếu không tìm thấy tài khoản, trả về null
        }
    }

    // 🔹 Lớp ánh xạ từ ResultSet sang thpTaiKhoan
    private static class ThpLoginMapper implements RowMapper<thpTaiKhoan> {
        @Override
        public thpTaiKhoan mapRow(ResultSet rs, int rowNum) throws SQLException {
            thpNhanVien nhanVien = new thpNhanVien();
            nhanVien.setThpMaNV(rs.getInt("thp_MaNV"));
            nhanVien.setThpHoTen(rs.getString("thp_HoTen"));
            nhanVien.setThpPhongBan(rs.getString("thp_PhongBan"));
            nhanVien.setThpVaiTro(rs.getString("thp_VaiTro"));

            return new thpTaiKhoan(
                rs.getInt("thp_MaTK"),
                rs.getString("thp_Email"),
                rs.getString("thp_MatKhau"),
                rs.getInt("thp_MaNV"),
                nhanVien
            );
        }
    }
}
