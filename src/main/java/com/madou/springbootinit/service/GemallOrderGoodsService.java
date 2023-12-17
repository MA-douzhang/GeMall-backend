package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallOrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_order_goods(订单商品表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallOrderGoodsService extends IService<GemallOrderGoods> {

    /**
     * 根据订单id查询订单商品信息
     * @param id
     * @return
     */
    List<GemallOrderGoods> queryByOid(Integer id);

    /**
     * 查询订单下物件数量
     * @param orderId
     * @return
     */
    Integer getComments(Integer orderId);

    /**
     * 根据订单id删除
     * @param orderId
     * @return
     */
    Boolean deleteByOrderId(Integer orderId);
}
