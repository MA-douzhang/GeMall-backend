package com.madou.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 售后表
 * @TableName gemall_aftersale
 */
@TableName(value ="gemall_aftersale")
@Data
public class GemallAftersale implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 售后编号
     */
    private String aftersaleSn;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款
     */
    private Integer type;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 退款金额
     */
    private BigDecimal amount;

    /**
     * 退款凭证图片链接数组
     */
    private String pictures;

    /**
     * 退款说明
     */
    private String comment;

    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     */
    private Integer status;

    /**
     * 管理员操作时间
     */
    private Date handleTime;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
