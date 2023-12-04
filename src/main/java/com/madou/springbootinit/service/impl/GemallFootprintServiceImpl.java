package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.model.entity.GemallFootprint;
import com.madou.springbootinit.service.GemallFootprintService;
import com.madou.springbootinit.mapper.GemallFootprintMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MA_dou
 * @description 针对表【gemall_footprint(用户浏览足迹表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallFootprintServiceImpl extends ServiceImpl<GemallFootprintMapper, GemallFootprint>
        implements GemallFootprintService {

    @Override
    public GemallFootprint findById(Integer userId, Integer footprintId) {
        QueryWrapper<GemallFootprint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", footprintId);
        queryWrapper.eq("user_id", userId);
        return this.getOne(queryWrapper);

    }

    @Override
    public List<GemallFootprint> queryByAddTime(Integer userId, Integer page, Integer limit) {
        QueryWrapper<GemallFootprint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        Page<GemallFootprint> page1 = new Page<>(page, limit);
        IPage<GemallFootprint> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }
}




