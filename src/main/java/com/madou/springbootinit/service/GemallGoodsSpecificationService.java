package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallGoodsSpecification;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author MA_dou
* @description 针对表【gemall_goods_specification(商品规格表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallGoodsSpecificationService extends IService<GemallGoodsSpecification> {

    /**
     * 获取商品规格
     * @param id
     * @return
     */
    Object getSpecificationVoList(Integer id);
}
