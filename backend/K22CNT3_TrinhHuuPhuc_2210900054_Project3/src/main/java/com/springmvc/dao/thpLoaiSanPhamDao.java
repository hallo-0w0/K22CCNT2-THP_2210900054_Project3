package com.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.springmvc.beans.thpLoaiSanPham;

@Repository
public class thpLoaiSanPhamDao {

    @Autowired
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // ✅ Lấy danh sách tất cả loại sản phẩm
    public List<thpLoaiSanPham> getThpAllLoaiSanPhams() {
        String sql = "SELECT * FROM thp_LOAI_SAN_PHAM";
        return template.query(sql, new ThpLoaiSanPhamMapper());
    }
    
    //xóa loại sp
    public boolean deleteLoai(int thpMaLoai) {
        String checkSql = "SELECT COUNT(*) FROM thp_SAN_PHAM WHERE thp_MaLoai = ?";
        int count = template.queryForObject(checkSql, new Object[]{thpMaLoai}, Integer.class);

        if (count > 0) {
            return false; // Không thể xóa vì có sản phẩm đang dùng loại này
        }

        String deleteSql = "DELETE FROM thp_LOAI_SAN_PHAM WHERE thp_MaLoai = ?";
        template.update(deleteSql, thpMaLoai);
        return true;
    }
       

    

    // ✅ Tìm hoặc thêm mới loại sản phẩm nếu chưa tồn tại
    public int findOrCreateLoai(String thpTenLoai) {
        String checkSql = "SELECT thp_MaLoai FROM thp_LOAI_SAN_PHAM WHERE thp_TenLoai = ?";
        try {
            return template.queryForObject(checkSql, new Object[]{thpTenLoai}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            // 🔹 Thêm mới loại sản phẩm nếu chưa có
            String insertSql = "INSERT INTO thp_LOAI_SAN_PHAM (thp_TenLoai) VALUES (?)";
            template.update(insertSql, thpTenLoai);
            
            // 🔹 Lấy ID mới nhất vừa chèn (tránh lỗi khi nhiều luồng chạy đồng thời)
            return template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        }
    }


    // ✅ Lớp ánh xạ ResultSet sang đối tượng thpLoaiSanPham
    private static class ThpLoaiSanPhamMapper implements RowMapper<thpLoaiSanPham> {
        @Override
        public thpLoaiSanPham mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new thpLoaiSanPham(
                rs.getInt("thp_MaLoai"),
                rs.getString("thp_TenLoai")
            );
        }
    }
}
