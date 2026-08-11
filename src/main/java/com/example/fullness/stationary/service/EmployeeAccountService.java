package com.example.fullness.stationary.service;

import com.example.fullness.stationary.entity.EmployeeAccount;

public interface EmployeeAccountService {

    boolean existsByName(String accountName);

    void register(EmployeeAccount employeeAccount);
}
