package com.madou.springbootinit.model.dto.adminGoods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.madou.springbootinit.common.PageRequest;
import com.madou.springbootinit.model.entity.GemallGoods;
import com.madou.springbootinit.model.entity.GemallGoodsAttribute;
import com.madou.springbootinit.model.entity.GemallGoodsProduct;
import com.madou.springbootinit.model.entity.GemallGoodsSpecification;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 商品添加
 *
 * @TableName gemall_user
 */
@Data
public class GemallGoodsAddRequest implements Serializable {

    /**
     * 商品信息
     */
    GemallGoods goods;
    /**
     * 商品规格
     */
    GemallGoodsSpecification[] specifications;
    /**
     * 商品参数
     */
    GemallGoodsAttribute[] attributes;
    /**
     * 商品货品
     */
    GemallGoodsProduct[] products;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
