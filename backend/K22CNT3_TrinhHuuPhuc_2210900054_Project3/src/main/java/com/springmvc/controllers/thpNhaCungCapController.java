package com.springmvc.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springmvc.beans.thpNhaCungCap;
import com.springmvc.dao.thpNhaCungCapDao;

@Controller
@RequestMapping("/thpnhacungcap")
public class thpNhaCungCapController {

    @Autowired  
    private thpNhaCungCapDao dao;

    // Hiển thị danh sách nhà cung cấp
    @GetMapping("/thpview")
    public String viewNhaCungCap(Model m) {
        List<thpNhaCungCap> list = dao.getThpAllNhaCungCap();
        m.addAttribute("list", list);
        return "thpnhacungcap/thpview";
    }

    // Hiển thị form thêm nhà cung cấp mới
    @GetMapping("/thpform")
    public String showForm(Model m) {
        m.addAttribute("command", new thpNhaCungCap());
        return "thpnhacungcap/thpform";
    }

    // Lưu nhà cung cấp mới vào database
    @PostMapping("/save")
    public String save(@ModelAttribute("nhaCungCap") thpNhaCungCap nhaCungCap) {
        dao.save(nhaCungCap);
        return "redirect:/thpnhacungcap/thpview";
    }

    // Hiển thị form chỉnh sửa nhà cung cấp
    @GetMapping("/thpedit/{thpMaNCC}")
    public String edit(@PathVariable int thpMaNCC, Model m) {
        thpNhaCungCap nhaCungCap = dao.getThpNhaCungCapById(thpMaNCC);
        m.addAttribute("command", nhaCungCap);
        return "thpnhacungcap/thpedit";
    }

    // Lưu chỉnh sửa nhà cung cấp
    @PostMapping("/thpeditsave")
    public String editSave(@ModelAttribute("nhaCungCap") thpNhaCungCap nhaCungCap) {
        dao.update(nhaCungCap);
        return "redirect:/thpnhacungcap/thpview";
    }

    // Xóa nhà cung cấp theo ID
    @GetMapping("/thpdelete/{thpMaNCC}")
    public String delete(@PathVariable int thpMaNCC) {
        dao.delete(thpMaNCC);
        return "redirect:/thpnhacungcap/thpview";
    }
}
