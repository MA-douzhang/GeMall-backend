package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallCart;
import com.madou.springbootinit.service.GemallCartService;
import com.madou.springbootinit.mapper.GemallCartMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MA_dou
 * @description 针对表【gemall_cart(购物车商品表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:25
 */
@Service
public class GemallCartServiceImpl extends ServiceImpl<GemallCartMapper, GemallCart>
        implements GemallCartService {

    @Override
    public List<GemallCart> queryByUidAndChecked(Integer userId) {
        QueryWrapper<GemallCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("checked", true);
        return this.list(queryWrapper);
    }

    @Override
    public GemallCart findById(Integer userId, Integer cartId) {
        QueryWrapper<GemallCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("id", cartId);
        return this.getOne(queryWrapper);
    }

    @Override
    public void clearGoods(Integer userId) {
        QueryWrapper<GemallCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        this.remove(queryWrapper);
    }

    @Override
    public List<GemallCart> queryByUid(Integer userId) {

        QueryWrapper<GemallCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.list(queryWrapper);
    }

    @Override
    public GemallCart queryExist(Integer goodsId, Integer productId, Integer userId) {
        QueryWrapper<GemallCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("product_id", productId);
        queryWrapper.eq("goods_id", goodsId);
        return this.getOne(queryWrapper);
    }

    @Override
    public void updateCheck(Integer userId, List<Integer> productIds, Boolean isChecked) {
        QueryWrapper<GemallCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.in("product_id", productIds);
        GemallCart cart = new GemallCart();
        if (isChecked) {
            cart.setChecked(1);

        } else {
            cart.setChecked(0);
        }
        this.update(cart, queryWrapper);
    }

    @Override
    public void delete(List<Integer> productIds, Integer userId) {
        QueryWrapper<GemallCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.in("product_id", productIds);
        this.remove(queryWrapper);
    }
}




