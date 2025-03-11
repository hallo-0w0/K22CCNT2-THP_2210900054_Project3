package com.springmvc.dao;

import com.springmvc.beans.thpPhieuNhap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@Repository
public class thpPhieuNhapDao {

    private JdbcTemplate template;

    // ✅ Thêm setter để Spring có thể inject `JdbcTemplate`
    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // ✅ Lấy danh sách tất cả Phiếu Nhập
    public List<thpPhieuNhap> getAllPhieuNhap() {
        String sql = "SELECT pn.*, nv.thp_HoTen AS tenNhanVien, ncc.thp_TenNCC AS tenNhaCungCap " +
                     "FROM thp_PHIEU_NHAP pn " +
                     "LEFT JOIN thp_NHAN_VIEN nv ON pn.thp_MaNV = nv.thp_MaNV " +
                     "LEFT JOIN thp_NHA_CUNG_CAP ncc ON pn.thp_MaNCC = ncc.thp_MaNCC";
        return template.query(sql, new ThpPhieuNhapMapper());
    }

    // ✅ Lấy Phiếu Nhập theo ID
    public thpPhieuNhap getPhieuNhapById(int thpMaPN) {
        String sql = "SELECT pn.*, nv.thp_HoTen AS tenNhanVien, ncc.thp_TenNCC AS tenNhaCungCap " +
                     "FROM thp_PHIEU_NHAP pn " +
                     "LEFT JOIN thp_NHAN_VIEN nv ON pn.thp_MaNV = nv.thp_MaNV " +
                     "LEFT JOIN thp_NHA_CUNG_Cap ncc ON pn.thp_MaNCC = ncc.thp_MaNCC " +
                     "WHERE pn.thp_MaPN = ?";
        try {
            return template.queryForObject(sql, new Object[]{thpMaPN}, new ThpPhieuNhapMapper());
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy Phiếu Nhập ID " + thpMaPN + ": " + e.getMessage());
            return null;
        }
    }

    // ✅ Thêm mới Phiếu Nhập
    public int save(thpPhieuNhap phieuNhap) {
        String sql = "INSERT INTO thp_PHIEU_NHAP (thp_NgayNhap, thp_MaNV, thp_MaNCC) VALUES (?, ?, ?)";
        return template.update(sql, phieuNhap.getThpNgayNhap(), phieuNhap.getThpMaNV(), phieuNhap.getThpMaNCC());
    }


    // ✅ Cập nhật Phiếu Nhập
    public int update(thpPhieuNhap phieuNhap) {
        String sql = "UPDATE thp_PHIEU_NHAP SET thp_NgayNhap=?, thp_MaNV=?, thp_MaNCC=? WHERE thp_MaPN=?";
        try {
            Date sqlDate = new Date(phieuNhap.getThpNgayNhap().getTime());
            return template.update(sql, sqlDate, phieuNhap.getThpMaNV(), phieuNhap.getThpMaNCC(), phieuNhap.getThpMaPN());
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật Phiếu Nhập ID " + phieuNhap.getThpMaPN() + ": " + e.getMessage());
            return 0;
        }
    }

    // ✅ Xóa Phiếu Nhập
    public int delete(int thpMaPN) {
        String sql = "DELETE FROM thp_PHIEU_NHAP WHERE thp_MaPN=?";
        try {
            return template.update(sql, thpMaPN);
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa Phiếu Nhập ID " + thpMaPN + ": " + e.getMessage());
            return 0;
        }
    }

    // ✅ Lớp ánh xạ ResultSet sang đối tượng thpPhieuNhap
    private static class ThpPhieuNhapMapper implements RowMapper<thpPhieuNhap> {
        @Override
        public thpPhieuNhap mapRow(ResultSet rs, int rowNum) throws SQLException {
            thpPhieuNhap phieuNhap = new thpPhieuNhap(
                rs.getInt("thp_MaPN"),
                rs.getDate("thp_NgayNhap"),
                rs.getInt("thp_MaNV"),
                rs.getInt("thp_MaNCC")
            );
            phieuNhap.setTenNhanVien(rs.getString("tenNhanVien")); 
            phieuNhap.setTenNhaCungCap(rs.getString("tenNhaCungCap")); 
            return phieuNhap;
        }
    }
}
