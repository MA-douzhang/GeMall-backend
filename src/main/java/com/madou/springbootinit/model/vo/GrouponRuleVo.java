package com.madou.springbootinit.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GrouponRuleVo {
    private Integer id;
    private String name;
    private String brief;
    private String picUrl;
    private BigDecimal counterPrice;
    private BigDecimal retailPrice;
    private BigDecimal grouponPrice;
    private BigDecimal grouponDiscount;
    private Integer grouponMember;
    private LocalDateTime expireTime;

}
