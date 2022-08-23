package com.ztyedu.mybatisplus.mapper;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.ztyedu.mybatisplus.pojo.Good;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 赵天宇
* @description 针对表【t_good】的数据库操作Mapper
* @createDate 2022-08-22 16:11:52
* @Entity com.ztyedu.mybatisplus.pojo.Good
*/
@Repository
public interface GoodMapper extends BaseMapper<Good> {
    /**
     * 显示当前所有货物 将status为0 或者 is_deleted为1的排除
     * @return
     */
    List<Good> selectAll();

    /**
     * 根据用户输入的商品名称去查询相对应的商品
     * @param goodName
     * @return
     */
    Good selectByGoodName(@Param("goodName") String goodName);

    /**
     * 更新商品数量和最近一次购买人的账号 通过商品昵称进行查询记录
     * @param goodNumber
     * @param userId
     * @param goodName
     * @return
     */
    int updateGoodNumberAndUserIdByGoodName(@Param("goodNumber") Integer goodNumber, @Param("userId") Integer userId, @Param("goodName") String goodName);

    /**
     * 管理员进行添加商品
     * @param good
     * @return
     */
    int insertAll(Good good);

    /**
     * 用逻辑删除
     * 将isDeleted从0改到1 表示删除
     * @param isDeleted
     * @param goodName
     * @return
     */
    int updateIsDeletedByGoodName(@Param("isDeleted") Integer isDeleted, @Param("goodName") String goodName);

    /**
     * 修改商品价格通过商品名称进行查询
     * @param goodPrice
     * @param goodName
     * @return
     */
    int updateGoodPriceByGoodName(@Param("goodPrice") BigDecimal goodPrice, @Param("goodName") String goodName);
}




