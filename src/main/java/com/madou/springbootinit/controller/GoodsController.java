package com.madou.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.annotation.LoginUser;
import com.madou.springbootinit.model.entity.*;
import com.madou.springbootinit.service.*;
import com.madou.springbootinit.system.SystemConfig;
import com.madou.springbootinit.utils.ResponseUtil;
import com.madou.springbootinit.validator.Order;
import com.madou.springbootinit.validator.Sort;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 商品服务
 */
@RestController
@RequestMapping("/goods")
@Validated
public class GoodsController {

    @Resource
    private GemallGoodsService goodsService;

    @Resource
    private GemallGoodsProductService productService;

    @Resource
    private GemallIssueService goodsIssueService;

    @Resource
    private GemallGoodsAttributeService goodsAttributeService;

    @Resource
    private GemallBrandService brandService;

    @Resource
    private GemallCommentService commentService;

    @Resource
    private GemallUserService userService;

    @Resource
    private GemallCollectService collectService;

    @Resource
    private GemallFootprintService footprintService;

    @Resource
    private GemallCategoryService categoryService;

    @Resource
    private GemallSearchHistoryService searchHistoryService;

    @Resource
    private GemallGoodsSpecificationService goodsSpecificationService;

    @Resource
    private GemallGrouponRulesService rulesService;

    private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<>(9);

    private final static RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(16, 16, 1000, TimeUnit.MILLISECONDS, WORK_QUEUE, HANDLER);

    /**
     * 商品详情
     * <p>
     * 用户可以不登录。
     * 如果用户登录，则记录用户足迹以及返回用户收藏信息。
     *
     * @param userId 用户ID
     * @param id     商品ID
     * @return 商品详情
     */
    @GetMapping("/detail")
    public Object detail(@LoginUser Integer userId, @NotNull Integer id) {
        // 商品信息
        GemallGoods info = goodsService.getById(id);

        // 商品属性
        Callable<List> goodsAttributeListCallable = () -> goodsAttributeService.queryByGid(id);

        // 商品规格 返回的是定制的GoodsSpecificationVo
        Callable<Object> objectCallable = () -> goodsSpecificationService.getSpecificationVoList(id);

        // 商品规格对应的数量和价格
        Callable<List> productListCallable = () -> productService.queryByGid(id);

        // 商品问题，这里是一些通用问题
        Callable<List> issueCallable = () -> goodsIssueService.querySelective("", 1, 4, "", "");

        // 商品品牌商
        Callable<GemallBrand> brandCallable = () -> {
            Integer brandId = info.getBrandId();
            GemallBrand brand;
            if (brandId == 0) {
                brand = new GemallBrand();
            } else {
                brand = brandService.getById(info.getBrandId());
            }
            return brand;
        };

        // 评论
        Callable<Map> commentsCallable = () -> {
            List<GemallComment> comments = commentService.queryGoodsByGid(id, 0, 2);
            List<Map<String, Object>> commentsVo = new ArrayList<>(comments.size());
            int commentCount = comments.size();
            for (GemallComment comment : comments) {
                Map<String, Object> c = new HashMap<>();
                c.put("id", comment.getId());
                c.put("addTime", comment.getAddTime());
                c.put("content", comment.getContent());
                c.put("adminContent", comment.getAdminContent());
                GemallUser user = userService.getById(comment.getUserId());
                c.put("nickname", user == null ? "" : user.getNickname());
                c.put("avatar", user == null ? "" : user.getAvatar());
                c.put("picList", comment.getPicUrls());
                commentsVo.add(c);
            }
            Map<String, Object> commentList = new HashMap<>();
            commentList.put("count", commentCount);
            commentList.put("data", commentsVo);
            return commentList;
        };

        //团购信息
        Callable<List> grouponRulesCallable = () -> rulesService.queryByGoodsId(id);

        // 用户收藏
        int userHasCollect = 0;
        if (userId != null) {
            userHasCollect = collectService.count(userId, (byte) 0, id);
        }

        // 记录用户的足迹 异步处理
        if (userId != null) {
            executorService.execute(() -> {
                GemallFootprint footprint = new GemallFootprint();
                footprint.setUserId(userId);
                footprint.setGoodsId(id);
                footprintService.save(footprint);
            });
        }
        FutureTask<List> goodsAttributeListTask = new FutureTask<>(goodsAttributeListCallable);
        FutureTask<Object> objectCallableTask = new FutureTask<>(objectCallable);
        FutureTask<List> productListCallableTask = new FutureTask<>(productListCallable);
        FutureTask<List> issueCallableTask = new FutureTask<>(issueCallable);
        FutureTask<Map> commentsCallableTsk = new FutureTask<>(commentsCallable);
        FutureTask<GemallBrand> brandCallableTask = new FutureTask<>(brandCallable);
        FutureTask<List> grouponRulesCallableTask = new FutureTask<>(grouponRulesCallable);

        executorService.submit(goodsAttributeListTask);
        executorService.submit(objectCallableTask);
        executorService.submit(productListCallableTask);
        executorService.submit(issueCallableTask);
        executorService.submit(commentsCallableTsk);
        executorService.submit(brandCallableTask);
        executorService.submit(grouponRulesCallableTask);

        Map<String, Object> data = new HashMap<>();

        try {
            data.put("info", info);
            data.put("userHasCollect", userHasCollect);
            data.put("issue", issueCallableTask.get());
            data.put("comment", commentsCallableTsk.get());
            data.put("specificationList", objectCallableTask.get());
            data.put("productList", productListCallableTask.get());
            data.put("attribute", goodsAttributeListTask.get());
            data.put("brand", brandCallableTask.get());
            data.put("groupon", grouponRulesCallableTask.get());
//            SystemConfig.isAutoCreateShareImage();
            data.put("share", SystemConfig.isAutoCreateShareImage());

        } catch (Exception e) {
            e.printStackTrace();
        }

        //商品分享图片地址
        data.put("shareImage", info.getShareUrl());
        return ResponseUtil.ok(data);
    }

