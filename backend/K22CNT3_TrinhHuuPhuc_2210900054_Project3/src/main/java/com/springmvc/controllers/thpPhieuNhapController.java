package com.springmvc.controllers;

import com.springmvc.beans.thpPhieuNhap;
import com.springmvc.beans.thpNhanVien;
import com.springmvc.beans.thpNhaCungCap;
import com.springmvc.dao.thpPhieuNhapDao;
import com.springmvc.dao.thpNhanVienDao;
import com.springmvc.dao.thpNhaCungCapDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;
import java.util.Date;

@Controller
@RequestMapping("/thpphieunhap")
public class thpPhieuNhapController {

    @Autowired
    private thpPhieuNhapDao dao;

    @Autowired
    private thpNhanVienDao nhanVienDao;

    @Autowired
    private thpNhaCungCapDao nhaCungCapDao;

    // ✅ Hiển thị danh sách Phiếu Nhập
    @GetMapping("/thpview")
    public String viewPhieuNhap(Model m, @RequestParam(value = "error", required = false) String error) {
        List<thpPhieuNhap> list = dao.getAllPhieuNhap();
        m.addAttribute("list", list);
        m.addAttribute("error", error);
        return "thpphieunhap/thpview";
    }

    // ✅ Hiển thị form thêm Phiếu Nhập
    @GetMapping("/thpform")
    public String showForm(Model m, @RequestParam(value = "error", required = false) String error) {
        m.addAttribute("command", new thpPhieuNhap());
        m.addAttribute("listNhanVien", nhanVienDao.getAllthpNhanViens());
        m.addAttribute("listNhaCungCap", nhaCungCapDao.getThpAllNhaCungCap());
        m.addAttribute("error", error);
        return "thpphieunhap/thpform";
    }

    // ✅ Lưu Phiếu Nhập mới
    @PostMapping("/save")
    public String save(
        @RequestParam("thpNgayNhap") @DateTimeFormat(pattern = "yyyy-MM-dd") Date thpNgayNhap,
        @RequestParam("thpMaNV") int thpMaNV,
        @RequestParam("thpMaNCC") int thpMaNCC
    ) {
        try {
            thpPhieuNhap phieuNhap = new thpPhieuNhap();
            phieuNhap.setThpNgayNhap(new java.sql.Date(thpNgayNhap.getTime()));
            phieuNhap.setThpMaNV(thpMaNV);
            phieuNhap.setThpMaNCC(thpMaNCC);

            dao.save(phieuNhap);
            return "redirect:/thpphieunhap/thpview";
        } catch (Exception e) {
            System.err.println("Lỗi khi lưu Phiếu Nhập: " + e.getMessage());
            return "redirect:/thpphieunhap/thpform?error=true";
        }
    }

    // ✅ Hiển thị form chỉnh sửa Phiếu Nhập
    @GetMapping("/thpedit/{thpMaPN}")
    public String edit(@PathVariable int thpMaPN, Model m) {
        thpPhieuNhap phieuNhap = dao.getPhieuNhapById(thpMaPN);
        if (phieuNhap == null) {
            return "redirect:/thpphieunhap/thpview?error=notfound";
        }
        m.addAttribute("command", phieuNhap);
        m.addAttribute("listNhanVien", nhanVienDao.getAllthpNhanViens());
        m.addAttribute("listNhaCungCap", nhaCungCapDao.getThpAllNhaCungCap());
        return "thpphieunhap/thpedit";
    }
}
