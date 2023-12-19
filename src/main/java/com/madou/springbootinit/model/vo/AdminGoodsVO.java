package com.madou.springbootinit.model.vo;

import com.madou.springbootinit.model.entity.GemallGoods;
import com.madou.springbootinit.model.entity.GemallGoodsAttribute;
import com.madou.springbootinit.model.entity.GemallGoodsProduct;
import com.madou.springbootinit.model.entity.GemallGoodsSpecification;
import lombok.Data;

import java.util.List;

/**
 * @author MA_dou
 * @version 1.0
 * @project ge-team-buy-backend
 * @description 管理商品VO
 * @date 2023/12/19 22:26:26
 */
@Data
public class AdminGoodsVO {
    /**
     * 商品信息
     */
    private GemallGoods goods;
    /**
     * 商品规格
     */
    private List<GemallGoodsSpecification> specifications;
    /**
     * 商品参数
     */
    private List<GemallGoodsAttribute> attributes;
    /**
     * 商品货品
     */
    private List<GemallGoodsProduct> products;

    /**
     * 商品类别id
     */
    private Integer[] categoryIds;
}
