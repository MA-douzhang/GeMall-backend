package com.madou.springbootinit.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.annotation.LoginUser;
import com.madou.springbootinit.common.BaseResponse;
import com.madou.springbootinit.common.ErrorCode;
import com.madou.springbootinit.common.ResultUtils;
import com.madou.springbootinit.exception.BusinessException;
import com.madou.springbootinit.model.dto.adminAddress.GemallAddressQueryRequest;
import com.madou.springbootinit.model.dto.adminUser.AdminUserLoginRequest;
import com.madou.springbootinit.model.dto.gamallUser.GemallUserQueryRequest;
import com.madou.springbootinit.model.vo.AdminUserVO;
import com.madou.springbootinit.model.vo.GemallAddressVO;
import com.madou.springbootinit.model.vo.GemallUserVO;
import com.madou.springbootinit.service.GemallAddressService;
import com.madou.springbootinit.service.GemallAdminService;
import com.madou.springbootinit.service.GemallUserService;
import com.madou.springbootinit.utils.ResponseUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 管理端-地址管理
 */
@RestController
@RequestMapping("/admin/address")
@Validated
public class AdminAddressController {

    @Resource
    private GemallAddressService gemallAddressService;


    /**
     * 查询地址列表
     * @param userId
     * @param queryRequest
     * @return
     */
    @PostMapping("/list")
    public BaseResponse<Page<GemallAddressVO>> getAddressList(@RequestBody GemallAddressQueryRequest queryRequest,@LoginUser Integer userId) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<GemallAddressVO> userVOList = gemallAddressService.getList(queryRequest);
        return ResultUtils.success(userVOList);
    }


}
