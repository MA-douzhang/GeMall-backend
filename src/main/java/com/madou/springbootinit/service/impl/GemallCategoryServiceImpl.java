package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallCategory;
import com.madou.springbootinit.service.GemallCategoryService;
import com.madou.springbootinit.mapper.GemallCategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MA_dou
 * @description 针对表【gemall_category(类目表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:25
 */
@Service
public class GemallCategoryServiceImpl extends ServiceImpl<GemallCategoryMapper, GemallCategory>
        implements GemallCategoryService {

    @Override
    public List<GemallCategory> queryL1() {
        QueryWrapper<GemallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", "L1");
        return this.list(queryWrapper);
    }

    @Override
    public List<GemallCategory> queryByPid(Integer id) {
        QueryWrapper<GemallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", id);
        return this.list(queryWrapper);
    }
}




