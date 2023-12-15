package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallRole;
import com.madou.springbootinit.service.GemallRoleService;
import com.madou.springbootinit.mapper.GemallRoleMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author MA_dou
* @description 针对表【gemall_role(角色表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallRoleServiceImpl extends ServiceImpl<GemallRoleMapper, GemallRole>
    implements GemallRoleService{

    @Override
    public Set<String> queryByIds(Integer[] roleIds) {

        Set<String> roles = new HashSet<String>();
        if(roleIds.length == 0){
            return roles;
        }
        QueryWrapper<GemallRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_ids",Arrays.asList(roleIds));
        queryWrapper.eq("enable",true);
        List<GemallRole> roleList = this.list(queryWrapper);

        for(GemallRole role : roleList){
            roles.add(role.getName());
        }

        return roles;
    }
}




