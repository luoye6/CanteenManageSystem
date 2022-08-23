package com.ztyedu.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztyedu.mybatisplus.pojo.Good;
import com.ztyedu.mybatisplus.service.GoodService;
import com.ztyedu.mybatisplus.mapper.GoodMapper;
import com.ztyedu.mybatisplus.utils.SpringBootUtil;
import org.springframework.stereotype.Service;

/**
* @author 赵天宇
* @description 针对表【t_good】的数据库操作Service实现
* @createDate 2022-08-22 16:11:52
*/
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService{

    @Override
    public boolean insertGood(Good good, Integer empId) {
        //调用GoodMapper中的方法
        GoodMapper goodMapper = SpringBootUtil.getBean(GoodMapper.class);
        //判断要添加的货物是否已经存在
        String goodName = good.getGoodName();
        Good selectByGoodName = goodMapper.selectByGoodName(goodName);
        if(selectByGoodName != null){
            //商品名称已经存在，只需要增加数量，那么不再是插入，而是只需要更新good_number即可
            int result = goodMapper.updateGoodNumberAndUserIdByGoodName(good.getGoodNumber(), empId, goodName);
            if(result != -1){
                return true;
            }else{
                return false;
            }
        }
        good.setUserId(empId);
        int result2 = goodMapper.insertAll(good);
        if( result2 != -1){
            return true;
        }
        return false;
    }
}




