package com.example.fullness.stationary.service;

import com.example.fullness.stationary.controller.form.EmployeeAccountRegisterForm;

public interface EmployeeAccountService {

    boolean existsByName(String accountName);

    void register(EmployeeAccountRegisterForm form);
}
