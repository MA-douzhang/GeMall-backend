package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallGrouponRules;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_groupon_rules(团购规则表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallGrouponRulesService extends IService<GemallGrouponRules> {

    /**
     * 根据商品id查询团购规则
     * @param id
     * @return
     */
    List<GemallGrouponRules> queryByGoodsId(Integer id);

    /**
     * 查询分页列表
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    List<GemallGrouponRules> queryList(Integer page, Integer size, String sort, String order);
}
