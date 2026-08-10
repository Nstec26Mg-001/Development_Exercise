package com.example.fullness.stationary.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.fullness.stationary.service.EmployeeAccountDetails;

@Controller
public class AdminMenuController {

    @GetMapping("/admin")
    public String menu(@AuthenticationPrincipal EmployeeAccountDetails userDetails, Model model) {
        model.addAttribute("loggedIn", true);
        model.addAttribute("loginEmployeeName", userDetails.getUsername());
        return "admin/menu";
    }
}
