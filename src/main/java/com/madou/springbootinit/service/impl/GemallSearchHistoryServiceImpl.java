package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallKeyword;
import com.madou.springbootinit.model.entity.GemallSearchHistory;
import com.madou.springbootinit.service.GemallSearchHistoryService;
import com.madou.springbootinit.mapper.GemallSearchHistoryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author MA_dou
* @description 针对表【gemall_search_history(搜索历史表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallSearchHistoryServiceImpl extends ServiceImpl<GemallSearchHistoryMapper, GemallSearchHistory>
    implements GemallSearchHistoryService{

    @Override
    public List<GemallSearchHistory> queryByUid(Integer userId) {
        QueryWrapper<GemallSearchHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<GemallSearchHistory> list = this.list(queryWrapper);
        Set<GemallSearchHistory> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }

    @Override
    public void deleteByUid(Integer userId) {
        QueryWrapper<GemallSearchHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        this.remove(queryWrapper);
    }
}




