package com.madou.springbootinit.model.dto.adminGoods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.madou.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品表请求
 * @TableName gemall_user
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GemallGoodsQueryRequest extends PageRequest implements Serializable {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 是否上架
     */
    private Boolean isOnSale;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
