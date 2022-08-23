package com.ztyedu.mybatisplus.service;

import com.ztyedu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 赵天宇
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-08-22 09:46:35
*/
public interface UserService extends IService<User> {
    /**
     * 与UserMapper中的insertAll对应 实现类进行业务逻辑拓展
     * @param user
     * @return
     */
    boolean insertAll(User user);
}
