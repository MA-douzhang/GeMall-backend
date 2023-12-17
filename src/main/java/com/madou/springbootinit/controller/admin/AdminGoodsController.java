package com.madou.springbootinit.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.annotation.LoginUser;
import com.madou.springbootinit.common.BaseResponse;
import com.madou.springbootinit.common.DeleteRequest;
import com.madou.springbootinit.common.ErrorCode;
import com.madou.springbootinit.common.ResultUtils;
import com.madou.springbootinit.exception.BusinessException;
import com.madou.springbootinit.model.dto.adminGoods.GemallGoodsQueryRequest;
import com.madou.springbootinit.model.vo.GemallGoodsVO;
import com.madou.springbootinit.service.GemallGoodsService;
import com.madou.springbootinit.service.common.ExpressService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    /**
     * 查询商品列表
     * @param userId
     * @param queryRequest
     * @return
     */
    @PostMapping("/list")
    public BaseResponse<Page<GemallGoodsVO>> getGoodsList(@LoginUser Integer userId, @RequestBody GemallGoodsQueryRequest queryRequest) {
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
     * 删除商品
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteGoods(@LoginUser Integer userId,@RequestBody DeleteRequest deleteRequest) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return ResultUtils.success(gemallGoodsService.delete(deleteRequest));
    }


}
