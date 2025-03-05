package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springmvc.beans.SanPham;

@Repository
public class SanPhamDao {
    
    @Autowired
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // Thêm sản phẩm
    public int save(SanPham sp) {
        String sql = "INSERT INTO thp_SAN_PHAM (thp_TenSP, thp_LoaiSP, thp_DonViTinh, thp_GiaNhap, thp_SoLuongTon) VALUES (?, ?, ?, ?, ?)";
        return template.update(sql, sp.getTenSP(), sp.getLoaiSP(), sp.getDonViTinh(), sp.getGiaNhap(), sp.getSoLuongTon());
    }

    // Cập nhật sản phẩm
    public int update(SanPham sp) {
        String sql = "UPDATE thp_SAN_PHAM SET thp_TenSP=?, thp_LoaiSP=?, thp_DonViTinh=?, thp_GiaNhap=?, thp_SoLuongTon=? WHERE thp_MaSP=?";
        return template.update(sql, sp.getTenSP(), sp.getLoaiSP(), sp.getDonViTinh(), sp.getGiaNhap(), sp.getSoLuongTon(), sp.getMaSP());
    }

    // Xóa sản phẩm
    public int delete(int maSP) {
        String sql = "DELETE FROM thp_SAN_PHAM WHERE thp_MaSP=?";
        return template.update(sql, maSP);
    }

    // Lấy sản phẩm theo ID
    public SanPham getSanPhamById(int maSP) {
        String sql = "SELECT * FROM thp_SAN_PHAM WHERE thp_MaSP=?";
        try {
            return template.queryForObject(sql, new Object[]{maSP}, new SanPhamMapper());
        } catch (Exception e) {
            return null;
        }
    }

    // Lấy danh sách sản phẩm
    public List<SanPham> getAllSanPhams() {
        String sql = "SELECT * FROM thp_SAN_PHAM";
        return template.query(sql, new SanPhamMapper());
    }

    // Lớp ánh xạ ResultSet sang SanPham
    private static class SanPhamMapper implements RowMapper<SanPham> {
        @Override
        public SanPham mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new SanPham(
                rs.getInt("thp_MaSP"),
                rs.getString("thp_TenSP"),
                rs.getString("thp_LoaiSP"),
                rs.getString("thp_DonViTinh"),
                rs.getDouble("thp_GiaNhap"),
                rs.getInt("thp_SoLuongTon")
            );
        }
    }
}
