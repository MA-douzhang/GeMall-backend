package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallAd;
import com.madou.springbootinit.service.GemallAdService;
import com.madou.springbootinit.mapper.GemallAdMapper;
import org.springframework.stereotype.Service;

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
}




