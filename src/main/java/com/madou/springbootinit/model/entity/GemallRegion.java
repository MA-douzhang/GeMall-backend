package com.madou.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
/**
 * 行政区域表
 * @TableName gemall_region
 */
@TableName(value ="gemall_region")
@Data
public class GemallRegion implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0
     */
    private Integer pid;

    /**
     * 行政区域名称
     */
    private String name;

    /**
     * 行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县
     */
    private Integer type;

    /**
     * 行政区域编码
     */
    private Integer code;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
