package com.example.fullness.stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAccount {
    private int id;

    private int employeeId;

    private String name;

    private String password;

    private Boolean enabled;
}
