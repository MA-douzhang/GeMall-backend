package com.madou.springbootinit.model.dto.adminOrder;

import com.baomidou.mybatisplus.annotation.TableField;
import com.madou.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 订单发货
 * @TableName gemall_user
 */
@Data
public class GemallOrderShipRequest implements Serializable {

    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 发货编号
     */
    private String shipSn;
    /**
     * 发货公司
     */
    private String shipChannel;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
