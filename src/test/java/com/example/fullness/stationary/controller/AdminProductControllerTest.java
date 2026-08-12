package com.example.fullness.stationary.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.fullness.stationary.config.SecurityConfig;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.service.ProductCategoryService;
import com.example.fullness.stationary.service.ProductService;

@WebMvcTest(AdminProductController.class)
@Import(SecurityConfig.class)
public class AdminProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @MockitoBean
    private ProductCategoryService productCategoryService;

    @Test
    @DisplayName("商品検索_全件表示OKテスト")
    @WithMockUser
    public void testSearchAll() throws Exception {
        List<Product> products = List.of(
                new Product(1, 1, "ボールペン(黒)", 150, "https://example.com/images/pen.jpg", 0, null),
                new Product(2, 1, "ノート(A5)", 200, "https://example.com/images/notebook.jpg", 0,
                        null));
        List<ProductCategory> categories = List.of(new ProductCategory(1, "文房具", null),
                new ProductCategory(2, "PC・周辺機器", null));

        when(productService.findWithPagination(1, 5)).thenReturn(products);
        when(productService.countAll()).thenReturn(2);
        when(productCategoryService.findAll()).thenReturn(categories);

        mockMvc.perform(get("/admin/product")).andExpect(status().isOk())
                .andExpect(view().name("admin/product/search"))
                .andExpect(model().attribute("products", products))
                .andExpect(model().attribute("categories", categories))
                .andExpect(model().attribute("currentPage", 1))
                .andExpect(model().attribute("totalPages", 1));

        verify(productService, times(1)).findWithPagination(1, 5);
        verify(productService, times(1)).countAll();
        verify(productCategoryService, times(1)).findAll();
    }

    @Test
    @DisplayName("商品検索_カテゴリ指定OKテスト")
    @WithMockUser
    public void testSearchByCategoryId() throws Exception {
        List<Product> products = List.of(
                new Product(1, 1, "ボールペン(黒)", 150, "https://example.com/images/pen.jpg", 0, null));
        List<ProductCategory> categories = List.of(new ProductCategory(1, "文房具", null));

        when(productService.findByCategoryId(1, 1, 5)).thenReturn(products);
        when(productService.countByCategoryId(1)).thenReturn(1);
        when(productCategoryService.findAll()).thenReturn(categories);

        mockMvc.perform(get("/admin/product").param("categoryId", "1")).andExpect(status().isOk())
                .andExpect(view().name("admin/product/search"))
                .andExpect(model().attribute("products", products))
                .andExpect(model().attribute("selectedCategoryId", 1))
                .andExpect(model().attribute("currentPage", 1))
                .andExpect(model().attribute("totalPages", 1));

        verify(productService, times(1)).findByCategoryId(1, 1, 5);
        verify(productService, times(1)).countByCategoryId(1);
    }

    @Test
    @DisplayName("商品検索_ページ指定OKテスト")
    @WithMockUser
    public void testSearchWithPage() throws Exception {
        List<Product> products = List.of(new Product(6, 3, "オフィスチェア", 19800,
                "https://example.com/images/chair.jpg", 0, null));
        List<ProductCategory> categories = List.of(new ProductCategory(1, "文房具", null));

        when(productService.findWithPagination(2, 5)).thenReturn(products);
        when(productService.countAll()).thenReturn(6);
        when(productCategoryService.findAll()).thenReturn(categories);

        mockMvc.perform(get("/admin/product").param("page", "2")).andExpect(status().isOk())
                .andExpect(view().name("admin/product/search"))
                .andExpect(model().attribute("products", products))
                .andExpect(model().attribute("currentPage", 2))
                .andExpect(model().attribute("totalPages", 2));

        verify(productService, times(1)).findWithPagination(2, 5);
    }

    @Test
    @DisplayName("商品検索_未認証時リダイレクトテスト")
    public void testSearchUnauthenticated() throws Exception {
        mockMvc.perform(get("/admin/product")).andExpect(status().is3xxRedirection());
    }
}
