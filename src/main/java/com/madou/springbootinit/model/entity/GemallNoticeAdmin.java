package com.madou.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;
/**
 * 通知管理员表
 * @TableName gemall_notice_admin
 */
@TableName(value ="gemall_notice_admin")
@Data
public class GemallNoticeAdmin implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 通知ID
     */
    private Integer noticeId;

    /**
     * 通知标题
     */
    private String noticeTitle;

    /**
     * 接收通知的管理员ID
     */
    private Integer adminId;

    /**
     * 阅读时间，如果是NULL则是未读状态
     */
    private Date readTime;

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
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
