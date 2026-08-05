package com.example.fullness.stationary.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.fullness.stationary.entity.Employee;

@Mapper
public interface EmployeeMapper {
    /**
     * 従業員情報全件取得
     * （部署、アカウント含む）
     * 
     * @return List<Employee>
     */
    public List<Employee> selectAllWithDepartmentAndEmployeeAccount();

    /**
     * 従業員取得（ID指定）
     * （部署、アカウント含む）
     * 
     * @param id
     * @return Employee
     */
    public Employee selectByIdWithDepartmentAndEmployeeAccount(int id);

    /**
     * 従業員取得（アカウント未登録）
     * （部署、アカウント含む）
     * 
     * @return List<Employee>
     */
    public List<Employee> selectNotHaveEmployeeAccount();
}
