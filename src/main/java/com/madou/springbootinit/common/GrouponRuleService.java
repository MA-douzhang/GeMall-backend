package com.madou.springbootinit.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.model.entity.GemallGoods;
import com.madou.springbootinit.model.entity.GemallGrouponRules;
import com.madou.springbootinit.model.vo.GrouponRuleVo;
import com.madou.springbootinit.service.GemallGoodsService;
import com.madou.springbootinit.service.GemallGrouponRulesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrouponRuleService {

    @Resource
    private GemallGrouponRulesService gemallGrouponRulesService;
    @Resource
    private GemallGoodsService goodsService;


    public List<GrouponRuleVo> queryList(Integer page, Integer size) {
        return queryList(page, size, "add_time", "desc");
    }


    public List<GrouponRuleVo> queryList(Integer page, Integer size, String sort, String order) {
        List<GemallGrouponRules> gemallGrouponRules = gemallGrouponRulesService.queryList(page, size, sort, order);

        List<GrouponRuleVo> list = new ArrayList<>();
        for (GemallGrouponRules rule : gemallGrouponRules) {
            Integer goodsId = rule.getGoodsId();
            GemallGoods goods = goodsService.getById(goodsId);
            if (goods == null)
                continue;

            GrouponRuleVo grouponRuleVo = new GrouponRuleVo();
            grouponRuleVo.setId(goods.getId());
            grouponRuleVo.setName(goods.getName());
            grouponRuleVo.setBrief(goods.getBrief());
            grouponRuleVo.setPicUrl(goods.getPicUrl());
            grouponRuleVo.setCounterPrice(goods.getCounterPrice());
            grouponRuleVo.setRetailPrice(goods.getRetailPrice());
            grouponRuleVo.setGrouponPrice(goods.getRetailPrice().subtract(rule.getDiscount()));
            grouponRuleVo.setGrouponDiscount(rule.getDiscount());
            grouponRuleVo.setGrouponMember(rule.getDiscountMember());
            grouponRuleVo.setExpireTime(rule.getExpireTime());
            list.add(grouponRuleVo);
        }
        return list;
    }
}
