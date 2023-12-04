package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallAd;
import com.madou.springbootinit.service.GemallAdService;
import com.madou.springbootinit.mapper.GemallAdMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_ad(广告表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:25
*/
@Service
public class GemallAdServiceImpl extends ServiceImpl<GemallAdMapper, GemallAd>
    implements GemallAdService{

}




