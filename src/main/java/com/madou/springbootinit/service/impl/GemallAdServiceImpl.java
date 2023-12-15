package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.model.entity.GemallAd;
import com.madou.springbootinit.model.entity.GemallIssue;
import com.madou.springbootinit.service.GemallAdService;
import com.madou.springbootinit.mapper.GemallAdMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author MA_dou
 * @description 针对表【gemall_ad(广告表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:25
 */
@Service
public class GemallAdServiceImpl extends ServiceImpl<GemallAdMapper, GemallAd>
        implements GemallAdService {

    @Override
    public List<GemallAd> queryIndex() {
        QueryWrapper<GemallAd> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("position", 1);
        queryWrapper.eq("enabled", 1);
        return this.list(queryWrapper);

    }

    @Override
    public List<GemallAd> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {

        QueryWrapper<GemallAd> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(content)) {
            queryWrapper.like("content",content);
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
                queryWrapper.orderByDesc(sort);
            } else {
                queryWrapper.orderByAsc(sort);
            }
        }

        Page<GemallAd> page1 = new Page<>(page, limit);
        IPage<GemallAd> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }
}




