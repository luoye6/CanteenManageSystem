package com.ztyedu.mybatisplus;

import com.ztyedu.mybatisplus.mapper.EmployeeMapper;
import com.ztyedu.mybatisplus.mapper.GoodMapper;
import com.ztyedu.mybatisplus.mapper.GoodRecordsMapper;
import com.ztyedu.mybatisplus.mapper.UserMapper;
import com.ztyedu.mybatisplus.pojo.Employee;
import com.ztyedu.mybatisplus.pojo.Good;
import com.ztyedu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private GoodRecordsMapper goodRecordsMapper;
    @Test
    public void test01(){
        LocalDateTime now = LocalDateTime.now();

    }
    @Test
    public void test02(){
        String password = "123456";
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        int result = userMapper.deleteByUserIdAndUserPassword(999, password);
        System.out.println("受影响的行数为: " + result);
    }
    @Test
    public void test03(){
        int result = userMapper.deleteById(230L);
        System.out.println("受影响的行数为: " + result);
    }
    @Test
    public void test04(){
        List<Good> goods = goodMapper.selectAll();
        for (Good good : goods) {
            System.out.println(good);
        }
    }
    @Test
    public void test05(){
        Integer empId = 688;
        String password = "123456";
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        Employee employee = employeeMapper.selectByEmpIdAndEmpPassword(empId, password);
        System.out.println(employee);
    }
    @Test
    public void test06(){
        Good good = goodMapper.selectByGoodName("奥利奥");
        System.out.println(good);
    }
}
