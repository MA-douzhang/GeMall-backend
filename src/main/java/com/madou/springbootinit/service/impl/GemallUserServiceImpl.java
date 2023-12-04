package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallUser;
import com.madou.springbootinit.service.GemallUserService;
import com.madou.springbootinit.mapper.GemallUserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
}




