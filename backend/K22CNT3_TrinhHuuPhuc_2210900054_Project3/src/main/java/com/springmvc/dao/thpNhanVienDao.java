package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springmvc.beans.thpNhanVien;
import com.springmvc.enums.thpVaiTro;

@Repository
public class thpNhanVienDao {
    
    @Autowired
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // 🛠 Chèn nhân viên (tránh lỗi null)
    public int save(thpNhanVien nv) {
        String sql = "INSERT INTO thp_NHAN_VIEN (thp_HoTen, thp_PhongBan, thp_VaiTro) VALUES (?, ?, ?)";
        String vaiTro = (nv.getThpVaiTro() != null) ? nv.getThpVaiTro().name() : thpVaiTro.NHAN_VIEN.name();
        return template.update(sql, nv.getThpHoTen(), nv.getThpPhongBan(), vaiTro);
    }

    // 🛠 Cập nhật nhân viên (tránh lỗi null)
    public int update(thpNhanVien nv) {
        String sql = "UPDATE thp_NHAN_VIEN SET thp_HoTen=?, thp_PhongBan=?, thp_VaiTro=? WHERE thp_MaNV=?";
        String vaiTro = (nv.getThpVaiTro() != null) ? nv.getThpVaiTro().name() : thpVaiTro.NHAN_VIEN.name();
        return template.update(sql, nv.getThpHoTen(), nv.getThpPhongBan(), vaiTro, nv.getThpMaNV());
    }

    // 🛠 Xóa nhân viên
    public int delete(int mathpNhanVien) {
        String sql = "DELETE FROM thp_NHAN_VIEN WHERE thp_MaNV=?";
        return template.update(sql, mathpNhanVien);
    }

    // 🛠 Lấy nhân viên theo ID (tránh null)
    public Optional<thpNhanVien> getthpNhanVienById(int mathpNhanVien) {
        String sql = "SELECT * FROM thp_NHAN_VIEN WHERE thp_MaNV=?";
        try {
            return Optional.ofNullable(template.queryForObject(sql, new Object[]{mathpNhanVien}, new thpNhanVienMapper()));
        } catch (Exception e) {
            return Optional.empty(); // Trả về Optional rỗng thay vì null
        }
    }

    // 🛠 Lấy danh sách nhân viên
    public List<thpNhanVien> getAllthpNhanViens() {
        String sql = "SELECT * FROM thp_NHAN_VIEN";
        return template.query(sql, new thpNhanVienMapper());
    }

    // 🛠 Lớp ánh xạ ResultSet sang thpNhanVien (tránh lỗi NullPointerException)
    private static class thpNhanVienMapper implements RowMapper<thpNhanVien> {
        @Override
        public thpNhanVien mapRow(ResultSet rs, int rowNum) throws SQLException {
            thpNhanVien nv = new thpNhanVien();
            nv.setThpMaNV(rs.getInt("thp_MaNV"));
            nv.setThpHoTen(rs.getString("thp_HoTen"));
            nv.setThpPhongBan(rs.getString("thp_PhongBan"));
            
            // 🛠 Tránh lỗi NullPointerException
            String vaiTroStr = rs.getString("thp_VaiTro");
            nv.setThpVaiTro(vaiTroStr != null ? thpVaiTro.fromString(vaiTroStr) : thpVaiTro.NHAN_VIEN);

            return nv;
        }
    }
}
