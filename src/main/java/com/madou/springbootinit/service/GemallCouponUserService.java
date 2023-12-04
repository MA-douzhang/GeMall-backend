package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallCouponUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_coupon_user(优惠券用户使用表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallCouponUserService extends IService<GemallCouponUser> {

    /**
     * 查询分页列表
     *
     * @param userId
     * @param couponId
     * @param status
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    List<GemallCouponUser> queryList(Integer userId, Integer couponId, Integer status, Integer page, Integer limit, String sort, String order);


    /**
     * 根据用户id查询用户所有优惠价
     * @param userId
     * @return
     */
    List<GemallCouponUser> queryAll(Integer userId);

    /**
     * 根据购物卷id查询使用数量
     * @param couponId
     * @return
     */
    Integer countCoupon(Integer couponId);

    /**
     * 根据购物卷id和用户id查询使用数量
     * @param userId
     * @param couponId
     * @return
     */
    Integer countUserAndCoupon(Integer userId, Integer couponId);

    /**
     * 根据订单id查询列表信息
     * @param orderId
     * @return
     */
    List<GemallCouponUser> findByOid(Integer orderId);
}
