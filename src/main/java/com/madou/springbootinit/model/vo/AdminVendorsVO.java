package com.madou.springbootinit.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理-快递VO
 * @TableName gemall_admin
 */
@Data
public class AdminVendorsVO implements Serializable {
    /**
     * 快递code
     */
    private String code;
    /**
     * 快递名称
     */
    private String name;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
