package com.madou.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 收货地址表
 * @TableName gemall_address
 */
@TableName(value ="gemall_address")
@Data
public class GemallAddress implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 收货人名称
     */
    private String name;

    /**
     * 用户表的用户ID
     */
    private Integer userId;

    /**
     * 行政区域表的省ID
     */
    private String province;

    /**
     * 行政区域表的市ID
     */
    private String city;

    /**
     * 行政区域表的区县ID
     */
    private String county;

    /**
     * 详细收货地址
     */
    private String addressDetail;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 是否默认地址
     */
    private Integer isDefault;

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
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
