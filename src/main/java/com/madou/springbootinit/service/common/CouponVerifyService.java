package com.madou.springbootinit.service.common;

import com.madou.springbootinit.common.CouponConstant;
import com.madou.springbootinit.model.entity.GemallCart;
import com.madou.springbootinit.model.entity.GemallCoupon;
import com.madou.springbootinit.model.entity.GemallCouponUser;
import com.madou.springbootinit.service.GemallCouponService;
import com.madou.springbootinit.service.GemallCouponUserService;
import com.madou.springbootinit.service.GemallGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CouponVerifyService {

    @Resource
    private GemallCouponUserService couponUserService;
    @Resource
    private GemallCouponService couponService;
    @Resource
    private GemallGoodsService goodsService;

    /**
     * 检测优惠券是否适合
     *
     * @param userId
     * @param couponId
     * @param checkedGoodsPrice
     * @return
     */
    public GemallCoupon checkCoupon(Integer userId, Integer couponId, Integer userCouponId, BigDecimal checkedGoodsPrice, List<GemallCart> cartList) {
        GemallCoupon coupon = couponService.getById(couponId);
        if (coupon == null || coupon.getDeleted() == 1) {
            return null;
        }

        GemallCouponUser couponUser = couponUserService.getById(userCouponId);
        if (couponUser == null) {
            couponUser = couponUserService.queryOne(userId, couponId);
        } else if (!couponId.equals(couponUser.getCouponId())) {
            return null;
        }

        if (couponUser == null) {
            return null;
        }

        // 检查是否超期
        Integer timeType = coupon.getTimeType();
        Integer days = coupon.getDays();
        LocalDateTime now = LocalDateTime.now();
        if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
            if (now.isBefore(coupon.getStartTime()) || now.isAfter(coupon.getEndTime())) {
                return null;
            }
        }
        else if(timeType.equals(CouponConstant.TIME_TYPE_DAYS)) {
            LocalDateTime expired = couponUser.getAddTime().plusDays(days);
            if (now.isAfter(expired)) {
                return null;
            }
        }
        else {
            return null;
        }

        // 检测商品是否符合
        Map<Integer, List<GemallCart>> cartMap = new HashMap<>();
        //可使用优惠券的商品或分类
        String[] split = coupon.getGoodsValue().split("");
        Integer[] items = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            items[i]=Integer.parseInt(split[i]);
        }
        List<Integer> goodsValueList = new ArrayList<>(Arrays.asList(items));
        Integer goodType = coupon.getGoodsType();

        if (goodType.equals(CouponConstant.GOODS_TYPE_CATEGORY) ||
                goodType.equals((CouponConstant.GOODS_TYPE_ARRAY))) {
            for (GemallCart cart : cartList) {
                Integer key = goodType.equals(CouponConstant.GOODS_TYPE_ARRAY) ? cart.getGoodsId() :
                        goodsService.getById(cart.getGoodsId()).getCategoryId();
                List<GemallCart> carts = cartMap.get(key);
                if (carts == null) {
                    carts = new LinkedList<>();
                }
                carts.add(cart);
                cartMap.put(key, carts);
            }
            //购物车中可以使用优惠券的商品或分类
            goodsValueList.retainAll(cartMap.keySet());
            //可使用优惠券的商品的总价格
            BigDecimal total = new BigDecimal(0);

            for (Integer goodsId : goodsValueList) {
                List<GemallCart> carts = cartMap.get(goodsId);
                for (GemallCart cart : carts) {
                    total = total.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
                }
            }
            //是否达到优惠券满减金额
            if (total.compareTo(coupon.getMin()) == -1) {
                return null;
            }
        }

        // 检测订单状态
        Integer status = coupon.getStatus();
        if (!status.equals(CouponConstant.STATUS_NORMAL)) {
            return null;
        }
        // 检测是否满足最低消费
        if (checkedGoodsPrice.compareTo(coupon.getMin()) == -1) {
            return null;
        }

        return coupon;
    }

}
