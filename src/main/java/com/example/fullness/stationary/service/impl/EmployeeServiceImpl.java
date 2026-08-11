package com.example.fullness.stationary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.mapper.EmployeeMapper;
import com.example.fullness.stationary.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Employee findById(int id) {
        return employeeMapper.selectByIdWithDepartmentAndEmployeeAccount(id);
    }

    @Override
    public List<Employee> findWithoutAccount() {
        return employeeMapper.selectNotHaveEmployeeAccount();
    }

}
