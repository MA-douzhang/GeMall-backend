package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.common.ErrorCode;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.exception.BusinessException;
import com.madou.springbootinit.model.dto.gamallUser.GemallUserQueryRequest;
import com.madou.springbootinit.model.entity.GemallUser;
import com.madou.springbootinit.model.vo.GemallUserVO;
import com.madou.springbootinit.service.GemallUserService;
import com.madou.springbootinit.mapper.GemallUserMapper;
import com.madou.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MA_dou
 * @description 针对表【gemall_user(用户表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallUserServiceImpl extends ServiceImpl<GemallUserMapper, GemallUser>
        implements GemallUserService {

    @Override
    public List<GemallUser> queryByUsername(String username) {

        QueryWrapper<GemallUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.list(queryWrapper);
    }

    @Override
    public List<GemallUser> queryByMobile(String mobile) {
        QueryWrapper<GemallUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        return this.list(queryWrapper);
    }

    @Override
    public Page<GemallUserVO> getUserList(GemallUserQueryRequest queryRequest) {
        long current = queryRequest.getCurrent();
        long size = queryRequest.getPageSize();

        Page<GemallUser> userPage = page(new Page<>(current, size),
                this.getQueryWrapper(queryRequest));
        Page<GemallUserVO> userVOPage = new Page<>(current, size, userPage.getTotal());
        List<GemallUserVO> userVO = getUserVO(userPage.getRecords());
        userVOPage.setRecords(userVO);
        return  userVOPage;
    }
    public GemallUserVO getUserVO(GemallUser user) {
        if (user == null) {
            return null;
        }
        GemallUserVO userVO = new GemallUserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
    /**
     * 用户脱敏
     * @param records
     * @return
     */
    private List<GemallUserVO> getUserVO(List<GemallUser> records) {
        if (CollectionUtils.isEmpty(records)) {
            return new ArrayList<>();
        }
        return records.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<GemallUser> getQueryWrapper(GemallUserQueryRequest queryRequest) {

        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Integer id = queryRequest.getId();
        String userName = queryRequest.getUsername();
        String nickname = queryRequest.getNickname();
        Integer userLevel = queryRequest.getUserLevel();
        String sortField = queryRequest.getSortField();
        String sortOrder = queryRequest.getSortOrder();
        Integer gender = queryRequest.getGender();
        String mobile = queryRequest.getMobile();
        Integer status = queryRequest.getStatus();
        QueryWrapper<GemallUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(userLevel!=null, "user_level", userLevel);
        queryWrapper.eq(gender!=null, "gender", gender);
        queryWrapper.eq(status!=null, "status", status);
        queryWrapper.eq(StringUtils.isNotBlank(mobile), "mobile", mobile);
        queryWrapper.like(StringUtils.isNotBlank(nickname), "nickname", nickname);
        queryWrapper.like(StringUtils.isNotBlank(userName), "userName", userName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




