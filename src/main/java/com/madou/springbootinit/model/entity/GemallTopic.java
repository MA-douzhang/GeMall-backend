package com.madou.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 专题表
 * @TableName gemall_topic
 */
@TableName(value ="gemall_topic")
@Data
public class GemallTopic implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 专题标题
     */
    private String title;

    /**
     * 专题子标题
     */
    private String subtitle;

    /**
     * 专题内容，富文本格式
     */
    private String content;

    /**
     * 专题相关商品最低价
     */
    private BigDecimal price;

    /**
     * 专题阅读量
     */
    private String readCount;

    /**
     * 专题图片
     */
    private String picUrl;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 专题相关商品，采用JSON数组格式
     */
    private Integer[] goods;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
