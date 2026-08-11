package com.example.fullness.stationary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.controller.form.EmployeeAccountRegisterForm;
import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.service.EmployeeAccountService;
import com.example.fullness.stationary.service.EmployeeService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/account")
@SessionAttributes("form")
public class AdminAccountController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeAccountService employeeAccountService;

    @GetMapping("/form")
    public String form(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new EmployeeAccountRegisterForm());
        }
        model.addAttribute("employees", employeeService.findWithoutAccount());
        return "admin/account/form";
    }

    @PostMapping("/form")
    public String formSubmit(@Valid @ModelAttribute("form") EmployeeAccountRegisterForm form,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // アノテーションバリデーション
        List<String> errorMessages = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> errorMessages.add(e.getDefaultMessage()));
        }

        // アカウント名重複チェック
        if (!bindingResult.hasFieldErrors("accountName") && form.getAccountName() != null
                && employeeAccountService.existsByName(form.getAccountName())) {
            errorMessages.add("そのアカウント名は既に使用されています。");
        }

        if (!errorMessages.isEmpty()) {
            redirectAttributes.addFlashAttribute("form", form);
            redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
            return "redirect:/admin/account/form";
        }

        // 社員名をセット
        Employee employee = employeeService.findById(form.getEmployeeId());
        form.setEmployeeName(employee.getName());
        redirectAttributes.addFlashAttribute("form", form);
        return "redirect:/admin/account/confirm";
    }

    @GetMapping("/confirm")
    public String confirm(Model model) {
        if (!model.containsAttribute("form")) {
            return "redirect:/admin/account/form";
        }
        return "admin/account/confirm";
    }

    @PostMapping("/confirm")
    public String confirmSubmit(@RequestParam String action, Model model,
            SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {

        EmployeeAccountRegisterForm form = (EmployeeAccountRegisterForm) model.getAttribute("form");

        if ("back".equals(action)) {
            redirectAttributes.addFlashAttribute("form", form);
            return "redirect:/admin/account/form";
        }

        // 登録処理
        employeeAccountService.register(form);

        redirectAttributes.addFlashAttribute("form", form);
        sessionStatus.setComplete();
        return "redirect:/admin/account/complete";
    }

    @GetMapping("/complete")
    public String complete(Model model) {
        if (!model.containsAttribute("form")) {
            return "redirect:/admin/account/form";
        }
        return "admin/account/complete";
    }

}
