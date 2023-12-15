package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
* @author MA_dou
* @description 针对表【gemall_role(角色表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallRoleService extends IService<GemallRole> {

    /**
     * 查询id
     * @param roleIds
     * @return
     */
    Set<String> queryByIds(Integer[] roleIds);
}
