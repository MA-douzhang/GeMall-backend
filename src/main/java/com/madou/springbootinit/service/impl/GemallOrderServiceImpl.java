package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.mapper.OrderMapper;
import com.madou.springbootinit.model.entity.GemallOrder;
import com.madou.springbootinit.service.GemallOrderService;
import com.madou.springbootinit.mapper.GemallOrderMapper;
import com.madou.springbootinit.utils.OrderUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author MA_dou
 * @description 针对表【gemall_order(订单表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallOrderServiceImpl extends ServiceImpl<GemallOrderMapper, GemallOrder>
        implements GemallOrderService {

    @Resource
    private OrderMapper orderMapper;
    @Override
    public Object orderInfo(Integer userId) {

        QueryWrapper<GemallOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<GemallOrder> orderList = this.list(queryWrapper);
        int unpaid = 0;
        int unship = 0;
        int unrecv = 0;
        int uncomment = 0;
        for (GemallOrder order : orderList) {
            if (OrderUtil.isCreateStatus(order)) {
                unpaid++;
            } else if (OrderUtil.isPayStatus(order)) {
                unship++;
            } else if (OrderUtil.isShipStatus(order)) {
                unrecv++;
            } else if (OrderUtil.isConfirmStatus(order) || OrderUtil.isAutoConfirmStatus(order)) {
                uncomment += order.getComments();
            } else {
                // do nothing
            }
        }

        Map<Object, Object> orderInfo = new HashMap<Object, Object>();
        orderInfo.put("unpaid", unpaid);
        orderInfo.put("unship", unship);
        orderInfo.put("unrecv", unrecv);
        orderInfo.put("uncomment", uncomment);
        return orderInfo;
    }

    @Override
    public List<GemallOrder> queryByOrderStatus(Integer userId, List<Integer> orderStatus, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<GemallOrder> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
                queryWrapper.orderByDesc(sort);
            } else {
                queryWrapper.orderByAsc(sort);
            }
        }
        if (orderStatus != null) {
            queryWrapper.in("order_status", orderStatus);
        }
        queryWrapper.eq("user_id", userId);
        Page<GemallOrder> page1 = new Page<>(page, limit);
        IPage<GemallOrder> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }

    @Override
    public GemallOrder findById(Integer userId, Integer orderId) {
        QueryWrapper<GemallOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("id", orderId);
        return this.getOne(queryWrapper);
    }

    @Override
    public String generateOrderSn(Integer userId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());
        String orderSn = now + getRandomNum(6);
        while (countByOrderSn(userId, orderSn) != 0) {
            orderSn = now + getRandomNum(6);
        }
        return orderSn;
    }

    @Override
    public int updateWithOptimisticLocker(GemallOrder order) {
        LocalDateTime preUpdateTime = order.getUpdateTime();
        order.setUpdateTime(LocalDateTime.now());
        return orderMapper.updateWithOptimisticLocker(preUpdateTime, order);
    }

    private String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    public int countByOrderSn(Integer userId, String orderSn) {
        QueryWrapper<GemallOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("order_sn",orderSn);
        return this.list(queryWrapper).size();
    }

}




