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

import com.example.fullness.stationary.controller.form.ProductCategoryRegisterForm;
import com.example.fullness.stationary.service.ProductCategoryService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/category")
@SessionAttributes("categoryForm")
public class AdminCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/add")
    public String form(Model model) {
        if (!model.containsAttribute("categoryForm")) {
            model.addAttribute("categoryForm", new ProductCategoryRegisterForm());
        }
        return "admin/category/form";
    }

    @PostMapping("/add")
    public String formSubmit(
            @Valid @ModelAttribute("categoryForm") ProductCategoryRegisterForm form,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        List<String> errorMessages = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> errorMessages.add(e.getDefaultMessage()));
        }

        if (!bindingResult.hasFieldErrors("name") && form.getName() != null
                && productCategoryService.existsByName(form.getName())) {
            errorMessages.add("そのカテゴリ名は既に登録されています。");
        }

        if (!errorMessages.isEmpty()) {
            redirectAttributes.addFlashAttribute("categoryForm", form);
            redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
            return "redirect:/admin/category/add";
        }

        redirectAttributes.addFlashAttribute("categoryForm", form);
        return "redirect:/admin/category/add/confirm";
    }

    @GetMapping("/add/confirm")
    public String confirm(Model model) {
        if (!model.containsAttribute("categoryForm")) {
            return "redirect:/admin/category/add";
        }
        return "admin/category/confirm";
    }

    @PostMapping("/add/confirm")
    public String confirmSubmit(@RequestParam String action, Model model,
            SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {

        ProductCategoryRegisterForm form =
                (ProductCategoryRegisterForm) model.getAttribute("categoryForm");

        if ("back".equals(action)) {
            redirectAttributes.addFlashAttribute("categoryForm", form);
            return "redirect:/admin/category/add";
        }

        productCategoryService.register(form);

        redirectAttributes.addFlashAttribute("categoryName", form.getName());
        sessionStatus.setComplete();
        return "redirect:/admin/category/add/complete";
    }

    @GetMapping("/add/complete")
    public String complete(Model model) {
        if (!model.containsAttribute("categoryName")) {
            return "redirect:/admin/category/add";
        }
        return "admin/category/complete";
    }
}
