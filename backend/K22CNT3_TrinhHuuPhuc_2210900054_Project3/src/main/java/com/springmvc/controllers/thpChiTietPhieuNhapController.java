package com.springmvc.controllers;

import com.springmvc.beans.thpChiTietPhieuNhap;
import com.springmvc.dao.thpChiTietPhieuNhapDao;
import com.springmvc.dao.thpSanPhamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/thpchitietphieunhap")
public class thpChiTietPhieuNhapController {

    @Autowired
    private thpChiTietPhieuNhapDao chiTietPhieuNhapDao;

    @Autowired
    private thpSanPhamDao sanPhamDao;

    // ✅ Hiển thị danh sách toàn bộ chi tiết phiếu nhập
    @GetMapping("/thpview")
    public String viewAllChiTietPhieuNhap(Model m) {
        List<thpChiTietPhieuNhap> list = chiTietPhieuNhapDao.getAllChiTietPhieuNhap();
        m.addAttribute("list", list);
        return "thpchitietphieunhap/thpview";
    }

    // ✅ Hiển thị danh sách chi tiết theo mã phiếu nhập
    @GetMapping("/thpview/{thpMaPN}")
    public String viewChiTietPhieuNhap(@PathVariable int thpMaPN, Model m) {
        List<thpChiTietPhieuNhap> list = chiTietPhieuNhapDao.getAllChiTietByPhieuNhap(thpMaPN);
        m.addAttribute("list", list);
        m.addAttribute("thpMaPN", thpMaPN);
        return "thpchitietphieunhap/thpview";
    }

    

    // ✅ Hiển thị form thêm chi tiết phiếu nhập
    @GetMapping("/thpform")
    public String showForm(@RequestParam(required = false, defaultValue = "0") int thpMaPN, Model m) {
        m.addAttribute("command", new thpChiTietPhieuNhap());
        m.addAttribute("listSanPham", sanPhamDao.getThpAllSanPhams());
        m.addAttribute("thpMaPN", thpMaPN);
        return "thpchitietphieunhap/thpform";
    }


    // ✅ Lưu chi tiết phiếu nhập + Cập nhật số lượng sản phẩm
    @PostMapping("/save")
    public String save(@ModelAttribute thpChiTietPhieuNhap ctpn, Model model) {
        // 🛑 Kiểm tra dữ liệu đầu vào
        if (ctpn.getThpMaPN() <= 0 || ctpn.getThpMaSP() <= 0 || ctpn.getThpSoLuongNhap() <= 0) {
            model.addAttribute("error", "Dữ liệu không hợp lệ! Vui lòng nhập đầy đủ thông tin.");
            model.addAttribute("listSanPham", sanPhamDao.getThpAllSanPhams()); // 🔹 Giữ danh sách sản phẩm
            return "thpchitietphieunhap/thpform";
        }

        // 🛑 Kiểm tra số lượng nhập có quá lớn không?
        if (ctpn.getThpSoLuongNhap() > 1000000) {
            model.addAttribute("error", "Số lượng nhập quá lớn! Vui lòng nhập giá trị hợp lệ.");
            model.addAttribute("listSanPham", sanPhamDao.getThpAllSanPhams()); // 🔹 Giữ danh sách sản phẩm
            return "thpchitietphieunhap/thpform";
        }

        try {
            // 🛠 Ghi log để debug
            System.out.println("🔹 Đang lưu chi tiết phiếu nhập:");
            System.out.println("   ➜ Mã Phiếu Nhập: " + ctpn.getThpMaPN());
            System.out.println("   ➜ Mã Sản Phẩm: " + ctpn.getThpMaSP());
            System.out.println("   ➜ Số Lượng Nhập: " + ctpn.getThpSoLuongNhap());

            // 🛠 Lưu chi tiết phiếu nhập
            chiTietPhieuNhapDao.save(ctpn);

            // 🛠 Cập nhật số lượng tồn kho
            sanPhamDao.updateSoLuongTon(ctpn.getThpMaSP(), ctpn.getThpSoLuongNhap());

        } catch (Exception e) {
            // 🛑 Bắt lỗi và hiển thị thông báo
            model.addAttribute("error", "Lỗi khi lưu dữ liệu: " + e.getMessage());
            model.addAttribute("listSanPham", sanPhamDao.getThpAllSanPhams()); // 🔹 Giữ danh sách sản phẩm
            return "thpchitietphieunhap/thpform";
        }

        // ✅ Thành công → Chuyển hướng về danh sách
        return "redirect:/thpchitietphieunhap/thpview/" + ctpn.getThpMaPN();
    }


    // ✅ Hiển thị form chỉnh sửa chi tiết phiếu nhập
    @GetMapping("/thpedit/{thpMaPN}/{thpMaSP}")
    public String edit(@PathVariable int thpMaPN, @PathVariable int thpMaSP, Model m) {
        thpChiTietPhieuNhap ctpn = chiTietPhieuNhapDao.getChiTietById(thpMaPN, thpMaSP);
        if (ctpn == null) return "redirect:/thpchitietphieunhap/thpview/" + thpMaPN;
        m.addAttribute("command", ctpn);
        return "thpchitietphieunhap/thpedit";
    }

    // ✅ Lưu chỉnh sửa chi tiết phiếu nhập
    @PostMapping("/thpeditsave")
    public String editSave(@ModelAttribute thpChiTietPhieuNhap ctpn) {
        chiTietPhieuNhapDao.update(ctpn);
        return "redirect:/thpchitietphieunhap/thpview/" + ctpn.getThpMaPN();
    }

    // ✅ Xóa chi tiết phiếu nhập
    @GetMapping("/thpdelete/{thpMaPN}/{thpMaSP}")
    public String delete(@PathVariable int thpMaPN, @PathVariable int thpMaSP) {
        chiTietPhieuNhapDao.delete(thpMaPN, thpMaSP);
        return "redirect:/thpchitietphieunhap/thpview/" + thpMaPN;
    }
}
