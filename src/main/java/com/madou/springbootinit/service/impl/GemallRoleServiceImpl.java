package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallRole;
import com.madou.springbootinit.service.GemallRoleService;
import com.madou.springbootinit.mapper.GemallRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_role(角色表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallRoleServiceImpl extends ServiceImpl<GemallRoleMapper, GemallRole>
    implements GemallRoleService{

}




