package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.springmvc.beans.thpSanPham;

@Repository
public class thpSanPhamDao {
    
    @Autowired
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // ✅ Thêm sản phẩm mới
    public int save(thpSanPham sp) {
        String sql = "INSERT INTO thp_SAN_PHAM (thp_TenSP, thp_MaLoai, thp_DonViTinh, thp_GiaNhap, thp_SoLuongTon) VALUES (?, ?, ?, ?, ?)";
        return template.update(sql, sp.getThpTenSP(), sp.getThpMaLoai(), sp.getThpDonViTinh(), sp.getThpGiaNhap(), sp.getThpSoLuongTon());
    }

    // ✅ Cập nhật sản phẩm
    public int update(thpSanPham sp) {
        String sql = "UPDATE thp_SAN_PHAM SET thp_TenSP=?, thp_MaLoai=?, thp_DonViTinh=?, thp_GiaNhap=?, thp_SoLuongTon=? WHERE thp_MaSP=?";
        return template.update(sql, sp.getThpTenSP(), sp.getThpMaLoai(), sp.getThpDonViTinh(), sp.getThpGiaNhap(), sp.getThpSoLuongTon(), sp.getThpMaSP());
    }

    // ✅ Xóa sản phẩm theo ID
    public int delete(int thpMaSP) {
        String sql = "DELETE FROM thp_SAN_PHAM WHERE thp_MaSP=?";
        return template.update(sql, thpMaSP);
    }

    // ✅ Lấy danh sách tất cả sản phẩm (JOIN với `thp_LOAI_SAN_PHAM` để lấy `thpTenLoai`)
    public List<thpSanPham> getThpAllSanPhams() {
        String sql = "SELECT sp.*, lsp.thp_TenLoai FROM thp_SAN_PHAM sp " +
                     "LEFT JOIN thp_LOAI_SAN_PHAM lsp ON sp.thp_MaLoai = lsp.thp_MaLoai";
        return template.query(sql, new ThpSanPhamMapper());
    }

    // ✅ Lấy sản phẩm theo ID (cải thiện xử lý lỗi)
    public thpSanPham getThpSanPhamById(int thpMaSP) {
        String sql = "SELECT sp.*, lsp.thp_TenLoai FROM thp_SAN_PHAM sp " +
                     "LEFT JOIN thp_LOAI_SAN_PHAM lsp ON sp.thp_MaLoai = lsp.thp_MaLoai " +
                     "WHERE sp.thp_MaSP=?";
        try {
            return template.queryForObject(sql, new Object[]{thpMaSP}, new ThpSanPhamMapper());
        } catch (EmptyResultDataAccessException e) {
            return null; // Trả về null thay vì ném Exception
        }
    }

    // ✅ Lớp ánh xạ dữ liệu từ ResultSet sang thpSanPham
    private static class ThpSanPhamMapper implements RowMapper<thpSanPham> {
        @Override
        public thpSanPham mapRow(ResultSet rs, int rowNum) throws SQLException {
            thpSanPham sp = new thpSanPham(
                rs.getInt("thp_MaSP"),
                rs.getString("thp_TenSP"),
                rs.getInt("thp_MaLoai"),
                rs.getString("thp_DonViTinh"),
                rs.getDouble("thp_GiaNhap"),
                rs.getInt("thp_SoLuongTon")
            );
            sp.setThpTenLoai(rs.getString("thp_TenLoai")); // 🔹 Gán tên loại sản phẩm từ JOIN
            return sp;
        }
    }
}
