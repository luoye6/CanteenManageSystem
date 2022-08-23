package com.ztyedu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_employee
 */
@TableName(value ="t_employee")
@Data
public class Employee implements Serializable {
    /**
     * 雪花算法生成
     */
    @TableId
    private Long id;

    /**
     * 员工昵称
     */
    private String empName;

    /**
     * 员工密码
     */
    private String empPassword;

    /**
     * Unique 员工id 唯一标识
     */
    private Integer empId;

    /**
     * 1表示员工可用 0表示员工被禁用
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
     * 逻辑删除 0表示未删除 1表示已删除
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}