package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallComment;
import com.madou.springbootinit.service.GemallCommentService;
import com.madou.springbootinit.mapper.GemallCommentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_comment(评论表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallCommentServiceImpl extends ServiceImpl<GemallCommentMapper, GemallComment>
    implements GemallCommentService{

    @Override
    public List<GemallComment> queryGoodsByGid(Integer id, int offset, int limit) {

        QueryWrapper<GemallComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("add_time");
        queryWrapper.eq("value_id",id);
        queryWrapper.eq("type",0);
        Page<GemallComment> page1 = new Page<>(offset, limit);
        IPage<GemallComment> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }
}




