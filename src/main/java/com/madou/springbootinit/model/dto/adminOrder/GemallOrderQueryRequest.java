package com.madou.springbootinit.model.dto.adminOrder;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.madou.springbootinit.common.PageRequest;
import com.madou.springbootinit.mybatis.JsonStringArrayTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户表请求
 * @TableName gemall_user
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GemallOrderQueryRequest extends PageRequest implements Serializable {

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 订单状态
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
     * 收货具体地址
     */
    private String address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
