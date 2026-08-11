package com.example.fullness.stationary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.controller.form.LoginForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class AdminLoginController {

    @GetMapping("/admin/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
            Model model) {
        if (error != null && !model.containsAttribute("errorMessage")) {
            model.addAttribute("errorMessage", "アカウント名またはパスワードが正しくありません。");
        }
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new LoginForm());
        }
        return "admin/login";
    }

    @PostMapping("/admin/login")
    public String login(@Valid @ModelAttribute("form") LoginForm form, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // 入力チェック
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach(e -> errorMessages.add(e.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("errorMessage", String.join(" ", errorMessages));
            redirectAttributes.addFlashAttribute("form", form);
            return "redirect:/admin/login";
        }

        // バリデーション通過 → Spring Security認証エンドポイントにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/authenticate");
        dispatcher.forward(request, response);
        return null;
    }
}
