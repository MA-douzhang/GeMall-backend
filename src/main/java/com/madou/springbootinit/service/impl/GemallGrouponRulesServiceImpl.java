package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.model.entity.GemallGrouponRules;
import com.madou.springbootinit.service.GemallGrouponRulesService;
import com.madou.springbootinit.mapper.GemallGrouponRulesMapper;
import com.madou.springbootinit.utils.GrouponConstant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MA_dou
 * @description 针对表【gemall_groupon_rules(团购规则表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallGrouponRulesServiceImpl extends ServiceImpl<GemallGrouponRulesMapper, GemallGrouponRules>
        implements GemallGrouponRulesService {

    @Override
    public List<GemallGrouponRules> queryByGoodsId(Integer id) {
        QueryWrapper<GemallGrouponRules> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", id);
        return this.list(queryWrapper);

    }

    @Override
    public List<GemallGrouponRules> queryList(Integer page, Integer size, String sort, String order) {
        QueryWrapper<GemallGrouponRules> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", GrouponConstant.RULE_STATUS_ON);
        if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
            queryWrapper.orderByDesc(sort);
        } else {
            queryWrapper.orderByAsc(sort);
        }
        Page<GemallGrouponRules> page1 = new Page<>(page, size);
        IPage<GemallGrouponRules> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }
}




