package com.example.fullness.stationary.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.factory.EmployeeMapperTestFactory;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "/sql/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
@Import(EmployeeMapperTestFactory.class)
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    private EmployeeMapperTestFactory employeeMapperTestFactory;

    public EmployeeMapperTest() {
        this.employeeMapperTestFactory = new EmployeeMapperTestFactory();
    }

    @Test
    @DisplayName("全件検索OKテスト")
    public void testSelectAllWithDepartmentAndEmployeeAccount() {
        List<Employee> expected = this.employeeMapperTestFactory.createEmployeeList();

        List<Employee> actual = this.employeeMapper.selectAllWithDepartmentAndEmployeeAccount();

        assertInstanceOf(List.class, actual);
        expected.forEach(employee -> assertInstanceOf(Employee.class, employee));
        assertEquals(3, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1件検索OKテスト")
    public void testSelectByIdWithDepartmentAndEmployeeAccount() {
        Employee expected = this.employeeMapperTestFactory.createEmployeeEntity(2);

        Employee actual = this.employeeMapper.selectByIdWithDepartmentAndEmployeeAccount(2);

        assertInstanceOf(Employee.class, actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("担当者アカウントなし全件取得OKテスト")
    public void testSelectNotHaveEmployeeAccount() {
        List<Employee> expected = this.employeeMapperTestFactory.createEmployeeListWithoutEmployeeAccount();

        List<Employee> actual = this.employeeMapper.selectNotHaveEmployeeAccount();

        assertInstanceOf(List.class, actual);
        expected.forEach(employee -> assertInstanceOf(Employee.class, employee));
        assertEquals(1, actual.size());
        assertEquals(expected, actual);
    }
}
