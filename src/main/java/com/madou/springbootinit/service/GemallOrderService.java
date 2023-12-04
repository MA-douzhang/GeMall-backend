package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_order(订单表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallOrderService extends IService<GemallOrder> {

    /**
     * 查询当前用户的订单信息
     * @param userId
     * @return
     */
    Object orderInfo(Integer userId);

    /**
     * 查询分页列表订单信息
     * @param userId
     * @param orderStatus
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    List<GemallOrder> queryByOrderStatus(Integer userId, List<Integer> orderStatus, Integer page, Integer limit, String sort, String order);

    /**
     * 根据id和用户id查询订单信息
     * @param userId
     * @param orderId
     * @return
     */
    GemallOrder findById(Integer userId, Integer orderId);

    /**
     * 生成订单id
     * @param userId
     * @return
     */
    String generateOrderSn(Integer userId);

    /**
     * 更新判断时间
     * @param order
     * @return
     */
    int updateWithOptimisticLocker(GemallOrder order);
}
