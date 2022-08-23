package com.ztyedu.mybatisplus.mapper;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ztyedu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 赵天宇
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-08-22 09:46:35
* @Entity com.ztyedu.mybatisplus.pojo.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户账号和密码进行登录
     * @param userId
     * @param userPassword
     * @return
     */
    User selectByUserIdAndUserPassword(@Param("userId") Integer userId, @Param("userPassword") String userPassword);

    /**
     * 1.功能 根据用户账号进行查询 判断账号是否重复
     * 2.功能 查询用户信息 (余额查询)
     * @param userId
     * @return
     */
    User selectByUserId(@Param("userId") Integer userId);

    /**
     * 用户注册
     * @param user
     * @return
     */
    int insertAll(User user);

    /**
     * 根据用户账号和密码进行注销用户的操作
     * @param userId
     * @param userPassword
     * @return
     */
    int deleteByUserIdAndUserPassword(@Param("userId") Integer userId, @Param("userPassword") String userPassword);

    /**
     * 功能1. 根据用户账号进行充值功能操作
     * 功能2. 根据用户账号进行购物时的扣费操作
     * @param userId
     * @return
     */
    int updateBalanceByUserId(@Param("balance") BigDecimal balance, @Param("userId") Integer userId);

}




