package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallGoodsProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author MA_dou
* @description 针对表【gemall_goods_product(商品货品表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallGoodsProductService extends IService<GemallGoodsProduct> {

    /**
     * 减少商品数量
     * @param productId
     * @param number
     * @return
     */
    int reduceStock(Integer productId, Integer number);

    /**
     * 增加商品数量
     * @param productId
     * @param number
     * @return
     */
    int addStock(Integer productId, Integer number);
}
