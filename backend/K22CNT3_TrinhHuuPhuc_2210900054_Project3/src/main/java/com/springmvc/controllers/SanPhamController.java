package com.springmvc.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springmvc.beans.SanPham;
import com.springmvc.dao.SanPhamDao;

@Controller
@RequestMapping("/sanpham")
public class SanPhamController {

    @Autowired  
    private SanPhamDao dao;

    // Hiển thị danh sách sản phẩm
    @GetMapping("/view")
    public String viewSanPham(Model m) {
        List<SanPham> list = dao.getAllSanPhams();
        m.addAttribute("list", list);
        return "sanpham/view";
    }

    // Hiển thị form thêm sản phẩm mới
    @GetMapping("/form")
    public String showForm(Model m) {
        m.addAttribute("command", new SanPham());
        return "sanpham/form";
    }

    // Lưu sản phẩm mới vào database
    @PostMapping("/save")
    public String save(@ModelAttribute("sanPham") SanPham sanPham) {
        dao.save(sanPham);
        return "redirect:/sanpham/view";
    }

    // Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("/edit/{maSP}")
    public String edit(@PathVariable int maSP, Model m) {
        SanPham sanPham = dao.getSanPhamById(maSP);
        m.addAttribute("command", sanPham);
        return "sanpham/edit";
    }

    // Lưu chỉnh sửa sản phẩm
    @PostMapping("/editsave")
    public String editSave(@ModelAttribute("sanPham") SanPham sanPham) {
        dao.update(sanPham);
        return "redirect:/sanpham/view";
    }

    // Xóa sản phẩm theo ID
    @GetMapping("/delete/{maSP}")
    public String delete(@PathVariable int maSP) {
        dao.delete(maSP);
        return "redirect:/sanpham/view";
    }
}
