package com.ztyedu.mybatisplus.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ztyedu.mybatisplus.pojo.GoodRecords;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 赵天宇
* @description 针对表【t_good_records】的数据库操作Mapper
* @createDate 2022-08-22 20:11:30
* @Entity com.ztyedu.mybatisplus.pojo.GoodRecords
*/
@Repository
public interface GoodRecordsMapper extends BaseMapper<GoodRecords> {
    /**
     * 用户根据账号来查询当前用户的购买记录
     * @param userId
     * @return
     */
    List<GoodRecords> selectByUserId(@Param("userId") Integer userId);

    /**
     * 添加用户购买记录
     * @param goodRecords
     * @return
     */
    int insertAll(GoodRecords goodRecords);

    /**
     * 当小卖部管理员去查询记录时，不用考虑用户账号个人情况，统一查询
     * @return
     */
    List<GoodRecords> selectAll();
}




