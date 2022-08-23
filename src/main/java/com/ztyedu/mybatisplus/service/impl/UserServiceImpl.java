package com.ztyedu.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztyedu.mybatisplus.pojo.User;
import com.ztyedu.mybatisplus.service.UserService;
import com.ztyedu.mybatisplus.mapper.UserMapper;
import com.ztyedu.mybatisplus.utils.SpringBootUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 赵天宇
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2022-08-22 09:46:35
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean insertAll(User user) {
        UserMapper userMapper = SpringBootUtil.getBean(UserMapper.class);
        user.setStatus(1);//初始化用户状态为正常
        user.setIsDeleted(0);//初始化用户状态 0表示未被禁用 1表示被禁用
        int result = userMapper.insertAll(user);
        if(result != -1){
            return true;
        }
        return false;
    }
}




