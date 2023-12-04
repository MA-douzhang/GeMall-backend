package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.model.entity.GemallCollect;
import com.madou.springbootinit.service.GemallCollectService;
import com.madou.springbootinit.mapper.GemallCollectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_collect(收藏表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallCollectServiceImpl extends ServiceImpl<GemallCollectMapper, GemallCollect>
    implements GemallCollectService{

    @Override
    public List<GemallCollect> queryByType(Integer userId, Integer type, Integer page, Integer limit, String sort, String order) {

        QueryWrapper<GemallCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("type",type);
        if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
            queryWrapper.orderByDesc(sort);
        } else {
            queryWrapper.orderByAsc(sort);
        }
        Page<GemallCollect> page1 = new Page<>(page, limit);
        IPage<GemallCollect> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }

    @Override
    public GemallCollect queryByTypeAndValue(Integer userId, Integer type, Integer valueId) {
        QueryWrapper<GemallCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("type",type);
        queryWrapper.eq("value_id",valueId);
        return this.getOne(queryWrapper);
    }
}




