package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallBrand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_brand(品牌商表)】的数据库操作Service
* @createDate 2023-12-01 23:40:25
*/
public interface GemallBrandService extends IService<GemallBrand> {

    /**
     * 返回分页列表
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    List<GemallBrand> queryList(Integer page, Integer limit, String sort, String order);

    /**
     * 返回分页列表
     * @param page
     * @param limit
     * @return
     */
    List<GemallBrand> query(int page, Integer limit);
}
