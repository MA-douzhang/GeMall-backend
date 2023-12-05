package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallCollect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_collect(收藏表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallCollectService extends IService<GemallCollect> {

    /**
     * 查询用户的收藏表
     * @param userId
     * @param type
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    List<GemallCollect> queryByType(Integer userId, Integer type, Integer page, Integer limit, String sort, String order);

    /**
     * 根据id查询收藏信息
     * @param userId
     * @param type
     * @param valueId
     * @return
     */
    GemallCollect queryByTypeAndValue(Integer userId, Integer type, Integer valueId);

    /**
     * 用户收藏数量
     * @param userId
     * @param i
     * @param id
     * @return
     */
    int count(Integer userId, int i, Integer id);
}
