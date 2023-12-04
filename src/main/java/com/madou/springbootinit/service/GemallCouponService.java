package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallCoupon;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_coupon(优惠券信息及规则表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallCouponService extends IService<GemallCoupon> {

    /**
     * 查询分页列表
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    List<GemallCoupon> queryList(Integer page, Integer limit, String sort, String order);

    /**
     * 根据优惠价兑换码查询优惠价
     * @param code
     * @return
     */
    GemallCoupon findByCode(String code);
}
