package com.example.fullness.stationary.factory;

import java.util.List;

import com.example.fullness.stationary.entity.EmployeeAccount;

public class EmployeeAccountMapperTestFactory {

    private List<EmployeeAccount> employeeAccounts;

    public EmployeeAccountMapperTestFactory() {
        this.employeeAccounts = List.of(
                new EmployeeAccount(
                        1,
                        1,
                        "yamada.t",
                        "hashed_pass_yamada_123",
                        null),
                new EmployeeAccount(
                        2,
                        3,
                        "suzuki.i",
                        "hashed_pass_suzuki_789",
                        null));
    }

    public List<EmployeeAccount> createEmployeeAccountList() {
        return this.employeeAccounts;
    }

    public EmployeeAccount createEmployeeAccountEntity(int id) {
        return this.employeeAccounts.stream().filter((ea) -> ea.getId() == id).toList().get(0);
    }

    public EmployeeAccount createVoidEmployeeAccountEntity(int employeeId) {
        return new EmployeeAccount(0, employeeId, null, null, null);
    }
}
