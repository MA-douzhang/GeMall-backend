package com.madou.springbootinit.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.madou.springbootinit.mybatis.JsonStringArrayTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品基本信息表
 * @TableName gemall_goods
 */
@Data
public class GemallGoodsVO implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 是否上架
     */
    private Boolean isOnSale;

    /**
     * 商品页面商品图片
     */
    private String picUrl;

    /**
     * 是否新品首发，如果设置则可以在新品首发页面展示
     */
    private Boolean isNew;

    /**
     * 是否人气推荐，如果设置则可以在人气推荐页面展示
     */
    private Boolean isHot;

    /**
     * 专柜价格
     */
    private BigDecimal counterPrice;

    /**
     * 零售价格
     */
    private BigDecimal retailPrice;

    /**
     * 商品详细介绍，是富文本格式
     */
    private String detail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
