package com.madou.springbootinit.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.annotation.LoginUser;
import com.madou.springbootinit.common.BaseResponse;
import com.madou.springbootinit.common.ErrorCode;
import com.madou.springbootinit.common.ResultUtils;
import com.madou.springbootinit.exception.BusinessException;
import com.madou.springbootinit.model.dto.adminAddress.GemallAddressQueryRequest;
import com.madou.springbootinit.model.dto.adminOrder.GemallOrderQueryRequest;
import com.madou.springbootinit.model.vo.AdminVendorsVO;
import com.madou.springbootinit.model.vo.GemallAddressVO;
import com.madou.springbootinit.model.vo.GemallOrderVO;
import com.madou.springbootinit.service.GemallAddressService;
import com.madou.springbootinit.service.GemallOrderService;
import com.madou.springbootinit.service.common.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管理端-订单管理
 */
@RestController
@RequestMapping("/admin/order")
@Validated
public class AdminOrderController {

    @Resource
    private GemallOrderService gemallOrderService;
    @Resource
    private ExpressService expressService;

    /**
     * 查询订单列表
     * @param userId
     * @param queryRequest
     * @return
     */
    @PostMapping("/list")
    public BaseResponse<Page<GemallOrderVO>> getOrderList(@LoginUser Integer userId, @RequestBody GemallOrderQueryRequest queryRequest) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<GemallOrderVO> userVOList = gemallOrderService.getList(queryRequest);
        return ResultUtils.success(userVOList);
    }
    /**
     * 查询物流公司
     *
     * @return
     */
    @GetMapping("/channel")
    public BaseResponse<List<AdminVendorsVO>> channel() {
        List<AdminVendorsVO> list = new ArrayList<>();
        List<Map<String, String>> vendors = expressService.getVendors();
        for(Map<String, String> map:vendors){
            AdminVendorsVO adminVendorsVO = new AdminVendorsVO();
            adminVendorsVO.setCode(map.get("code"));
            adminVendorsVO.setName(map.get("name"));
            list.add(adminVendorsVO);
        }
        return ResultUtils.success(list);
    }


}
