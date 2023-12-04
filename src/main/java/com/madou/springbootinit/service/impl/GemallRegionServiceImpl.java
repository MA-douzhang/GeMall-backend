package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallRegion;
import com.madou.springbootinit.service.GemallRegionService;
import com.madou.springbootinit.mapper.GemallRegionMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_region(行政区域表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallRegionServiceImpl extends ServiceImpl<GemallRegionMapper, GemallRegion>
    implements GemallRegionService{

}




