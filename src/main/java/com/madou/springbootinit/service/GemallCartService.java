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
}
