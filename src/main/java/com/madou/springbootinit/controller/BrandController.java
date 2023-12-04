package com.madou.springbootinit.controller;

import com.madou.springbootinit.model.entity.GemallBrand;
import com.madou.springbootinit.service.GemallBrandService;
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
import java.util.List;

/**
 * 专题服务
 */
@RestController
@RequestMapping("/brand")
@Validated
public class BrandController {

    @Resource
    private GemallBrandService brandService;

    /**
     * 品牌列表
     *
     * @param page 分页页数
     * @param limit 分页大小
     * @return 品牌列表
     */
    @GetMapping("list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<GemallBrand> brandList = brandService.queryList(page, limit, sort, order);
        return ResponseUtil.okList(brandList);
    }

    /**
     * 品牌详情
     *
     * @param id 品牌ID
     * @return 品牌详情
     */
    @GetMapping("detail")
    public Object detail(@NotNull Integer id) {
        GemallBrand entity = brandService.getById(id);
        if (entity == null) {
            return ResponseUtil.badArgumentValue();
        }

        return ResponseUtil.ok(entity);
    }
}
