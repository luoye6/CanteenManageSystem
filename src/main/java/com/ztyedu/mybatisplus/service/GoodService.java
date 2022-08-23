package com.ztyedu.mybatisplus.service;

import com.ztyedu.mybatisplus.pojo.Good;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 赵天宇
* @description 针对表【t_good】的数据库操作Service
* @createDate 2022-08-22 16:11:52
*/
public interface GoodService extends IService<Good> {
    public boolean insertGood(Good good, Integer empId);
}
