package com.ztyedu.mybatisplus.service;

import com.ztyedu.mybatisplus.pojo.GoodRecords;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ztyedu.mybatisplus.pojo.User;

/**
* @author 赵天宇
* @description 针对表【t_good_records】的数据库操作Service
* @createDate 2022-08-22 20:11:30
*/
public interface GoodRecordsService extends IService<GoodRecords> {
    boolean insertGoodRecords(String commodityName, Integer commodityNumber, User user);
}
