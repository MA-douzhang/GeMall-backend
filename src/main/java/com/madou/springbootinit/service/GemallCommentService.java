package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_comment(评论表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallCommentService extends IService<GemallComment> {

    /**
     * 查询分页列表
     * @param id
     * @param offset
     * @param limit
     * @return
     */
    List<GemallComment> queryGoodsByGid(Integer id, int offset, int limit);
}
