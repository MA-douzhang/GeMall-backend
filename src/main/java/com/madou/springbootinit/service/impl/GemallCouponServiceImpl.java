package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.common.CouponConstant;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.model.entity.GemallCoupon;
import com.madou.springbootinit.service.GemallCouponService;
import com.madou.springbootinit.mapper.GemallCouponMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MA_dou
 * @description 针对表【gemall_coupon(优惠券信息及规则表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallCouponServiceImpl extends ServiceImpl<GemallCouponMapper, GemallCoupon>
        implements GemallCouponService {

    @Override
    public List<GemallCoupon> queryList(Integer page, Integer limit, String sort, String order) {

        QueryWrapper<GemallCoupon> queryWrapper = new QueryWrapper<>();
        if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
            queryWrapper.orderByDesc(sort);
        } else {
            queryWrapper.orderByAsc(sort);
        }
        Page<GemallCoupon> page1 = new Page<>(page, limit);
        IPage<GemallCoupon> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }

    @Override
    public GemallCoupon findByCode(String code) {
        QueryWrapper<GemallCoupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        queryWrapper.eq("type", CouponConstant.TYPE_CODE);
        queryWrapper.eq("status", CouponConstant.STATUS_NORMAL);
        return this.getOne(queryWrapper);

    }

    @Override
    public List<GemallCoupon> queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }
}




