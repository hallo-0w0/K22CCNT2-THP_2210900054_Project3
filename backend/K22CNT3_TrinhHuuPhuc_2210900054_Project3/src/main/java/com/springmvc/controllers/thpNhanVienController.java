package com.springmvc.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springmvc.beans.thpNhanVien;
import com.springmvc.dao.thpNhanVienDao;
import com.springmvc.enums.thpVaiTro;

@Controller
public class thpNhanVienController {

    @Autowired  
    private thpNhanVienDao dao;

    @GetMapping("/thpmenu")
    public String getthpMenu() {
        return "thpmenu";
    }

    @GetMapping("/thpnhanvien/thpform")
    public String showForm(Model m) {
        m.addAttribute("command", new thpNhanVien());
        m.addAttribute("vaiTroList", thpVaiTro.values());
        return "thpnhanvien/thpform";
    }

    @PostMapping("/thpnhanvien/save")
    public String save(@ModelAttribute("thpnhanVien") thpNhanVien thpnhanVien) {
        if (thpnhanVien.getThpVaiTro() == null) {
            thpnhanVien.setThpVaiTro(thpVaiTro.NHAN_VIEN);
        }
        dao.save(thpnhanVien);
        return "redirect:/thpnhanvien/thpview";
    }

    @GetMapping("/thpnhanvien/thpview")
    public String viewNhanVien(Model m) {
        List<thpNhanVien> list = dao.getAllthpNhanViens();
        m.addAttribute("list", list);
        return "thpnhanvien/thpview";
    }

    @GetMapping("/thpnhanvien/thpedit/{thpMaNV}")
    public String edit(@PathVariable int thpMaNV, Model m) {
        Optional<thpNhanVien> optionalNhanVien = dao.getthpNhanVienById(thpMaNV);
        if (optionalNhanVien.isPresent()) {
            m.addAttribute("command", optionalNhanVien.get());
            m.addAttribute("vaiTroList", thpVaiTro.values());
            return "thpnhanvien/thpedit";
        }
        return "redirect:/thpnhanvien/thpview";
    }

    @PostMapping("/thpnhanvien/thpeditsave")
    public String editSave(@ModelAttribute("thpnhanVien") thpNhanVien thpnhanVien) {
        if (thpnhanVien.getThpVaiTro() == null) {
            thpnhanVien.setThpVaiTro(thpVaiTro.NHAN_VIEN);
        }
        dao.update(thpnhanVien);
        return "redirect:/thpnhanvien/thpview";
    }

    @GetMapping("/thpnhanvien/thpdelete/{thpMaNV}")
    public String delete(@PathVariable int thpMaNV, Model m) {
        try {
            dao.delete(thpMaNV);
        } catch (Exception e) {
            m.addAttribute("error", "Không thể xóa nhân viên vì có dữ liệu liên quan!");
            return "thpnhanvien/thpview";
        }
        return "redirect:/thpnhanvien/thpview";
    }
}
