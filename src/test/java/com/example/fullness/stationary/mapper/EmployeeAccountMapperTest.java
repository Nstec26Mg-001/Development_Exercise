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
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.factory.EmployeeAccountMapperTestFactory;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "/sql/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
@Import(EmployeeAccountMapperTestFactory.class)
public class EmployeeAccountMapperTest {

    @Autowired
    private EmployeeAccountMapper employeeAccountMapper;

    private EmployeeAccountMapperTestFactory employeeAccountMapperTestFactory;

    public EmployeeAccountMapperTest() {
        this.employeeAccountMapperTestFactory = new EmployeeAccountMapperTestFactory();
    }

    @Test
    @DisplayName("全件検索OKテスト")
    public void testSelectAll() {
        List<EmployeeAccount> expected = this.employeeAccountMapperTestFactory.createEmployeeAccountList();

        List<EmployeeAccount> actual = this.employeeAccountMapper.selectAll();

        assertInstanceOf(List.class, actual);
        expected.forEach(employee -> assertInstanceOf(EmployeeAccount.class, employee));
        assertEquals(2, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("アカウント検索OKテスト")
    public void testSelectByEmployeeAccountName() {
        EmployeeAccount expected = this.employeeAccountMapperTestFactory.createEmployeeAccountEntity(1);

        EmployeeAccount actual = this.employeeAccountMapper.selectByEmployeeAccountName("yamada.t");

        assertInstanceOf(EmployeeAccount.class, actual);
        assertEquals(expected, actual);
    }
    
}
