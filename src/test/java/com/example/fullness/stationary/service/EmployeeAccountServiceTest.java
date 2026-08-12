package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fullness.stationary.controller.form.EmployeeAccountRegisterForm;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.helper.EmployeeAccountHelper;
import com.example.fullness.stationary.mapper.EmployeeAccountMapper;
import com.example.fullness.stationary.service.impl.EmployeeAccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeAccountServiceTest {

    @Mock
    private EmployeeAccountMapper employeeAccountMapper;

    @Mock
    private EmployeeAccountHelper employeeAccountHelper;

    @InjectMocks
    private EmployeeAccountServiceImpl employeeAccountService;

    @Test
    @DisplayName("アカウント名存在チェック_存在するOKテスト")
    public void testExistsByNameTrue() {
        EmployeeAccount account = new EmployeeAccount(1, 1, "testuser", "password");
        when(employeeAccountMapper.selectByEmployeeAccountName("testuser")).thenReturn(account);

        boolean actual = employeeAccountService.existsByName("testuser");

        assertTrue(actual);
        verify(employeeAccountMapper, times(1)).selectByEmployeeAccountName("testuser");
    }

    @Test
    @DisplayName("アカウント名存在チェック_存在しないOKテスト")
    public void testExistsByNameFalse() {
        when(employeeAccountMapper.selectByEmployeeAccountName("newuser")).thenReturn(null);

        boolean actual = employeeAccountService.existsByName("newuser");

        assertFalse(actual);
        verify(employeeAccountMapper, times(1)).selectByEmployeeAccountName("newuser");
    }

    @Test
    @DisplayName("登録OKテスト")
    public void testRegister() {
        EmployeeAccountRegisterForm form =
                new EmployeeAccountRegisterForm(1, "山田太郎", "testuser", "password");
        EmployeeAccount account = new EmployeeAccount(0, 1, "testuser", "encodedPassword");
        when(employeeAccountHelper.convertToEntity(form)).thenReturn(account);

        employeeAccountService.register(form);

        verify(employeeAccountHelper, times(1)).convertToEntity(form);
        verify(employeeAccountMapper, times(1)).insert(account);
    }
}
