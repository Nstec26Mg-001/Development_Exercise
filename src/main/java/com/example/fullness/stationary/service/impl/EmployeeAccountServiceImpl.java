package com.example.fullness.stationary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.controller.form.EmployeeAccountRegisterForm;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.helper.EmployeeAccountHelper;
import com.example.fullness.stationary.mapper.EmployeeAccountMapper;
import com.example.fullness.stationary.service.EmployeeAccountService;

@Service
@Transactional
public class EmployeeAccountServiceImpl implements EmployeeAccountService {

    @Autowired
    private EmployeeAccountMapper employeeAccountMapper;

    @Autowired
    private EmployeeAccountHelper employeeAccountHelper;

    @Override
    public boolean existsByName(String accountName) {
        return employeeAccountMapper.selectByEmployeeAccountName(accountName) != null;
    }

    @Override
    public void register(EmployeeAccountRegisterForm form) {
        EmployeeAccount account = employeeAccountHelper.convertToEntity(form);
        employeeAccountMapper.insert(account);
    }
}
