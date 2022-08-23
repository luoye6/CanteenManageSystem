package com.ztyedu.mybatisplus.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ztyedu.mybatisplus.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 赵天宇
* @description 针对表【t_employee】的数据库操作Mapper
* @createDate 2022-08-22 19:19:23
* @Entity com.ztyedu.mybatisplus.pojo.Employee
*/
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 小卖部管理员登录校验
     * @param empId
     * @param empPassword
     * @return
     */
    Employee selectByEmpIdAndEmpPassword(@Param("empId") Integer empId, @Param("empPassword") String empPassword);
}




