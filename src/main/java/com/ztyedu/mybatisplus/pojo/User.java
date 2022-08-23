package com.ztyedu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 雪花算法生成
     */
    @TableId
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户账户 Unique 唯一识别
     */
    private Integer userId;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 1表示账户可用 0表示账户被禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;



    /**
     * 逻辑删除
     */
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}