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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/thpphieunhap")
public class thpPhieuNhapController {

    @Autowired
    private thpPhieuNhapDao dao; 

    @Autowired
    private thpNhanVienDao nhanVienDao; 

    @Autowired
    private thpNhaCungCapDao nhaCungCapDao; 

    // ✅ Xử lý chuyển đổi String -> Date
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    // ✅ Hiển thị danh sách Phiếu Nhập
    @GetMapping("/thpview")
    public String viewPhieuNhap(Model m) {
        List<thpPhieuNhap> list = dao.getAllPhieuNhap();
        m.addAttribute("list", list);
        return "thpphieunhap/thpview";
    }

    // ✅ Hiển thị form thêm Phiếu Nhập
    @GetMapping("/thpform")
    public String showForm(Model m) {
        m.addAttribute("command", new thpPhieuNhap());
        m.addAttribute("listNhanVien", nhanVienDao.getAllthpNhanViens());
        m.addAttribute("listNhaCungCap", nhaCungCapDao.getThpAllNhaCungCap());

        // Truyền ngày hiện tại vào form
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        m.addAttribute("currentDate", sdf.format(new Date()));

        return "thpphieunhap/thpform";
    }

    // ✅ Lưu Phiếu Nhập mới
    @PostMapping("/save")
    public String save(@ModelAttribute thpPhieuNhap phieuNhap) {
        try {
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
            return "redirect:/thpphieunhap/thpview?notfound=true";
        }
        m.addAttribute("command", phieuNhap);
        m.addAttribute("listNhanVien", nhanVienDao.getAllthpNhanViens());
        m.addAttribute("listNhaCungCap", nhaCungCapDao.getThpAllNhaCungCap());

        return "thpphieunhap/thpedit";
    }

    // ✅ Lưu chỉnh sửa Phiếu Nhập
    @PostMapping("/thpeditsave")
    public String editSave(@ModelAttribute thpPhieuNhap phieuNhap) {
        try {
            dao.update(phieuNhap);
            return "redirect:/thpphieunhap/thpview";
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật Phiếu Nhập: " + e.getMessage());
            return "redirect:/thpphieunhap/thpedit/" + phieuNhap.getThpMaPN() + "?error=true";
        }
    }

    // ✅ Xóa Phiếu Nhập
    @GetMapping("/thpdelete/{thpMaPN}")
    public String delete(@PathVariable int thpMaPN) {
        try {
            dao.delete(thpMaPN);
            return "redirect:/thpphieunhap/thpview";
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa Phiếu Nhập: " + e.getMessage());
            return "redirect:/thpphieunhap/thpview?deleteerror=true";
        }
    }
}
