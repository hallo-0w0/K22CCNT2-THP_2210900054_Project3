package com.springmvc.dao;

import com.springmvc.beans.thpChiTietPhieuNhap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class thpChiTietPhieuNhapDao {

    private JdbcTemplate template;

    // ✅ Setter để Spring inject `JdbcTemplate`
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
 // ✅ Cập nhật số lượng nhập trong chi tiết phiếu nhập
    public int update(thpChiTietPhieuNhap ctpn) {
        String sql = "UPDATE thp_CHI_TIET_NHAP SET thp_SoLuongNhap = ? WHERE thp_MaPN = ? AND thp_MaSP = ?";
        return template.update(sql, ctpn.getThpSoLuongNhap(), ctpn.getThpMaPN(), ctpn.getThpMaSP());
    }


    // ✅ Lấy danh sách toàn bộ Chi Tiết Phiếu Nhập
    public List<thpChiTietPhieuNhap> getAllChiTietPhieuNhap() {
        String sql = "SELECT ctpn.*, sp.thp_TenSP " +
                     "FROM thp_CHI_TIET_NHAP ctpn " +
                     "LEFT JOIN thp_SAN_PHAM sp ON ctpn.thp_MaSP = sp.thp_MaSP";
        return template.query(sql, new ThpChiTietPhieuNhapMapper());
    }

    // ✅ Lấy danh sách chi tiết theo mã phiếu nhập
    public List<thpChiTietPhieuNhap> getAllChiTietByPhieuNhap(int thpMaPN) {
        String sql = "SELECT ctpn.*, sp.thp_TenSP FROM thp_CHI_TIET_NHAP ctpn " +
                     "LEFT JOIN thp_SAN_PHAM sp ON ctpn.thp_MaSP = sp.thp_MaSP " +
                     "WHERE ctpn.thp_MaPN = ?";
        return template.query(sql, new Object[]{thpMaPN}, new ThpChiTietPhieuNhapMapper());
    }

    // ✅ Lấy chi tiết phiếu nhập theo mã phiếu nhập và mã sản phẩm
    public thpChiTietPhieuNhap getChiTietById(int thpMaPN, int thpMaSP) {
        String sql = "SELECT ctpn.*, sp.thp_TenSP FROM thp_CHI_TIET_NHAP ctpn " +
                     "LEFT JOIN thp_SAN_PHAM sp ON ctpn.thp_MaSP = sp.thp_MaSP " +
                     "WHERE ctpn.thp_MaPN = ? AND ctpn.thp_MaSP = ?";
        try {
            return template.queryForObject(sql, new Object[]{thpMaPN, thpMaSP}, new ThpChiTietPhieuNhapMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // ✅ Thêm chi tiết phiếu nhập (Kiểm tra nếu sản phẩm đã tồn tại -> cập nhật số lượng)
    public int save(thpChiTietPhieuNhap ctpn) {
        thpChiTietPhieuNhap existing = getChiTietById(ctpn.getThpMaPN(), ctpn.getThpMaSP());

        if (existing != null) {
            // Nếu sản phẩm đã có trong phiếu nhập, cập nhật số lượng
            int newSoLuong = existing.getThpSoLuongNhap() + ctpn.getThpSoLuongNhap();
            return updateSoLuongNhap(ctpn.getThpMaPN(), ctpn.getThpMaSP(), newSoLuong);
        } else {
            // Nếu sản phẩm chưa có trong phiếu nhập, thêm mới
            String sql = "INSERT INTO thp_CHI_TIET_NHAP (thp_MaPN, thp_MaSP, thp_SoLuongNhap) VALUES (?, ?, ?)";
            int result = template.update(sql, ctpn.getThpMaPN(), ctpn.getThpMaSP(), ctpn.getThpSoLuongNhap());

            // Cập nhật số lượng tồn kho
            updateSoLuongTon(ctpn.getThpMaSP(), ctpn.getThpSoLuongNhap());
            return result;
        }
    }

    // ✅ Cập nhật số lượng nhập
    public int updateSoLuongNhap(int thpMaPN, int thpMaSP, int newSoLuong) {
        String sql = "UPDATE thp_CHI_TIET_NHAP SET thp_SoLuongNhap = ? WHERE thp_MaPN = ? AND thp_MaSP = ?";
        int result = template.update(sql, newSoLuong, thpMaPN, thpMaSP);

        // Cập nhật số lượng tồn kho
        updateSoLuongTon(thpMaSP, newSoLuong);
        return result;
    }

    // ✅ Xóa chi tiết phiếu nhập
    public int delete(int thpMaPN, int thpMaSP) {
        String sql = "DELETE FROM thp_CHI_TIET_NHAP WHERE thp_MaPN = ? AND thp_MaSP = ?";
        return template.update(sql, thpMaPN, thpMaSP);
    }

    // ✅ Cập nhật số lượng sản phẩm trong kho
    public int updateSoLuongTon(int thpMaSP, int soLuongNhap) {
        String sql = "UPDATE thp_SAN_PHAM SET thp_SoLuongTon = thp_SoLuongTon + ? WHERE thp_MaSP = ?";
        return template.update(sql, soLuongNhap, thpMaSP);
    }

    // ✅ Lớp ánh xạ từ ResultSet sang đối tượng `thpChiTietPhieuNhap`
    private static class ThpChiTietPhieuNhapMapper implements RowMapper<thpChiTietPhieuNhap> {
        @Override
        public thpChiTietPhieuNhap mapRow(ResultSet rs, int rowNum) throws SQLException {
            thpChiTietPhieuNhap ctpn = new thpChiTietPhieuNhap();
            ctpn.setThpMaPN(rs.getInt("thp_MaPN"));
            ctpn.setThpMaSP(rs.getInt("thp_MaSP"));
            ctpn.setThpTenSP(rs.getString("thp_TenSP")); // Lấy tên sản phẩm từ JOIN
            ctpn.setThpSoLuongNhap(rs.getInt("thp_SoLuongNhap"));
            return ctpn;
        }
    }
}
