package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallAd;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_ad(广告表)】的数据库操作Service
* @createDate 2023-12-01 23:40:25
*/
public interface GemallAdService extends IService<GemallAd> {

    /**+
     * 广告查询
     * @return
     */
    List<GemallAd> queryIndex();

    /**
     * 查询广告
     * @param name
     * @param content
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    List<GemallAd> querySelective(String name, String content, Integer page, Integer limit, String sort, String order);
}
