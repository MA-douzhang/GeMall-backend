package com.madou.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.madou.springbootinit.mybatis.JsonIntegerArrayTypeHandler;
import com.madou.springbootinit.mybatis.JsonStringArrayTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 管理员表
 * @TableName gemall_admin
 */
@TableName(value ="gemall_admin",autoResultMap = true)
@Data
public class GemallAdmin implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员名称
     */
    private String username;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 最近一次登录IP地址
     */
    private String lastLoginIp;

    /**
     * 最近一次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 头像图片
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 角色列表
     */
    @TableField(value = "role_ids",typeHandler = JsonIntegerArrayTypeHandler.class)
    private Integer[] roleIds;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
