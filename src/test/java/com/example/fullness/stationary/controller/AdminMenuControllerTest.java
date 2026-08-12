package com.example.fullness.stationary.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.fullness.stationary.config.SecurityConfig;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.service.EmployeeAccountDetails;

@WebMvcTest(AdminMenuController.class)
@Import(SecurityConfig.class)
public class AdminMenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("メニュー画面表示_認証済みOKテスト")
    public void testMenuAuthenticated() throws Exception {
        EmployeeAccount account = new EmployeeAccount(1, 1, "testuser", "password");
        EmployeeAccountDetails userDetails =
                new EmployeeAccountDetails(account, Collections.emptyList());

        mockMvc.perform(get("/admin").with(user(userDetails))).andExpect(status().isOk())
                .andExpect(view().name("admin/menu"))
                .andExpect(model().attribute("loginEmployeeName", "testuser"));
    }

    @Test
    @DisplayName("メニュー画面_未認証時リダイレクトテスト")
    public void testMenuUnauthenticated() throws Exception {
        mockMvc.perform(get("/admin")).andExpect(status().is3xxRedirection());
    }
}
