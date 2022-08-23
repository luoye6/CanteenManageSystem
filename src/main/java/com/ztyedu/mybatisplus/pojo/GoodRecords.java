package com.ztyedu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_good_records
 */
@TableName(value ="t_good_records")
@Data
public class GoodRecords implements Serializable {
    /**
     * 雪花算法生成
     */
    @TableId
    private Long id;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品价格
     */
    private BigDecimal goodPrice;

    /**
     * 商品购买数量
     */
    private Integer goodNumber;

    /**
     * 购买者账号
     */
    private Integer userId;

    /**
     * 购买者昵称
     */
    private String userName;

    /**
     * 购买时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return goodName+" \t\t￥"+goodPrice+"\t\t"+goodNumber+"\t\t\t\t"+userId+"\t\t\t\t\t"+userName+"\t\t\t\t"+createTime+"\t\t\t"+updateTime;
    }
}