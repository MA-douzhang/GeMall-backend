package com.madou.springbootinit.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.annotation.LoginUser;
import com.madou.springbootinit.common.BaseResponse;
import com.madou.springbootinit.common.DeleteRequest;
import com.madou.springbootinit.common.ErrorCode;
import com.madou.springbootinit.common.ResultUtils;
import com.madou.springbootinit.exception.BusinessException;
import com.madou.springbootinit.model.dto.adminGoods.GemallGoodsAddRequest;
import com.madou.springbootinit.model.dto.adminGoods.GemallGoodsQueryRequest;
import com.madou.springbootinit.model.vo.AdminGoodsVO;
import com.madou.springbootinit.model.vo.CatVO;
import com.madou.springbootinit.model.vo.GemallGoodsVO;
import com.madou.springbootinit.service.GemallCartService;
import com.madou.springbootinit.service.GemallCategoryService;
import com.madou.springbootinit.service.GemallGoodsService;
import com.madou.springbootinit.service.common.ExpressService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 管理端-商品管理
 */
@RestController
@RequestMapping("/admin/goods")
@Validated
public class AdminGoodsController {

    @Resource
    private GemallGoodsService gemallGoodsService;
    @Resource
    private ExpressService expressService;

    @Resource
    private GemallCategoryService gemallCategoryService;

    /**
     * 查询商品列表
     * @param userId
     * @param queryRequest
     * @return
     */
    @PostMapping("/list")
    public BaseResponse<Page<GemallGoodsVO>> getGoodsList( @RequestBody GemallGoodsQueryRequest queryRequest,@LoginUser Integer userId) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<GemallGoodsVO> list = gemallGoodsService.getList(queryRequest);
        return ResultUtils.success(list);
    }

    /**
     * 添加商品
     *
     * @param goodsAllinone
     * @return
     */
    @PostMapping("/create")
    public BaseResponse<Boolean> createGoods(@RequestBody GemallGoodsAddRequest goodsAllinone) {
        return ResultUtils.success(gemallGoodsService.createGoods(goodsAllinone));
    }
    /**
     * 编辑商品
     *
     * @param goodsAllinone
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody GemallGoodsAddRequest goodsAllinone) {
        return ResultUtils.success(gemallGoodsService.updateGoods(goodsAllinone));
    }
    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public BaseResponse<AdminGoodsVO> detail(Integer id) {
        return ResultUtils.success(gemallGoodsService.detailGoods(id));

    }
    /**
     * 删除商品
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteGoods(@RequestBody DeleteRequest deleteRequest,@LoginUser Integer userId) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return ResultUtils.success(gemallGoodsService.delete(deleteRequest));
    }

    /**
     * 商品类别
     * @return
     */
    @GetMapping("/catAndBrand")
    public  BaseResponse<List<CatVO>> getCatList() {
        return ResultUtils.success(gemallCategoryService.getCatList());
    }
}
