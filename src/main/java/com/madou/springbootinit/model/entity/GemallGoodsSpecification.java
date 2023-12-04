package com.madou.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
/**
 * 商品规格表
 * @TableName gemall_goods_specification
 */
@TableName(value ="gemall_goods_specification")
@Data
public class GemallGoodsSpecification implements Serializable {
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
     * 商品规格名称
     */
    private String specification;

    /**
     * 商品规格值
     */
    private String value;

    /**
     * 商品规格图片
     */
    private String picUrl;

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
