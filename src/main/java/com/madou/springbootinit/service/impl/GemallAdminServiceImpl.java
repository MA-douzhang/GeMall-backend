package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallAdmin;
import com.madou.springbootinit.service.GemallAdminService;
import com.madou.springbootinit.mapper.GemallAdminMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_admin(管理员表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:25
*/
@Service
public class GemallAdminServiceImpl extends ServiceImpl<GemallAdminMapper, GemallAdmin>
    implements GemallAdminService{

}




