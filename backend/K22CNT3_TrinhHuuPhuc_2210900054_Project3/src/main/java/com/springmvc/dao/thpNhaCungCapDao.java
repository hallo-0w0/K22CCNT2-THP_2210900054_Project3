package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springmvc.beans.thpNhaCungCap;

@Repository
public class thpNhaCungCapDao {
    
    @Autowired
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // Thêm mới nhà cung cấp
    public int save(thpNhaCungCap ncc) {
        String sql = "INSERT INTO thp_NHA_CUNG_CAP (thp_TenNCC, thp_DiaChi, thp_SoDienThoai) VALUES (?, ?, ?)";
        return template.update(sql, ncc.getThpTenNCC(), ncc.getThpDiaChi(), ncc.getThpSoDienThoai());
    }

    // Cập nhật thông tin nhà cung cấp
    public int update(thpNhaCungCap ncc) {
        String sql = "UPDATE thp_NHA_CUNG_CAP SET thp_TenNCC=?, thp_DiaChi=?, thp_SoDienThoai=? WHERE thp_MaNCC=?";
        return template.update(sql, ncc.getThpTenNCC(), ncc.getThpDiaChi(), ncc.getThpSoDienThoai(), ncc.getThpMaNCC());
    }

    // Xóa nhà cung cấp theo ID
    public int delete(int thpMaNCC) {
        String sql = "DELETE FROM thp_NHA_CUNG_CAP WHERE thp_MaNCC=?";
        return template.update(sql, thpMaNCC);
    }

    // Lấy nhà cung cấp theo ID
    public thpNhaCungCap getThpNhaCungCapById(int thpMaNCC) {
        String sql = "SELECT * FROM thp_NHA_CUNG_CAP WHERE thp_MaNCC=?";
        try {
            return template.queryForObject(sql, new Object[]{thpMaNCC}, new ThpNhaCungCapMapper());
        } catch (Exception e) {
            return null;
        }
    }

    // Lấy danh sách tất cả nhà cung cấp
    public List<thpNhaCungCap> getThpAllNhaCungCap() {
        String sql = "SELECT * FROM thp_NHA_CUNG_CAP";
        return template.query(sql, new ThpNhaCungCapMapper());
    }

    // Lớp ánh xạ ResultSet sang đối tượng NhaCungCap
    private static class ThpNhaCungCapMapper implements RowMapper<thpNhaCungCap> {
        @Override
        public thpNhaCungCap mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new thpNhaCungCap(
                rs.getInt("thp_MaNCC"),
                rs.getString("thp_TenNCC"),
                rs.getString("thp_DiaChi"),
                rs.getString("thp_SoDienThoai")
            );
        }
    }
}
