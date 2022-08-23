package com.ztyedu.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztyedu.mybatisplus.common.CustomException;
import com.ztyedu.mybatisplus.mapper.GoodMapper;
import com.ztyedu.mybatisplus.mapper.UserMapper;
import com.ztyedu.mybatisplus.pojo.Good;
import com.ztyedu.mybatisplus.pojo.GoodRecords;
import com.ztyedu.mybatisplus.pojo.User;
import com.ztyedu.mybatisplus.service.GoodRecordsService;
import com.ztyedu.mybatisplus.mapper.GoodRecordsMapper;
import com.ztyedu.mybatisplus.utils.SpringBootUtil;
import com.ztyedu.mybatisplus.utils.Utility;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author 赵天宇
 * @description 针对表【t_good_records】的数据库操作Service实现
 * @createDate 2022-08-22 20:11:30
 */
@Service
public class GoodRecordsServiceImpl extends ServiceImpl<GoodRecordsMapper, GoodRecords> implements GoodRecordsService {


    @Override
    public boolean insertGoodRecords(String commodityName, Integer commodityNumber, User user) {
        // 当用户购买货物时 需要进行逻辑判断

        // 1.用户要购买的商品昵称是否存在以及数量是否足够以及在Good表中进行查询
        GoodMapper goodMapper = SpringBootUtil.getBean(GoodMapper.class);
        Good good = goodMapper.selectByGoodName(commodityName);
        if (good == null) {
            throw new CustomException("您要购买的商品不存在");
        }
        if (good.getGoodNumber() < commodityNumber) {
            throw new CustomException("您要购买的商品的数量不足");
        }
        System.out.print("请问您是否要进行支付(Y/N): ");
        String key = Utility.readString(1);
        if (key.equals("Y")) {
            // 2.用户当前余额是否足够支付
            String number = commodityNumber.toString();
            BigDecimal newNumber = new BigDecimal(number);
            BigDecimal sum = good.getGoodPrice().multiply(newNumber);
            UserMapper userMapper = SpringBootUtil.getBean(UserMapper.class);
            User newUser = userMapper.selectByUserId(user.getUserId());
            if (sum.compareTo(newUser.getBalance()) > 0) {
                throw new CustomException("您当前的余额不足");
            }
            BigDecimal surplus = newUser.getBalance().subtract(sum);
            // 3.如果购买成功,需要减少Good表中的商品数量，然后再添加购物记录到GoodRecords表
            int result = goodMapper.updateGoodNumberAndUserIdByGoodName(good.getGoodNumber() - commodityNumber, user.getUserId(),commodityName);
            if (result != -1) {
                GoodRecordsMapper goodRecordsMapper = SpringBootUtil.getBean(GoodRecordsMapper.class);
                GoodRecords goodRecords = new GoodRecords();
                goodRecords.setGoodName(commodityName);
                goodRecords.setGoodPrice(good.getGoodPrice());
                goodRecords.setGoodNumber(commodityNumber);
                goodRecords.setUserId(user.getUserId());
                goodRecords.setUserName(user.getUserName());
                int result2 = goodRecordsMapper.insertAll(goodRecords);
                if (result2 != -1) {
                    int result3 = userMapper.updateBalanceByUserId(surplus, user.getUserId());
                    if(result3 != -1){
                        return true;
                    }
                }
            }

        } else {
            return false;
        }
        return false;
    }
}




