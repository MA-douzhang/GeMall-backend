package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallGoodsAttribute;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_goods_attribute(商品参数表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallGoodsAttributeService extends IService<GemallGoodsAttribute> {

    /**+
     * 根据商品id查询列表
     * @param id
     * @return
     */
    List<GemallGoodsAttribute> queryByGid(Integer id);
}
