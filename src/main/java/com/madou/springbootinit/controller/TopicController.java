package com.madou.springbootinit.controller;

import com.madou.springbootinit.annotation.LoginUser;
import com.madou.springbootinit.model.entity.GemallGoods;
import com.madou.springbootinit.model.entity.GemallTopic;
import com.madou.springbootinit.service.GemallCollectService;
import com.madou.springbootinit.service.GemallGoodsService;
import com.madou.springbootinit.service.GemallTopicService;
import com.madou.springbootinit.utils.ResponseUtil;
import com.madou.springbootinit.validator.Order;
import com.madou.springbootinit.validator.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专题服务
 */
@RestController
@RequestMapping("/topic")
@Validated
public class TopicController {

    @Resource
    private GemallTopicService topicService;
    @Resource
    private GemallGoodsService goodsService;
    @Resource
    private GemallCollectService collectService;

    /**
     * 专题列表
     *
     * @param page  分页页数
     * @param limit 分页大小
     * @return 专题列表
     */
    @GetMapping("/list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<GemallTopic> topicList = topicService.queryList(page, limit, sort, order);
        return ResponseUtil.okList(topicList);
    }

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    @GetMapping("/detail")
    public Object detail(@LoginUser Integer userId, @NotNull Integer id) {
        GemallTopic topic = topicService.getById(id);
        List<GemallGoods> goods = new ArrayList<>();
        for (String i : topic.getGoods().split("")){
            GemallGoods good = goodsService.findByIdVO(i);
            if (null != good)
                goods.add(good);
        }

        // 用户收藏
        int userHasCollect = 0;
        if (userId != null) {
            userHasCollect = collectService.count(userId, 1, id);
        }

        Map<String, Object> entity = new HashMap<String, Object>();
        entity.put("topic", topic);
        entity.put("goods", goods);
        entity.put("userHasCollect", userHasCollect);
        return ResponseUtil.ok(entity);
    }

    /**
     * 相关专题
     *
     * @param id 专题ID
     * @return 相关专题
     */
    @GetMapping("/related")
    public Object related(@NotNull Integer id) {
        List<GemallTopic> topicRelatedList = topicService.queryRelatedList(id, 0, 4);
        return ResponseUtil.okList(topicRelatedList);
    }
}
