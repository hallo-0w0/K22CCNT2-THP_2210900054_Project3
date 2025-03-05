package com.springmvc.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springmvc.beans.NhanVien;
import com.springmvc.dao.NhanVienDao;
import com.springmvc.enums.VaiTro;

@Controller
public class NhanVienController {

    @Autowired  
    private NhanVienDao dao; // Inject dao từ file cấu hình XML hoặc Annotation-based config

    // Hiển thị trang menu
    @GetMapping("/menu")
    public String getMenu() {
        return "menu"; // Hiển thị file WEB-INF/views/menu.jsp
    }

    // Hiển thị form thêm nhân viên mới
    @GetMapping("/nhanvien/form")
    public String showForm(Model m) {
        m.addAttribute("command", new NhanVien());
        m.addAttribute("vaiTroList", VaiTro.values()); // Truyền danh sách VaiTrò vào form
        return "nhanvien/form"; // Đường dẫn đúng theo cấu trúc thư mục
    }

    // Lưu nhân viên mới vào database
    @PostMapping("/nhanvien/save")
    public String save(@ModelAttribute("nhanVien") NhanVien nhanVien) {
        dao.save(nhanVien);
        return "redirect:/nhanvien/view"; // ✅ Loại bỏ SpringMVCPagination để tránh lỗi nhân đôi
    }

    // Hiển thị danh sách nhân viên
    @GetMapping("/nhanvien/view")
    public String viewNhanVien(Model m) {
        List<NhanVien> list = dao.getAllNhanViens();
        m.addAttribute("list", list);
        return "nhanvien/view"; // Đường dẫn đúng
    }

    // Hiển thị form chỉnh sửa thông tin nhân viên
    @GetMapping("/nhanvien/edit/{maNV}")
    public String edit(@PathVariable int maNV, Model m) {
        NhanVien nhanVien = dao.getNhanVienById(maNV);
        m.addAttribute("command", nhanVien);
        m.addAttribute("vaiTroList", VaiTro.values()); // Truyền danh sách VaiTrò vào form
        return "nhanvien/edit"; // Đường dẫn đúng
    }

    // Lưu chỉnh sửa thông tin nhân viên
    @PostMapping("/nhanvien/editsave")
    public String editSave(@ModelAttribute("nhanVien") NhanVien nhanVien) {
        dao.update(nhanVien);
        return "redirect:/nhanvien/view"; // ✅ Giữ nguyên đường dẫn đúng
    }

    // Xóa nhân viên theo ID
    @GetMapping("/nhanvien/delete/{maNV}")
    public String delete(@PathVariable int maNV) {
        dao.delete(maNV);
        return "redirect:/nhanvien/view"; // ✅ Giữ nguyên đường dẫn đúng
    }
}
