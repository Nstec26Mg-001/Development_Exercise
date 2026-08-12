package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.mapper.EmployeeMapper;
import com.example.fullness.stationary.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    @DisplayName("ID検索OKテスト")
    public void testFindById() {
        Employee expected = new Employee(1, 1, "山田太郎", "ヤマダタロウ", null, null);
        when(employeeMapper.selectByIdWithDepartmentAndEmployeeAccount(1)).thenReturn(expected);

        Employee actual = employeeService.findById(1);

        assertInstanceOf(Employee.class, actual);
        assertEquals(expected, actual);
        verify(employeeMapper, times(1)).selectByIdWithDepartmentAndEmployeeAccount(1);
    }

    @Test
    @DisplayName("アカウント未登録社員検索OKテスト")
    public void testFindWithoutAccount() {
        List<Employee> expected = List.of(
                new Employee(1, 1, "山田太郎", "ヤマダタロウ", null, null),
                new Employee(2, 1, "鈴木花子", "スズキハナコ", null, null));
        when(employeeMapper.selectNotHaveEmployeeAccount()).thenReturn(expected);

        List<Employee> actual = employeeService.findWithoutAccount();

        assertInstanceOf(List.class, actual);
        assertEquals(2, actual.size());
        assertEquals(expected, actual);
        verify(employeeMapper, times(1)).selectNotHaveEmployeeAccount();
    }
}
