package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallGroupon;
import com.madou.springbootinit.service.GemallGrouponService;
import com.madou.springbootinit.mapper.GemallGrouponMapper;
import com.madou.springbootinit.utils.GrouponConstant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MA_dou
 * @description 针对表【gemall_groupon(团购活动表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallGrouponServiceImpl extends ServiceImpl<GemallGrouponMapper, GemallGroupon>
        implements GemallGrouponService {

    @Override
    public GemallGroupon queryByOrderId(Integer id) {
        QueryWrapper<GemallGroupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", id);
        return this.getOne(queryWrapper);

    }

    @Override
    public Integer countGroupon(Integer grouponLinkId) {
        QueryWrapper<GemallGroupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupon_id", grouponLinkId);
        queryWrapper.eq("status", GrouponConstant.STATUS_NONE);
        return this.list(queryWrapper).size();
    }

    @Override
    public boolean hasJoin(Integer userId, Integer grouponLinkId) {
        QueryWrapper<GemallGroupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupon_id", grouponLinkId);
        queryWrapper.eq("status", GrouponConstant.STATUS_NONE);
        queryWrapper.eq("user_id",userId);
        return this.list(queryWrapper).size()!=0;
    }

    @Override
    public List<GemallGroupon> queryJoinRecord(Integer grouponId) {
        QueryWrapper<GemallGroupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("groupon_id",grouponId);
        queryWrapper.eq("status",GrouponConstant.STATUS_NONE);
        queryWrapper.orderByDesc("add_time");
        return list(queryWrapper);
    }
}




