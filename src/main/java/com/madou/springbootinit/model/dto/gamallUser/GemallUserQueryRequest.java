package com.madou.springbootinit.model.dto.gamallUser;

import com.baomidou.mybatisplus.annotation.TableField;
import com.madou.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表请求
 * @TableName gemall_user
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GemallUserQueryRequest extends PageRequest implements Serializable {
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
     * 最近一次登录时间
     */
    private LocalDateTime lastLoginTime;

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
     * 0 可用, 1 禁用, 2 注销
     */
    private Integer status;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
