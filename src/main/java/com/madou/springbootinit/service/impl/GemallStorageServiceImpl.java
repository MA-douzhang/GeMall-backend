package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallStorage;
import com.madou.springbootinit.service.GemallStorageService;
import com.madou.springbootinit.mapper.GemallStorageMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_storage(文件存储表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallStorageServiceImpl extends ServiceImpl<GemallStorageMapper, GemallStorage>
    implements GemallStorageService{

}




