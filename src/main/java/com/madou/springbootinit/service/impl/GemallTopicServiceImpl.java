package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.model.entity.GemallTopic;
import com.madou.springbootinit.service.GemallTopicService;
import com.madou.springbootinit.mapper.GemallTopicMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_topic(专题表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallTopicServiceImpl extends ServiceImpl<GemallTopicMapper, GemallTopic>
    implements GemallTopicService{

    @Override
    public List<GemallTopic> queryList(Integer page, Integer limit, String sort, String order) {

        QueryWrapper<GemallTopic> queryWrapper = new QueryWrapper<>();
        if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
            queryWrapper.orderByDesc(sort);
        } else {
            queryWrapper.orderByAsc(sort);
        }
        Page<GemallTopic> page1 = new Page<>(page, limit);
        IPage<GemallTopic> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }

    @Override
    public List<GemallTopic> queryRelatedList(Integer id, int offset, int limit) {
        GemallTopic byId = this.getById(id);
        if (byId == null) {
            return queryList(offset, limit, "add_time", "desc");
        }
        QueryWrapper<GemallTopic> queryWrapper = new QueryWrapper<>();
        queryWrapper.notIn("id",id);
        Page<GemallTopic> page1 = new Page<>(offset, limit);
        IPage<GemallTopic> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }
}




