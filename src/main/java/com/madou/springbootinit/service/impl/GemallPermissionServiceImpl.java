package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallPermission;
import com.madou.springbootinit.service.GemallPermissionService;
import com.madou.springbootinit.mapper.GemallPermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_permission(权限表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallPermissionServiceImpl extends ServiceImpl<GemallPermissionMapper, GemallPermission>
    implements GemallPermissionService{

}




