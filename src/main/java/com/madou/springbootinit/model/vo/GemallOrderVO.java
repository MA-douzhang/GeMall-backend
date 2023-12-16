package com.madou.springbootinit.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 订单id
 * @TableName gemall_order
 */
@Data
public class GemallOrderVO implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 订单状态
     * 当101用户未付款时，此时用户可以进行的操作是取消订单，或者付款操作
     * 当201支付完成而商家未发货时，此时用户可以取消订单并申请退款
     * 当301商家已发货时，此时用户可以有确认收货的操作
     * 当401用户确认收货以后，此时用户可以进行的操作是删除订单，评价商品，申请售后，或者再次购买
     * 当402系统自动确认收货以后，此时用户可以删除订单，评价商品，申请售后，或者再次购买
     */

    private Integer orderStatus;

    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     */
    private Integer aftersaleStatus;

    /**
     * 收货人名称
     */
    private String consignee;

    /**
     * 收货人手机号
     */
    private String mobile;

    /**
     * 收货具体地址
     */
    private String address;

    /**
     * 用户订单留言
     */
    private String message;

    /**
     * 商品总费用
     */
    private BigDecimal goodsPrice;

    /**
     * 订单费用， = goods_price + freight_price - coupon_price
     */
    private BigDecimal orderPrice;

    /**
     * 实付费用， = order_price - integral_price
     */
    private BigDecimal actualPrice;

    /**
     * 发货开始时间
     */
    private Date shipTime;

    /**
     * 用户确认收货时间
     */
    private LocalDateTime confirmTime;

    /**
     * 订单关闭时间
     */
    private LocalDateTime endTime;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
