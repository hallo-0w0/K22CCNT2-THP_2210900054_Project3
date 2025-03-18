package com.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springmvc.dao.thpRegisterDao;

@Controller
@RequestMapping("/thpregister")
public class thpRegisterController {

    @Autowired
    private thpRegisterDao registerDao;

    // 🟢 Hiển thị form đăng ký
    @GetMapping("/thpview")
    public String showRegisterForm(Model model) {
        return "thpregister/thpview";
    }

    // 🟡 Xử lý đăng ký tài khoản
    @PostMapping("/thpsave")
    public String register(@RequestParam("thpHoTen") String hoTen,
                           @RequestParam("thpEmail") String email,
                           @RequestParam("thpMatKhau") String matKhau,
                           Model model) {

        // 🔍 Kiểm tra nếu email đã tồn tại
        if (registerDao.isEmailExists(email)) {
            model.addAttribute("error", "Email đã được sử dụng!");
            return "thpregister/thpview";
        }

        // 🏢 Thêm nhân viên trước
        Integer thpMaNV = registerDao.addNhanVien(hoTen);
        if (thpMaNV == null) {
            model.addAttribute("error", "Lỗi khi tạo nhân viên!");
            return "thpregister/thpview";
        }

        // 🆕 Thêm tài khoản sau khi nhân viên được tạo thành công
        boolean success = registerDao.saveTaiKhoan(email, matKhau, thpMaNV);
        if (!success) {
            model.addAttribute("error", "Lỗi khi tạo tài khoản!");
            return "thpregister/thpview";
        }

        return "redirect:/thplogin/thpview"; // ✅ Đăng ký thành công, chuyển đến trang đăng nhập
    }
}
