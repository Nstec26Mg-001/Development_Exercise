package com.example.fullness.stationary.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.fullness.stationary.controller.form.EmployeeAccountRegisterForm;
import com.example.fullness.stationary.entity.EmployeeAccount;

@Component
public class EmployeeAccountHelper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeAccount convertToEntity(EmployeeAccountRegisterForm form) {
        EmployeeAccount account = new EmployeeAccount();
        account.setEmployeeId(form.getEmployeeId());
        account.setName(form.getAccountName());
        account.setPassword(passwordEncoder.encode(form.getPassword()));
        return account;
    }
}
