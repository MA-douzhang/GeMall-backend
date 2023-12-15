package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallPermission;
import com.madou.springbootinit.model.entity.GemallRole;
import com.madou.springbootinit.service.GemallPermissionService;
import com.madou.springbootinit.mapper.GemallPermissionMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author MA_dou
* @description 针对表【gemall_permission(权限表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallPermissionServiceImpl extends ServiceImpl<GemallPermissionMapper, GemallPermission>
    implements GemallPermissionService{

    @Override
    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }
        QueryWrapper<GemallPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_ids",Arrays.asList(roleIds));
        List<GemallPermission> permissionList = this.list(queryWrapper);

        for(GemallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }
}