    /**
     * 商品分类类目
     *
     * @param id 分类类目ID
     * @return 商品分类类目
     */
    @GetMapping("/category")
    public Object category(@NotNull Integer id) {
        GemallCategory cur = categoryService.getById(id);
        GemallCategory parent = null;
        List<GemallCategory> children = null;

        if (cur.getPid() == 0) {
            parent = cur;
            children = categoryService.queryByPid(cur.getId());
            cur = children.size() > 0 ? children.get(0) : cur;
        } else {
            parent = categoryService.getById(cur.getPid());
            children = categoryService.queryByPid(cur.getPid());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("currentCategory", cur);
        data.put("parentCategory", parent);
        data.put("brotherCategory", children);
        return ResponseUtil.ok(data);
    }

    /**
     * 根据条件搜素商品
     * <p>
     * 1. 这里的前五个参数都是可选的，甚至都是空
     * 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
     *
     * @param categoryId 分类类目ID，可选
     * @param brandId    品牌商ID，可选
     * @param keyword    关键字，可选
     * @param isNew      是否新品，可选
     * @param isHot      是否热买，可选
     * @param userId     用户ID
     * @param page       分页页数
     * @param limit      分页大小
     * @param sort       排序方式，支持"add_time", "retail_price"或"name"
     * @param order      排序类型，顺序或者降序
     * @return 根据条件搜素的商品详情
     */
    @GetMapping("/list")
    public Object list(
            Integer categoryId,
            Integer brandId,
            String keyword,
            Boolean isNew,
            Boolean isHot,
            @LoginUser Integer userId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @Sort(accepts = {"add_time", "retail_price", "name"}) @RequestParam(defaultValue = "add_time") String sort,
            @Order @RequestParam(defaultValue = "desc") String order) {

        //添加到搜索历史
        if (userId != null && !StringUtils.isEmpty(keyword)) {
            GemallSearchHistory searchHistoryVo = new GemallSearchHistory();
            searchHistoryVo.setKeyword(keyword);
            searchHistoryVo.setUserId(userId);
            searchHistoryVo.setFrom("wx");
            searchHistoryVo.setAddTime(LocalDateTime.now());
            searchHistoryVo.setUpdateTime(LocalDateTime.now());
            searchHistoryService.save(searchHistoryVo);
        }

        //查询列表数据
        List<GemallGoods> goodsList = goodsService.querySelective(categoryId, brandId, keyword, isHot, isNew, page, limit, sort, order);

        // 查询商品所属类目列表。
        List<Integer> goodsCatIds = goodsService.getCatIds(brandId, keyword, isHot, isNew);
        List<GemallCategory> categoryList = null;
        if (goodsCatIds.size() != 0) {
            categoryList = categoryService.queryL2ByIds(goodsCatIds);
        } else {
            categoryList = new ArrayList<>(0);
        }

        Page<GemallGoods> pagedList = new Page<>(page, limit);
        pagedList.setRecords(goodsList);
        Map<String, Object> entity = new HashMap<>();
        entity.put("list", goodsList);
        entity.put("total", new Long(pagedList.getTotal()).intValue());
        entity.put("page", new Long(pagedList.getCurrent()).intValue());
        entity.put("limit", new Long(pagedList.getSize()).intValue());
        entity.put("pages", new Long(pagedList.getPages()).intValue());
        entity.put("filterCategoryList", categoryList);

        // 因为这里需要返回额外的filterCategoryList参数，因此不能方便使用ResponseUtil.okList
        return ResponseUtil.ok(entity);
    }

    /**
     * 商品详情页面“大家都在看”推荐商品
     *
     * @param id, 商品ID
     * @return 商品详情页面推荐商品
     */
    @GetMapping("related")
    public Object related(@NotNull Integer id) {
        GemallGoods goods = goodsService.getById(id);
        if (goods == null) {
            return ResponseUtil.badArgumentValue();
        }

        // 目前的商品推荐算法仅仅是推荐同类目的其他商品
        int cid = goods.getCategoryId();

        // 查找六个相关商品
        int related = 6;
        List<GemallGoods> goodsList = goodsService.queryByCategory(cid, 0, related);
        return ResponseUtil.okList(goodsList);
    }

    /**
     * 在售的商品总数
     *
     * @return 在售的商品总数
     */
    @GetMapping("/count")
    public Object count() {
        Integer goodsCount = goodsService.queryOnSale();
        return ResponseUtil.ok(goodsCount);
    }

}
