package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallGoods;
import com.madou.springbootinit.service.GemallGoodsService;
import com.madou.springbootinit.mapper.GemallGoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_goods(商品基本信息表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallGoodsServiceImpl extends ServiceImpl<GemallGoodsMapper, GemallGoods>
    implements GemallGoodsService{

}




