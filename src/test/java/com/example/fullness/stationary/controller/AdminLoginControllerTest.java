package com.example.fullness.stationary.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.fullness.stationary.config.SecurityConfig;

@WebMvcTest(AdminLoginController.class)
@Import(SecurityConfig.class)
public class AdminLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("ログイン画面表示OKテスト")
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/admin/login")).andExpect(status().isOk())
                .andExpect(view().name("admin/login"));
    }

    @Test
    @DisplayName("ログイン画面_エラーパラメータ付きOKテスト")
    public void testLoginPageWithError() throws Exception {
        mockMvc.perform(get("/admin/login").param("error", "")).andExpect(status().isOk())
                .andExpect(view().name("admin/login"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    @DisplayName("ログインPOST_バリデーションエラーテスト")
    public void testLoginPostValidationError() throws Exception {
        mockMvc.perform(
                post("/admin/login").with(csrf()).param("accountName", "").param("password", ""))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/admin/login"))
                .andExpect(flash().attributeExists("errorMessage"));
    }
}
