package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.constant.CouponUserConstant;
import com.madou.springbootinit.model.entity.GemallCouponUser;
import com.madou.springbootinit.service.GemallCouponUserService;
import com.madou.springbootinit.mapper.GemallCouponUserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MA_dou
 * @description 针对表【gemall_coupon_user(优惠券用户使用表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallCouponUserServiceImpl extends ServiceImpl<GemallCouponUserMapper, GemallCouponUser>
        implements GemallCouponUserService {

    @Override
    public List<GemallCouponUser> queryList(Integer userId, Integer couponId, Integer status, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<GemallCouponUser> queryWrapper = new QueryWrapper<>();
        if (userId != null) {
            queryWrapper.eq("user_id", userId);
        }
        if (couponId != null) {
            queryWrapper.eq("coupon_id", couponId);
        }
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
            queryWrapper.orderByDesc(sort);
        } else {
            queryWrapper.orderByAsc(sort);
        }
        Page<GemallCouponUser> page1 = new Page<>(page, limit);
        IPage<GemallCouponUser> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }

    @Override
    public List<GemallCouponUser> queryAll(Integer userId) {
        return queryList(userId, null, CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
    }

    @Override
    public Integer countCoupon(Integer couponId) {
        QueryWrapper<GemallCouponUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("coupon_id", couponId);
        return this.list(queryWrapper).size();
    }

    @Override
    public Integer countUserAndCoupon(Integer userId, Integer couponId) {
        QueryWrapper<GemallCouponUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("coupon_id", couponId);
        return this.list(queryWrapper).size();
    }

    @Override
    public List<GemallCouponUser> findByOid(Integer orderId) {
        QueryWrapper<GemallCouponUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return list(queryWrapper);
    }

    @Override
    public GemallCouponUser queryOne(Integer userId, Integer couponId) {
        List<GemallCouponUser> couponUserList = queryList(userId, couponId, CouponUserConstant.STATUS_USABLE, 1, 1, "add_time", "desc");
        if (couponUserList.size() == 0) {
            return null;
        }
        return couponUserList.get(0);

    }
}




