package com.madou.springbootinit.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CouponVo {
    private Integer id;
    private Integer cid;
    private String name;
    private String desc;
    private String tag;
    private BigDecimal min;
    private BigDecimal discount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean available;
}
