package com.example.fullness.stationary.service;

import java.util.List;
import com.example.fullness.stationary.entity.Employee;

public interface EmployeeService {
    List<Employee> findWithoutAccount();

    Employee findById(int id);
}
