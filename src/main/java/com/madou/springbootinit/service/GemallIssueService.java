package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallIssue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_issue(常见问题表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallIssueService extends IService<GemallIssue> {

    /**
     * 查询分页列表
     * @param question
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    List<GemallIssue> querySelective(String question, Integer page, Integer size, String sort, String order);
}
