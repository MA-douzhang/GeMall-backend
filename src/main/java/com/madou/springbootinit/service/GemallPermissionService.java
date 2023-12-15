package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
* @author MA_dou
* @description 针对表【gemall_permission(权限表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallPermissionService extends IService<GemallPermission> {

    /**
     * 查询权限
     * @param roleIds
     * @return
     */
    Set<String> queryByRoleIds(Integer[] roleIds);
}
