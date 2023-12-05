package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallKeyword;
import com.madou.springbootinit.service.GemallKeywordService;
import com.madou.springbootinit.mapper.GemallKeywordMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author MA_dou
 * @description 针对表【gemall_keyword(关键字表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallKeywordServiceImpl extends ServiceImpl<GemallKeywordMapper, GemallKeyword>
        implements GemallKeywordService {

    @Override
    public GemallKeyword queryDefault() {

        QueryWrapper<GemallKeyword> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_default", true);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<GemallKeyword> queryHots() {
        QueryWrapper<GemallKeyword> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_hot", true);
        return this.list(queryWrapper);
    }

    @Override
    public List<GemallKeyword> queryByKeyword(String keyword, Integer page, Integer limit) {
        QueryWrapper<GemallKeyword> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("keyword",keyword);
        Page<GemallKeyword> page1 = new Page<>(page, limit);
        IPage<GemallKeyword> iPage = this.page(page1, queryWrapper);
        List<GemallKeyword> records = iPage.getRecords();
        Set<GemallKeyword> set = new HashSet<>(records);
        return new ArrayList<>(set);
    }
}




