package com.ztyedu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

/**
 * @TableName t_good
 */
@TableName(value = "t_good")
@Data
public class Good implements Serializable {
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
     * 商品金额
     */
    private BigDecimal goodPrice;

    /**
     * 商品现存数量
     */
    private Integer goodNumber;

    /**
     * 购买者账号 根据账号查询账户的支付记录
     */
    private Integer userId;

    /**
     * 1表示可出售 0表示禁止出售
     */
    private Integer status;

    /**
     * 添加货物的时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新货物的时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 0表示未删除 1表示已删除
     */
   @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return goodName + " \t\t￥" + goodPrice + "\t\t" + goodNumber + "\t\t\t\t" + userId + "\t\t\t\t\t" + status + "\t\t\t\t" + createTime + "\t\t\t" + updateTime + "\t\t\t" + isDeleted;
    }
}