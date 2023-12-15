package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.common.ErrorCode;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.exception.BusinessException;
import com.madou.springbootinit.manager.UserTokenManager;
import com.madou.springbootinit.model.entity.GemallAddress;
import com.madou.springbootinit.model.entity.GemallAdmin;
import com.madou.springbootinit.model.vo.AdminUserVO;
import com.madou.springbootinit.service.GemallAdminService;
import com.madou.springbootinit.mapper.GemallAdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sun.javafx.font.FontResource.SALT;

/**
 * @author MA_dou
 * @description 针对表【gemall_admin(管理员表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:25
 */
@Service
@Slf4j
public class GemallAdminServiceImpl extends ServiceImpl<GemallAdminMapper, GemallAdmin>
        implements GemallAdminService {

    @Override
    public List<GemallAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order) {

        QueryWrapper<GemallAdmin> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            queryWrapper.like("user_name", username);
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
                queryWrapper.orderByDesc(sort);
            } else {
                queryWrapper.orderByAsc(sort);
            }
        }
        Page<GemallAdmin> page1 = new Page<>(page, limit);
        IPage<GemallAdmin> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }

    @Override
    public List<GemallAdmin> findAdmin(String username) {
        QueryWrapper<GemallAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        return this.list(queryWrapper);
    }

    @Override
    public AdminUserVO userLogin(String username, String password, HttpServletRequest request) {

        // 1. 校验
        if (org.apache.commons.lang3.StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (username.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        if (password.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 查询用户是否存在

        QueryWrapper<GemallAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", encryptPassword);
        GemallAdmin user = this.getOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }

        // 3. 记录用户的登录态
        return this.getAdminUserVO(user);
    }

    private AdminUserVO getAdminUserVO(GemallAdmin user) {
        if (user == null) {
            return null;
        }
        AdminUserVO adminUserVO = new AdminUserVO();
        BeanUtils.copyProperties(user, adminUserVO);
        // token
        String token = UserTokenManager.generateToken(user.getId());
        adminUserVO.setToken(token);
        return adminUserVO;

    }
}




