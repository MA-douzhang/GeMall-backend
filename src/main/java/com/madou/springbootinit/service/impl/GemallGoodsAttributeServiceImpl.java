package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallGoodsAttribute;
import com.madou.springbootinit.service.GemallGoodsAttributeService;
import com.madou.springbootinit.mapper.GemallGoodsAttributeMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_goods_attribute(商品参数表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallGoodsAttributeServiceImpl extends ServiceImpl<GemallGoodsAttributeMapper, GemallGoodsAttribute>
    implements GemallGoodsAttributeService{

}




