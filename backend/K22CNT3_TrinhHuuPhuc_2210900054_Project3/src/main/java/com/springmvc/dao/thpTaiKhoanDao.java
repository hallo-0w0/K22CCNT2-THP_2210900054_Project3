package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.beans.thpTaiKhoan;
import com.springmvc.beans.thpNhanVien;

@Repository
public class thpTaiKhoanDao {

    @Autowired
    private JdbcTemplate template;

    // ✅ Thêm setter để Spring có thể inject template
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // ✅ Lấy danh sách tài khoản + tên nhân viên
    public List<thpTaiKhoan> getThpAllTaiKhoan() {
        String sql = "SELECT tk.thp_MaTK, tk.thp_Email, tk.thp_MatKhau, tk.thp_MaNV, nv.thp_HoTen " +
                     "FROM thp_TAI_KHOAN tk " +
                     "JOIN thp_NHAN_VIEN nv ON tk.thp_MaNV = nv.thp_MaNV";
        return template.query(sql, new RowMapper<thpTaiKhoan>() {
            @Override
            public thpTaiKhoan mapRow(ResultSet rs, int rowNum) throws SQLException {
                thpTaiKhoan tk = new thpTaiKhoan();
                tk.setThpMaTK(rs.getInt("thp_MaTK"));
                tk.setThpEmail(rs.getString("thp_Email"));
                tk.setThpMatKhau(rs.getString("thp_MatKhau"));

                // ✅ Kiểm tra nếu nhân viên không tồn tại
                thpNhanVien nv = new thpNhanVien();
                nv.setThpMaNV(rs.getInt("thp_MaNV"));
                nv.setThpHoTen(rs.getString("thp_HoTen"));

                tk.setThpNhanVien(nv); // Gán nhân viên vào tài khoản
                return tk;
            }
        });
    }

    // ✅ Thêm tài khoản mới
    public int save(thpTaiKhoan tk) {
        String sql = "INSERT INTO thp_TAI_KHOAN (thp_Email, thp_MatKhau, thp_MaNV) VALUES (?, ?, ?)";
        return template.update(sql, tk.getThpEmail(), tk.getThpMatKhau(),
                tk.getThpNhanVien() != null ? tk.getThpNhanVien().getThpMaNV() : null);
    }

    // ✅ Cập nhật tài khoản
    public int update(thpTaiKhoan tk) {
        String sql = "UPDATE thp_TAI_KHOAN SET thp_Email=?, thp_MatKhau=?, thp_MaNV=? WHERE thp_MaTK=?";
        return template.update(sql, tk.getThpEmail(), tk.getThpMatKhau(),
                tk.getThpNhanVien() != null ? tk.getThpNhanVien().getThpMaNV() : null, tk.getThpMaTK());
    }

    // ✅ Lấy tài khoản theo ID
    public thpTaiKhoan getThpTaiKhoanById(int thpMaTK) {
        String sql = "SELECT tk.thp_MaTK, tk.thp_Email, tk.thp_MatKhau, tk.thp_MaNV, nv.thp_HoTen " +
                     "FROM thp_TAI_KHOAN tk " +
                     "JOIN thp_NHAN_VIEN nv ON tk.thp_MaNV = nv.thp_MaNV " +
                     "WHERE tk.thp_MaTK=?";
        try {
            return template.queryForObject(sql, new Object[]{thpMaTK}, new RowMapper<thpTaiKhoan>() {
                @Override
                public thpTaiKhoan mapRow(ResultSet rs, int rowNum) throws SQLException {
                    thpTaiKhoan tk = new thpTaiKhoan();
                    tk.setThpMaTK(rs.getInt("thp_MaTK"));
                    tk.setThpEmail(rs.getString("thp_Email"));
                    tk.setThpMatKhau(rs.getString("thp_MatKhau"));

                    thpNhanVien nv = new thpNhanVien();
                    nv.setThpMaNV(rs.getInt("thp_MaNV"));
                    nv.setThpHoTen(rs.getString("thp_HoTen"));

                    tk.setThpNhanVien(nv);
                    return tk;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null; // Trả về null nếu không tìm thấy
        }
    }

    // ✅ Xóa tài khoản
    public int delete(int thpMaTK) {
        String sql = "DELETE FROM thp_TAI_KHOAN WHERE thp_MaTK=?";
        return template.update(sql, thpMaTK);
    }
}
