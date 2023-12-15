package com.madou.springbootinit.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.annotation.LoginUser;
import com.madou.springbootinit.common.BaseResponse;
import com.madou.springbootinit.common.ErrorCode;
import com.madou.springbootinit.common.ResultUtils;
import com.madou.springbootinit.exception.BusinessException;
import com.madou.springbootinit.manager.UserTokenManager;
import com.madou.springbootinit.model.dto.adminUser.AdminUserLoginRequest;
import com.madou.springbootinit.model.dto.gamallUser.GemallUserQueryRequest;
import com.madou.springbootinit.model.dto.user.UserInfo;
import com.madou.springbootinit.model.entity.GemallAdmin;
import com.madou.springbootinit.model.entity.GemallUser;
import com.madou.springbootinit.model.vo.AdminUserVO;
import com.madou.springbootinit.model.vo.GemallUserVO;
import com.madou.springbootinit.service.GemallAdminService;
import com.madou.springbootinit.service.GemallUserService;
import com.madou.springbootinit.utils.IpUtil;
import com.madou.springbootinit.utils.JacksonUtil;
import com.madou.springbootinit.utils.RegexUtil;
import com.madou.springbootinit.utils.ResponseUtil;
import com.madou.springbootinit.utils.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.madou.springbootinit.constant.ResponseCode.*;

/**
 * 管理端-用户端
 */
@RestController
@RequestMapping("/admin/user")
@Validated
public class AdminUserController {

    @Resource
    private GemallAdminService adminService;

    @Resource
    private GemallUserService gemallUserService;
    /**
     * 账号登录
     *
     * @param loginRequest    请求内容，{ username: xxx, password: xxx }
     * @param request 请求对象
     * @return 登录结果
     */
    @PostMapping("/login")
    public BaseResponse<AdminUserVO> login(@RequestBody AdminUserLoginRequest loginRequest, HttpServletRequest request) {
        if (loginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (username == null || password == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        AdminUserVO adminUserVO = adminService.userLogin(username, password, request);
        return ResultUtils.success(adminUserVO);
    }


    @PostMapping("/logout")
    public Object logout(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        return ResponseUtil.ok();
    }


    @PostMapping("/list")
    public BaseResponse<Page<GemallUserVO>> getUserList(@LoginUser Integer userId, @RequestBody GemallUserQueryRequest queryRequest) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<GemallUserVO> userVOList = gemallUserService.getUserList(queryRequest);
        return ResultUtils.success(userVOList);
    }


}
