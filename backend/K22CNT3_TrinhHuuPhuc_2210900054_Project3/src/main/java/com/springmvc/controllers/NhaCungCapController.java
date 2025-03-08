package com.springmvc.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springmvc.beans.NhaCungCap;
import com.springmvc.dao.NhaCungCapDao;

@Controller
@RequestMapping("/nhacungcap")
public class NhaCungCapController {

    @Autowired  
    private NhaCungCapDao dao;

    // Hiển thị danh sách nhà cung cấp
    @GetMapping("/view")
    public String viewNhaCungCap(Model m) {
        List<NhaCungCap> list = dao.getAllNhaCungCap();
        m.addAttribute("list", list);
        return "nhacungcap/view";
    }

    // Hiển thị form thêm nhà cung cấp mới
    @GetMapping("/form")
    public String showForm(Model m) {
        m.addAttribute("command", new NhaCungCap());
        return "nhacungcap/form";
    }

    // Lưu nhà cung cấp mới vào database
    @PostMapping("/save")
    public String save(@ModelAttribute("nhaCungCap") NhaCungCap nhaCungCap) {
        dao.save(nhaCungCap);
        return "redirect:/nhacungcap/view";
    }

    // Hiển thị form chỉnh sửa nhà cung cấp
    @GetMapping("/edit/{maNCC}")
    public String edit(@PathVariable int maNCC, Model m) {
        NhaCungCap nhaCungCap = dao.getNhaCungCapById(maNCC);
        m.addAttribute("command", nhaCungCap);
        return "nhacungcap/edit";
    }

    // Lưu chỉnh sửa nhà cung cấp
    @PostMapping("/editsave")
    public String editSave(@ModelAttribute("nhaCungCap") NhaCungCap nhaCungCap) {
        dao.update(nhaCungCap);
        return "redirect:/nhacungcap/view";
    }

    // Xóa nhà cung cấp theo ID
    @GetMapping("/delete/{maNCC}")
    public String delete(@PathVariable int maNCC) {
        dao.delete(maNCC);
        return "redirect:/nhacungcap/view";
    }
}
