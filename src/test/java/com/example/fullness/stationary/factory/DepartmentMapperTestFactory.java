package com.example.fullness.stationary.factory;

import java.util.List;
import com.example.fullness.stationary.entity.Department;

public class DepartmentMapperTestFactory {

    private List<Department> departments;

    public DepartmentMapperTestFactory() {
        this.departments = List.of(
            new Department(1, "開発部",null),
            new Department(2, "営業部", null),
            new Department(3, "総務部", null)
        );
    }

    public List<Department> createDepartmentList() {
        return this.departments;
    }

    public Department createDepartmentEntity(int id) {
        return this.departments.stream().filter((d) -> d.getId() == id).toList().get(0);
    }
}
