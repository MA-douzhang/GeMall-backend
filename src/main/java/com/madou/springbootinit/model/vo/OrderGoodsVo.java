package com.madou.springbootinit.model.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderGoodsVo {
    private Integer id;
    private String goodsName;
    private String picUrl;
    private Integer goodsId;
    private Integer productId;
    private String[] specifications;
    private Integer number;
    private BigDecimal price;
    private String location;


}
