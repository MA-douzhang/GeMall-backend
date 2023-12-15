package com.madou.springbootinit.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户表VO
 * @TableName gemall_user
 */
@Data
public class GemallUserVO implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 性别：0 未知， 1男， 1 女
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 最近一次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最近一次登录IP地址
     */
    private String lastLoginIp;

    /**
     * 0 普通用户，1 VIP用户，2 高级VIP用户
     */
    private Integer userLevel;

    /**
     * 用户昵称或网络名称
     */
    private String nickname;

    /**
     * 用户手机号码
     */
    private String mobile;

    /**
     * 用户头像图片
     */
    private String avatar;


    /**
     * 0 可用, 1 禁用, 2 注销
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
