package com.springmvc.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springmvc.beans.thpTaiKhoan;
import com.springmvc.dao.thpLoginDao;

@Controller
@RequestMapping("/thplogin")
public class thpLoginController {

    @Autowired
    private thpLoginDao loginDao;

    // 🔹 Hiển thị form đăng nhập
    @GetMapping("/thpview")
    public String showLoginForm(Model model) {
        model.addAttribute("taiKhoan", new thpTaiKhoan());
        return "thplogin/thpview"; // Chỉ định JSP form đăng nhập
    }

    // 🔹 Xử lý đăng nhập
    @PostMapping("/thpauth")
    public String login(@ModelAttribute("taiKhoan") thpTaiKhoan taiKhoan, HttpSession session, Model model) {
        thpTaiKhoan user = loginDao.login(taiKhoan.getThpEmail(), taiKhoan.getThpMatKhau());

        if (user != null) {
            session.setAttribute("loggedUser", user); // Lưu thông tin đăng nhập vào session
            return "redirect:/thpmenu"; // Chuyển hướng sau khi đăng nhập thành công
        } else {
            model.addAttribute("error", "Email hoặc mật khẩu không đúng!");
            return "thplogin/thpview";
        }
    }

    // 🔹 Xử lý đăng xuất
    @GetMapping("/thplogout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa session
        return "redirect:/thplogin/thpview";
    }
}
