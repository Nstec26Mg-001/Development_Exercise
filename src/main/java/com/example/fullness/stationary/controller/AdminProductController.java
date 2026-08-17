package com.example.fullness.stationary.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.controller.form.ProductRegisterForm;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.service.ProductCategoryService;
import com.example.fullness.stationary.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/product")
@SessionAttributes("addProductForm")
public class AdminProductController {

    private static final int PAGE_SIZE = 5;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    public String search(@RequestParam(required = false) Integer categoryId,
            @RequestParam(defaultValue = "1") int page, Model model) {

        List<Product> products;
        int totalCount;

        if (categoryId != null) {
            products = productService.findByCategoryId(categoryId, page, PAGE_SIZE);
            totalCount = productService.countByCategoryId(categoryId);
        } else {
            products = productService.findWithPagination(page, PAGE_SIZE);
            totalCount = productService.countAll();
        }

        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);

        model.addAttribute("products", products);
        model.addAttribute("categories", productCategoryService.findAll());
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "admin/product/search";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        if (!model.containsAttribute("addProductForm")) {
            model.addAttribute("addProductForm", new ProductRegisterForm());
        }
        model.addAttribute("categories", productCategoryService.findAll());
        return "admin/product/add_form";
    }

    @PostMapping("/add")
    public String addFormSubmit(@Valid @ModelAttribute("addProductForm") ProductRegisterForm form,
            BindingResult bindingResult,
            @RequestParam(value = "image", required = false) MultipartFile image,
            RedirectAttributes redirectAttributes) {

        List<String> errorMessages = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> errorMessages.add(e.getDefaultMessage()));
        }

        if (!errorMessages.isEmpty()) {
            redirectAttributes.addFlashAttribute("addProductForm", form);
            redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
            return "redirect:/admin/product/add";
        }

        ProductCategory category = productCategoryService.findById(form.getCategoryId());
        if (category != null) {
            form.setCategoryName(category.getName());
        }

        if (image != null && !image.isEmpty()) {
            String originalFilename = image.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String filename = UUID.randomUUID().toString() + extension;
            Path uploadDir = Paths.get("src/main/resources/static/images/products");
            try {
                Files.createDirectories(uploadDir);
                Path target = uploadDir.resolve(filename);
                Files.copy(image.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("画像の保存に失敗しました", e);
            }
            form.setImagePath("/images/products/" + filename);
        }

        redirectAttributes.addFlashAttribute("addProductForm", form);
        return "redirect:/admin/product/add/confirm";
    }

    @GetMapping("/add/confirm")
    public String addConfirm(Model model) {
        if (!model.containsAttribute("addProductForm")) {
            return "redirect:/admin/product/add";
        }
        return "admin/product/add_confirm";
    }

    @PostMapping("/add/confirm")
    public String addConfirmSubmit(@RequestParam String action, Model model,
            SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {

        ProductRegisterForm form = (ProductRegisterForm) model.getAttribute("addProductForm");

        if ("back".equals(action)) {
            redirectAttributes.addFlashAttribute("addProductForm", form);
            return "redirect:/admin/product/add";
        }

        productService.register(form);

        redirectAttributes.addFlashAttribute("productName", form.getName());
        sessionStatus.setComplete();
        return "redirect:/admin/product/add/complete";
    }

    @GetMapping("/add/complete")
    public String addComplete(Model model) {
        if (!model.containsAttribute("productName")) {
            return "redirect:/admin/product/add";
        }
        return "admin/product/add_complete";
    }
}
