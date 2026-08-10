package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminLoginController {

    @GetMapping("/admin/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "アカウント名またはパスワードが正しくありません。");
        }
        model.addAttribute("loggedIn", false);
        return "admin/login";
    }
}
