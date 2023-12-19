package com.madou.springbootinit.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 商品类别
 */
@Data
public class CatVO {
    /**
     * 商品类别值
     */
    private Integer value;
    /**
     * 商品类别描述
     */
    private String label;
    /**
     * 商品子类别
     */
    private List<CatVO> children = null;

}
