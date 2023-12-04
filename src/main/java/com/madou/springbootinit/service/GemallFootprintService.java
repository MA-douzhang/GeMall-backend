package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallFootprint;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_footprint(用户浏览足迹表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallFootprintService extends IService<GemallFootprint> {


    /**
     * 根据id和用户id查询足迹信息
     * @param userId
     * @param footprintId
     * @return
     */
    GemallFootprint findById(Integer userId, Integer footprintId);

    /**
     * 根据用户id查询分页列表
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    List<GemallFootprint> queryByAddTime(Integer userId, Integer page, Integer limit);
}
