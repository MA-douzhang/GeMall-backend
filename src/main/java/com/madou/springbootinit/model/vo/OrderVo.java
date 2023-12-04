package com.madou.springbootinit.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class OrderVo {
    private Integer id;
    private String orderSn;
    private Integer orderStatus;
    private BigDecimal actualPrice;
    private BigDecimal integralPrice;
    private BigDecimal freightPrice;
    private BigDecimal orderPrice;
    private LocalDateTime addTime;
    private Integer userId;
    private String userName;
    private String userAvatar;
    private String consignee;
    private String address;
    private String mobile;
    private String shipChannel;
    private String shipSn;
    private String message;
    private LocalDateTime payTime;
    private List<OrderGoodsVo> goodsVoList;


}
