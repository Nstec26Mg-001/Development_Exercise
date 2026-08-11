package com.example.fullness.stationary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.mapper.EmployeeAccountMapper;
import com.example.fullness.stationary.service.EmployeeAccountService;

@Service
@Transactional
public class EmployeeAccountServiceImpl implements EmployeeAccountService {

    @Autowired
    private EmployeeAccountMapper employeeAccountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean existsByName(String accountName) {
        return employeeAccountMapper.selectByEmployeeAccountName(accountName) != null;
    }

    @Override
    public void register(EmployeeAccount employeeAccount) {
        employeeAccount.setPassword(passwordEncoder.encode(employeeAccount.getPassword()));
        employeeAccount.setEnabled(true);
        employeeAccountMapper.insert(employeeAccount);
    }
}
