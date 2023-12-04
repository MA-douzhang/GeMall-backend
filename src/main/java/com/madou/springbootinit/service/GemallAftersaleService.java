package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallAftersale;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_aftersale(售后表)】的数据库操作Service
* @createDate 2023-12-01 23:40:25
*/
public interface GemallAftersaleService extends IService<GemallAftersale> {


    /**
     * 根据订单id和用户id删除收后信息
     * @param userId
     * @param orderId
     */
    void deleteByOrderId(Integer userId, Integer orderId);
}
