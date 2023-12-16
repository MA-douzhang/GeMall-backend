package com.madou.springbootinit.model.dto.adminAddress;

import com.baomidou.mybatisplus.annotation.TableField;
import com.madou.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表请求
 * @TableName gemall_user
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GemallAddressQueryRequest extends PageRequest implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 收货人名称
     */
    private String name;

    /**
     * 用户表的用户名称
     */
    private String username;

    /**
     * 行政区域表的省
     */
    private String province;

    /**
     * 行政区域表的市
     */
    private String city;

    /**
     * 行政区域表的区县
     */
    private String county;

    /**
     * 详细收货地址
     */
    private String addressDetail;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 是否默认地址
     */
    private Boolean isDefault;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
