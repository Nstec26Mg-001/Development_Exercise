package com.example.fullness.stationary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.service.ProductCategoryService;
import com.example.fullness.stationary.service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    private static final int PAGE_SIZE = 5;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    public String search(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(defaultValue = "1") int page,
            Model model) {

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
}
