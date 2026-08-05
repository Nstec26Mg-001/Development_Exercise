package com.example.fullness.stationary.factory;

import java.util.List;
import com.example.fullness.stationary.entity.Employee;

public class EmployeeMapperTestFactory {

    private DepartmentMapperTestFactory departmentMapperTestFactory;

    private EmployeeAccountMapperTestFactory employeeAccountMapperTestFactory;

    private List<Employee> employees;

    public EmployeeMapperTestFactory() {
        this.departmentMapperTestFactory= new DepartmentMapperTestFactory();

        this.employeeAccountMapperTestFactory = new EmployeeAccountMapperTestFactory();

        this.employees = List.of(
            new Employee(
                1, 
                1, 
                "山田 太郎", 
                "ヤマダ タロウ", 
                this.departmentMapperTestFactory.createDepartmentEntity(1), 
                employeeAccountMapperTestFactory.createEmployeeAccountEntity(1)),
            new Employee(
                2, 
                1, 
                "佐藤 花子", 
                "サトウ ハナコ", 
                this.departmentMapperTestFactory.createDepartmentEntity(1), 
                employeeAccountMapperTestFactory.createVoidEmployeeAccountEntity(2)),
            new Employee(
                3, 
                2, 
                "鈴木 一郎", 
                "スズキ イチロウ", 
                this.departmentMapperTestFactory.createDepartmentEntity(2), 
                employeeAccountMapperTestFactory.createEmployeeAccountEntity(2))
        );
    }
    
    public List<Employee> createEmployeeList(){
        return this.employees;
    }

    public Employee createEmployeeEntity(int id) {
        return this.employees.stream().filter((e) -> e.getId() == id).toList().get(0);
    }

    public List<Employee> createEmployeeListWithoutEmployeeAccount() {
        return List.of(this.employees.get(1));
    }
}
