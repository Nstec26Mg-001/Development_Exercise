package com.example.fullness.stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;

    private int departmentId;

    private String name;

    private String nameKana;

    private Department department;

    private EmployeeAccount employeeAccount;

    public boolean hasAccount() {
        return employeeAccount != null;
    }
}
