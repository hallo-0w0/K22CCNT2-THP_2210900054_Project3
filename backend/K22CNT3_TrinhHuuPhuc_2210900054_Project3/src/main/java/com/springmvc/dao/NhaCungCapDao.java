package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springmvc.beans.NhaCungCap;

@Repository
public class NhaCungCapDao {
    
    @Autowired
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // Thêm mới nhà cung cấp
    public int save(NhaCungCap ncc) {
        String sql = "INSERT INTO thp_NHA_CUNG_CAP (thp_TenNCC, thp_DiaChi, thp_SoDienThoai) VALUES (?, ?, ?)";
        return template.update(sql, ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSoDienThoai());
    }

    // Cập nhật thông tin nhà cung cấp
    public int update(NhaCungCap ncc) {
        String sql = "UPDATE thp_NHA_CUNG_CAP SET thp_TenNCC=?, thp_DiaChi=?, thp_SoDienThoai=? WHERE thp_MaNCC=?";
        return template.update(sql, ncc.getTenNCC(), ncc.getDiaChi(), ncc.getSoDienThoai(), ncc.getMaNCC());
    }

    // Xóa nhà cung cấp theo ID
    public int delete(int maNCC) {
        String sql = "DELETE FROM thp_NHA_CUNG_CAP WHERE thp_MaNCC=?";
        return template.update(sql, maNCC);
    }

    // Lấy nhà cung cấp theo ID
    public NhaCungCap getNhaCungCapById(int maNCC) {
        String sql = "SELECT * FROM thp_NHA_CUNG_CAP WHERE thp_MaNCC=?";
        try {
            return template.queryForObject(sql, new Object[]{maNCC}, new NhaCungCapMapper());
        } catch (Exception e) {
            return null;
        }
    }

    // Lấy danh sách tất cả nhà cung cấp
    public List<NhaCungCap> getAllNhaCungCap() {
        String sql = "SELECT * FROM thp_NHA_CUNG_CAP";
        return template.query(sql, new NhaCungCapMapper());
    }

    // Lớp ánh xạ ResultSet sang đối tượng NhaCungCap
    private static class NhaCungCapMapper implements RowMapper<NhaCungCap> {
        @Override
        public NhaCungCap mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new NhaCungCap(
                rs.getInt("thp_MaNCC"),
                rs.getString("thp_TenNCC"),
                rs.getString("thp_DiaChi"),
                rs.getString("thp_SoDienThoai")
            );
        }
    }
}
