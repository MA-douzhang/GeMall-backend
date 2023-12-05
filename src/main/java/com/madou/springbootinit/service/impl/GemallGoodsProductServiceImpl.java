package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.mapper.GoodsProductMapper;
import com.madou.springbootinit.model.entity.GemallGoodsProduct;
import com.madou.springbootinit.model.entity.GemallGoodsSpecification;
import com.madou.springbootinit.service.GemallGoodsProductService;
import com.madou.springbootinit.mapper.GemallGoodsProductMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_goods_product(商品货品表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallGoodsProductServiceImpl extends ServiceImpl<GemallGoodsProductMapper, GemallGoodsProduct>
    implements GemallGoodsProductService{

    @Resource
    private GoodsProductMapper goodsProductMapper;

    @Override
    public int reduceStock(Integer productId, Integer number) {
        return goodsProductMapper.reduceStock(productId, number);
    }

    @Override
    public int addStock(Integer productId, Integer number) {
        return goodsProductMapper.addStock(productId, number);
    }

    @Override
    public List<GemallGoodsProduct> queryByGid(Integer id) {
        QueryWrapper<GemallGoodsProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", id);
        return this.list(queryWrapper);
    }
}




