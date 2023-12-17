package com.madou.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.common.BaseResponse;
import com.madou.springbootinit.common.DeleteRequest;
import com.madou.springbootinit.model.dto.adminOrder.GemallOrderQueryRequest;
import com.madou.springbootinit.model.dto.adminOrder.GemallOrderShipRequest;
import com.madou.springbootinit.model.entity.GemallOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.madou.springbootinit.model.vo.GemallOrderVO;

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

    /**
     * 管理-查询订单列表
     * @param queryRequest
     * @return
     */
    Page<GemallOrderVO> getList(GemallOrderQueryRequest queryRequest);


    /**
     * 管理-发货
     * @param gemallOrderShipRequest
     * @return
     */
    Boolean ship(GemallOrderShipRequest gemallOrderShipRequest);

    /**
     * 管理-删除订单
     * @param deleteRequest
     * @return
     */
    Boolean delete(DeleteRequest deleteRequest);
}
