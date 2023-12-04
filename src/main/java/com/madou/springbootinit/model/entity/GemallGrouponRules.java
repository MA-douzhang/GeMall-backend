package com.madou.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
/**
 * 团购规则表
 * @TableName gemall_groupon_rules
 */
@TableName(value ="gemall_groupon_rules")
@Data
public class GemallGrouponRules implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品表的商品ID
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片或者商品货品图片
     */
    private String picUrl;

    /**
     * 优惠金额
     */
    private BigDecimal discount;

    /**
     * 达到优惠条件的人数
     */
    private Integer discountMember;

    /**
     * 团购过期时间
     */
    private Date expireTime;

    /**
     * 团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2
     */
    private Integer status;

    /**
     * 创建时间
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
