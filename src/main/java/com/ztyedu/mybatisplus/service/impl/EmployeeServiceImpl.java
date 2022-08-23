package com.ztyedu.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztyedu.mybatisplus.pojo.Employee;
import com.ztyedu.mybatisplus.service.EmployeeService;
import com.ztyedu.mybatisplus.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author 赵天宇
* @description 针对表【t_employee】的数据库操作Service实现
* @createDate 2022-08-22 19:19:23
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{

}




