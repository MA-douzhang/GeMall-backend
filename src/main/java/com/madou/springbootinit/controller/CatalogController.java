package com.madou.springbootinit.controller;

import com.madou.springbootinit.model.entity.GemallCategory;
import com.madou.springbootinit.service.GemallCategoryService;
import com.madou.springbootinit.utils.ResponseUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类目服务
 */
@RestController
@RequestMapping("/catalog")
@Validated
public class CatalogController {

    @Resource
    private GemallCategoryService categoryService;

    @GetMapping("/getfirstcategory")
    public Object getFirstCategory() {
        // 所有一级分类目录
        List<GemallCategory> l1CatList = categoryService.queryL1();
        return ResponseUtil.ok(l1CatList);
    }

    @GetMapping("/getsecondcategory")
    public Object getSecondCategory(@NotNull Integer id) {
        // 所有二级分类目录
        List<GemallCategory> currentSubCategory = categoryService.queryByPid(id);
        return ResponseUtil.ok(currentSubCategory);
    }

    /**
     * 分类详情
     *
     * @param id   分类类目ID。
     *             如果分类类目ID是空，则选择第一个分类类目。
     *             需要注意，这里分类类目是一级类目
     * @return 分类详情
     */
    @GetMapping("/index")
    public Object index(Integer id) {

        // 所有一级分类目录
        List<GemallCategory> l1CatList = categoryService.queryL1();

        // 当前一级分类目录
        GemallCategory currentCategory = null;
        if (id != null) {
            currentCategory = categoryService.getById(id);
        } else {
             if (l1CatList.size() > 0) {
                currentCategory = l1CatList.get(0);
            }
        }

        // 当前一级分类目录对应的二级分类目录
        List<GemallCategory> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.queryByPid(currentCategory.getId());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("categoryList", l1CatList);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return ResponseUtil.ok(data);
    }

    /**
     * 所有分类数据
     *
     * @return 所有分类数据
     */
    @GetMapping("/all")
    public Object queryAll() {
        //todo 改为Redis
//        //优先从缓存中读取
//        if (HomeCacheManager.hasData(HomeCacheManager.CATALOG)) {
//            return ResponseUtil.ok(HomeCacheManager.getCacheData(HomeCacheManager.CATALOG));
//        }


        // 所有一级分类目录
        List<GemallCategory> l1CatList = categoryService.queryL1();

        //所有子分类列表
        Map<Integer, List<GemallCategory>> allList = new HashMap<>();
        List<GemallCategory> sub;
        for (GemallCategory category : l1CatList) {
            sub = categoryService.queryByPid(category.getId());
            allList.put(category.getId(), sub);
        }

        // 当前一级分类目录
        GemallCategory currentCategory = l1CatList.get(0);

        // 当前一级分类目录对应的二级分类目录
        List<GemallCategory> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.queryByPid(currentCategory.getId());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("categoryList", l1CatList);
        data.put("allList", allList);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);

        //todo 改为redis
//        //缓存数据
//        HomeCacheManager.loadData(HomeCacheManager.CATALOG, data);
        return ResponseUtil.ok(data);
    }

    /**
     * 当前分类栏目
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    @GetMapping("/current")
    public Object current(@NotNull Integer id) {
        // 当前分类
        GemallCategory currentCategory = categoryService.getById(id);
        if(currentCategory == null){
            return ResponseUtil.badArgumentValue();
        }
        List<GemallCategory> currentSubCategory = categoryService.queryByPid(currentCategory.getId());

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return ResponseUtil.ok(data);
    }
}
