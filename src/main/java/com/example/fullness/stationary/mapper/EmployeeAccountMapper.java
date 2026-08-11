package com.example.fullness.stationary.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.fullness.stationary.entity.EmployeeAccount;

@Mapper
public interface EmployeeAccountMapper {
    public List<EmployeeAccount> selectAll();

    public EmployeeAccount selectByEmployeeAccountName(String employeeAccountName);

    public void insert(EmployeeAccount employeeAccount);
}
