package com.madou.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
/**
 * 优惠券用户使用表
 * @TableName gemall_coupon_user
 */
@TableName(value ="gemall_coupon_user")
@Data
public class GemallCouponUser implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 优惠券ID
     */
    private Integer couponId;

    /**
     * 使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；
     */
    private Integer status;

    /**
     * 使用时间
     */
    private LocalDateTime usedTime;

    /**
     * 有效期开始时间
     */
    private LocalDateTime startTime;

    /**
     * 有效期截至时间
     */
    private LocalDateTime endTime;

    /**
     * 订单ID
     */
    private Integer orderId;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
