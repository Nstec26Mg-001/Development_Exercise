package com.example.fullness.stationary.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.fullness.stationary.config.SecurityConfig;
import com.example.fullness.stationary.controller.form.EmployeeAccountRegisterForm;
import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.service.EmployeeAccountService;
import com.example.fullness.stationary.service.EmployeeService;

@WebMvcTest(AdminAccountController.class)
@Import(SecurityConfig.class)
public class AdminAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @MockitoBean
    private EmployeeAccountService employeeAccountService;

    @Test
    @DisplayName("アカウント登録フォーム画面表示OKテスト")
    @WithMockUser
    public void testForm() throws Exception {
        List<Employee> employees = List.of(new Employee(1, 1, "山田太郎", "ヤマダタロウ", null, null));
        when(employeeService.findWithoutAccount()).thenReturn(employees);

        mockMvc.perform(get("/admin/account/form")).andExpect(status().isOk())
                .andExpect(view().name("admin/account/form"))
                .andExpect(model().attributeExists("form"))
                .andExpect(model().attribute("employees", employees));
    }

    @Test
    @DisplayName("アカウント登録_バリデーションエラーテスト")
    @WithMockUser
    public void testFormSubmitValidationError() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("form", new EmployeeAccountRegisterForm());

        mockMvc.perform(post("/admin/account/form").session(session).with(csrf())
                .param("employeeId", "").param("accountName", "").param("password", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/account/form"))
                .andExpect(flash().attributeExists("errorMessages"));
    }

    @Test
    @DisplayName("アカウント登録_正常送信OKテスト")
    @WithMockUser
    public void testFormSubmitSuccess() throws Exception {
        Employee employee = new Employee(1, 1, "山田太郎", "ヤマダタロウ", null, null);
        when(employeeService.findById(1)).thenReturn(employee);
        when(employeeAccountService.existsByName("testuser1")).thenReturn(false);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("form", new EmployeeAccountRegisterForm());

        mockMvc.perform(
                post("/admin/account/form").session(session).with(csrf()).param("employeeId", "1")
                        .param("accountName", "testuser1").param("password", "password1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/account/confirm"));
    }

    @Test
    @DisplayName("アカウント登録_アカウント名重複エラーテスト")
    @WithMockUser
    public void testFormSubmitDuplicateAccountName() throws Exception {
        when(employeeAccountService.existsByName("testuser1")).thenReturn(true);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("form", new EmployeeAccountRegisterForm());

        mockMvc.perform(
                post("/admin/account/form").session(session).with(csrf()).param("employeeId", "1")
                        .param("accountName", "testuser1").param("password", "password1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/account/form"))
                .andExpect(flash().attributeExists("errorMessages"));
    }

    @Test
    @DisplayName("アカウント登録_未認証時リダイレクトテスト")
    public void testFormUnauthenticated() throws Exception {
        mockMvc.perform(get("/admin/account/form")).andExpect(status().is3xxRedirection());
    }
}
