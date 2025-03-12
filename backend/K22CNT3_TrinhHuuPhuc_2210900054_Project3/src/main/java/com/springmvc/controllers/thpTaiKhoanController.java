package com.springmvc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springmvc.beans.thpTaiKhoan;
import com.springmvc.beans.thpNhanVien;
import com.springmvc.dao.thpTaiKhoanDao;
import com.springmvc.dao.thpNhanVienDao;

@Controller
@RequestMapping("/thptaikhoan")
public class thpTaiKhoanController {

    @Autowired  
    private thpTaiKhoanDao taiKhoanDao;

    @Autowired
    private thpNhanVienDao nhanVienDao; // Để lấy danh sách nhân viên

    // Load danh sách nhân viên vào form để chọn
    @ModelAttribute("thpNhanVienList")
    public List<thpNhanVien> getAllNhanVien() {
        return nhanVienDao.getAllthpNhanViens();
    }

    // Hiển thị danh sách tài khoản
    @GetMapping("/thpview")
    public String viewTaiKhoan(Model model) {
        List<thpTaiKhoan> list = taiKhoanDao.getThpAllTaiKhoan();
        model.addAttribute("list", list);
        return "thptaikhoan/thpview";
    }

    // Hiển thị form thêm tài khoản
    @GetMapping("/thpform")
    public String showForm(Model model) {
        model.addAttribute("command", new thpTaiKhoan());
        return "thptaikhoan/thpform";
    }

    // Lưu tài khoản mới
    @PostMapping("/save")
    public String save(@ModelAttribute("taiKhoan") thpTaiKhoan taiKhoan) {
        Optional<thpNhanVien> optionalNhanVien = nhanVienDao.getthpNhanVienById(taiKhoan.getThpNhanVien().getThpMaNV());

        if (optionalNhanVien.isPresent()) {
            taiKhoan.setThpNhanVien(optionalNhanVien.get());
            taiKhoanDao.save(taiKhoan);
        }

        return "redirect:/thptaikhoan/thpview";
    }

    // Hiển thị form chỉnh sửa tài khoản
    @GetMapping("/thpedit/{thpMaTK}")
    public String edit(@PathVariable int thpMaTK, Model model) {
        thpTaiKhoan taiKhoan = taiKhoanDao.getThpTaiKhoanById(thpMaTK);
        if (taiKhoan == null) {
            return "redirect:/thptaikhoan/thpview"; // Nếu không tìm thấy tài khoản, quay về danh sách
        }
        model.addAttribute("command", taiKhoan);
        return "thptaikhoan/thpedit";
    }

    // Lưu chỉnh sửa tài khoản
    @PostMapping("/thpeditsave")
    public String editSave(@ModelAttribute("taiKhoan") thpTaiKhoan taiKhoan) {
        Optional<thpNhanVien> optionalNhanVien = nhanVienDao.getthpNhanVienById(taiKhoan.getThpNhanVien().getThpMaNV());

        if (optionalNhanVien.isPresent()) {
            taiKhoan.setThpNhanVien(optionalNhanVien.get());
            taiKhoanDao.update(taiKhoan);
        }

        return "redirect:/thptaikhoan/thpview";
    }

    // Xóa tài khoản
    @GetMapping("/thpdelete/{thpMaTK}")
    public String delete(@PathVariable int thpMaTK) {
        taiKhoanDao.delete(thpMaTK);
        return "redirect:/thptaikhoan/thpview";
    }
}
