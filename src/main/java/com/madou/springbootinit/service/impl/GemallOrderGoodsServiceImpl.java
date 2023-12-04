package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallOrderGoods;
import com.madou.springbootinit.service.GemallOrderGoodsService;
import com.madou.springbootinit.mapper.GemallOrderGoodsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MA_dou
 * @description 针对表【gemall_order_goods(订单商品表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallOrderGoodsServiceImpl extends ServiceImpl<GemallOrderGoodsMapper, GemallOrderGoods>
        implements GemallOrderGoodsService {

    @Override
    public List<GemallOrderGoods> queryByOid(Integer id) {

        QueryWrapper<GemallOrderGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", id);
        return this.list(queryWrapper);
    }

    @Override
    public Integer getComments(Integer orderId) {
        QueryWrapper<GemallOrderGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return this.list(queryWrapper).size();
    }
}




