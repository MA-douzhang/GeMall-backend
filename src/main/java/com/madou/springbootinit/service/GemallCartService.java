package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallCart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_cart(购物车商品表)】的数据库操作Service
* @createDate 2023-12-01 23:40:25
*/
public interface GemallCartService extends IService<GemallCart> {

    /**
     * 查询当前用户的购物车信息
     * @param userId
     * @return
     */
    List<GemallCart> queryByUidAndChecked(Integer userId);

    /**
     * 根据id和用户查询购物车信息
     * @param userId
     * @param cartId
     * @return
     */
    GemallCart findById(Integer userId, Integer cartId);

    /**
     * 根据用户id清除购物车
     * @param userId
     */
    void clearGoods(Integer userId);

    /**
     * 根据用户id查询购物车信息
     * @param userId
     * @return
     */
    List<GemallCart> queryByUid(Integer userId);

    /**
     * 查询当前商品在购物车是否已经存在
     * @param goodsId
     * @param productId
     * @param userId
     * @return
     */
    GemallCart queryExist(Integer goodsId, Integer productId, Integer userId);

    /**
     * 更新购物车状态
     * @param userId
     * @param productIds
     * @param isChecked
     */
    void updateCheck(Integer userId, List<Integer> productIds, Boolean isChecked);

    /**
     * 根据用户id和商品id删除购物车信息
     * @param productIds
     * @param userId
     */
    void delete(List<Integer> productIds, Integer userId);
}
