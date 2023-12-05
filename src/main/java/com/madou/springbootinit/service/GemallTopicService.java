package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallTopic;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_topic(专题表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallTopicService extends IService<GemallTopic> {

    /**
     * 查询分页列表
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    List<GemallTopic> queryList(Integer page, Integer limit, String sort, String order);

    /**
     * 查询专题分页列表
     * @param id
     * @param offset
     * @param limit
     * @return
     */
    List<GemallTopic> queryRelatedList(Integer id, int offset, int limit);
}
