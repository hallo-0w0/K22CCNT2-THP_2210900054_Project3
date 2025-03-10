package com.springmvc.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springmvc.beans.thpSanPham;
import com.springmvc.dao.thpSanPhamDao;
import com.springmvc.dao.thpLoaiSanPhamDao;

@Controller
@RequestMapping("/thpsanpham")
public class thpSanPhamController {

    @Autowired  
    private thpSanPhamDao dao;

    @Autowired  
    private thpLoaiSanPhamDao loaiDao;

    @GetMapping("/thpview")
    public String viewSanPham(Model m) {
        List<thpSanPham> list = dao.getThpAllSanPhams();
        m.addAttribute("list", list);
        return "thpsanpham/thpview";
    }

    @GetMapping("/thpform")
    public String showForm(Model m) {
        m.addAttribute("command", new thpSanPham());
        m.addAttribute("listLoaiSP", loaiDao.getThpAllLoaiSanPhams());
        return "thpsanpham/thpform";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute thpSanPham sanPham, 
                       @RequestParam(value = "thpTenLoaiMoi", required = false) String thpTenLoaiMoi) { 
        if (thpTenLoaiMoi != null && !thpTenLoaiMoi.trim().isEmpty()) {
            int thpMaLoai = loaiDao.findOrCreateLoai(thpTenLoaiMoi);
            sanPham.setThpMaLoai(thpMaLoai);
        }
        dao.save(sanPham);
        return "redirect:/thpsanpham/thpview";
    }

    @PostMapping(value = "/addLoai", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> addLoai(@RequestParam("thpTenLoaiMoi") String thpTenLoaiMoi) {
        Map<String, Object> response = new HashMap<>();
        
        if (thpTenLoaiMoi != null && !thpTenLoaiMoi.trim().isEmpty()) {
            int thpMaLoai = loaiDao.findOrCreateLoai(thpTenLoaiMoi);
            response.put("success", true);
            response.put("thpMaLoai", thpMaLoai);
        } else {
            response.put("success", false);
            response.put("message", "Tên loại sản phẩm không hợp lệ!");
        }
        
        return response;
    }

    //xoa loại sản phẩm
    @DeleteMapping(value = "/deleteLoai", produces = "application/json")
    @ResponseBody
    public Map<String, Object> deleteLoai(@RequestParam("thpMaLoai") int thpMaLoai) {
        Map<String, Object> response = new HashMap<>();
        
        boolean isDeleted = loaiDao.deleteLoai(thpMaLoai);
        if (isDeleted) {
            response.put("success", true);
        } else {
            response.put("success", false);
            response.put("message", "Loại sản phẩm đang được sử dụng!");
        }
        
        return response;
    }
    
    

    // 🟡 Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("/thpedit/{thpMaSP}")
    public String edit(@PathVariable int thpMaSP, Model m) {
        thpSanPham sanPham = dao.getThpSanPhamById(thpMaSP);
        m.addAttribute("command", sanPham);
        m.addAttribute("listLoaiSP", loaiDao.getThpAllLoaiSanPhams());
        return "thpsanpham/thpedit";
    }

    // 🔵 Lưu chỉnh sửa sản phẩm
    @PostMapping("/thpeditsave")
    public String editSave(@ModelAttribute thpSanPham sanPham, 
                           @RequestParam(value = "thpTenLoaiMoi", required = false) String thpTenLoaiMoi) {
        if (thpTenLoaiMoi != null && !thpTenLoaiMoi.trim().isEmpty()) {
            int thpMaLoai = loaiDao.findOrCreateLoai(thpTenLoaiMoi);
            sanPham.setThpMaLoai(thpMaLoai);
        }
        dao.update(sanPham);
        return "redirect:/thpsanpham/thpview";
    }

    // 🔴 Xóa sản phẩm
    @GetMapping("/thpdelete/{thpMaSP}")
    public String delete(@PathVariable int thpMaSP) {
        dao.delete(thpMaSP);
        return "redirect:/thpsanpham/thpview";
    }
}
