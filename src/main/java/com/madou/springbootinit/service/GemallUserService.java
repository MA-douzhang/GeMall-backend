package com.madou.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.model.dto.gamallUser.GemallUserQueryRequest;
import com.madou.springbootinit.model.entity.GemallUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.madou.springbootinit.model.vo.GemallUserVO;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_user(用户表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallUserService extends IService<GemallUser> {

    /**
     * 根据用户名返回相同用户名的账户列表
     * @param username
     * @return
     */
    List<GemallUser> queryByUsername(String username);

    /**
     * 根据电话返回相同用户名的账户列表
     * @param mobile
     * @return
     */
    List<GemallUser> queryByMobile(String mobile);

    Page<GemallUserVO> getUserList(GemallUserQueryRequest queryRequest);

    /**
     * 用户查询条件
     * @param queryRequest
     * @return
     */
    QueryWrapper<GemallUser> getQueryWrapper(GemallUserQueryRequest queryRequest);
}
