package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springmvc.beans.NhanVien;
import com.springmvc.enums.VaiTro;

@Repository // Đánh dấu class này là Bean của Spring (chỉ cần khi dùng Annotation-based config)
public class NhanVienDao {
    
    @Autowired
    private JdbcTemplate template;

    // Nếu dùng XML config, cần setter này
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // Thêm nhân viên
    public int save(NhanVien nv) {
        String sql = "INSERT INTO thp_NHAN_VIEN (thp_HoTen, thp_PhongBan, thp_VaiTro) VALUES (?, ?, ?)";
        return template.update(sql, nv.getHoTen(), nv.getPhongBan(), nv.getVaiTro().name());
    }

    // Cập nhật nhân viên
    public int update(NhanVien nv) {
        String sql = "UPDATE thp_NHAN_VIEN SET thp_HoTen=?, thp_PhongBan=?, thp_VaiTro=? WHERE thp_MaNV=?";
        return template.update(sql, nv.getHoTen(), nv.getPhongBan(), nv.getVaiTro().name(), nv.getMaNV());
    }

    // Xóa nhân viên
    public int delete(int maNhanVien) {
        String sql = "DELETE FROM thp_NHAN_VIEN WHERE thp_MaNV=?";
        return template.update(sql, maNhanVien);
    }

    // Lấy nhân viên theo ID
    public NhanVien getNhanVienById(int maNhanVien) {
        String sql = "SELECT * FROM thp_NHAN_VIEN WHERE thp_MaNV=?";
        try {
            return template.queryForObject(sql, new Object[]{maNhanVien}, new NhanVienMapper());
        } catch (Exception e) {
            return null; // Tránh lỗi khi không tìm thấy nhân viên
        }
    }

    // Lấy danh sách nhân viên
    public List<NhanVien> getAllNhanViens() {
        String sql = "SELECT * FROM thp_NHAN_VIEN";
        return template.query(sql, new NhanVienMapper());
    }

    // Lớp riêng để ánh xạ ResultSet sang NhanVien
    private static class NhanVienMapper implements RowMapper<NhanVien> {
        @Override
        public NhanVien mapRow(ResultSet rs, int rowNum) throws SQLException {
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getInt("thp_MaNV"));
            nv.setHoTen(rs.getString("thp_HoTen"));
            nv.setPhongBan(rs.getString("thp_PhongBan"));
            nv.setVaiTro(VaiTro.fromString(rs.getString("thp_VaiTro"))); // Enum xử lý an toàn
            return nv;
        }
    }
}
