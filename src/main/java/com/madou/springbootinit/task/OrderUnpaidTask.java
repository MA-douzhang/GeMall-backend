package com.madou.springbootinit.task;

import com.madou.springbootinit.model.entity.GemallOrder;
import com.madou.springbootinit.model.entity.GemallOrderGoods;
import com.madou.springbootinit.service.GemallGoodsProductService;
import com.madou.springbootinit.service.GemallOrderGoodsService;
import com.madou.springbootinit.service.GemallOrderService;
import com.madou.springbootinit.system.SystemConfig;
import com.madou.springbootinit.utils.BeanUtil;
import com.madou.springbootinit.utils.OrderUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDateTime;
import java.util.List;

public class OrderUnpaidTask extends Task {
    private final Log logger = LogFactory.getLog(OrderUnpaidTask.class);
    private int orderId = -1;

    public OrderUnpaidTask(Integer orderId, long delayInMilliseconds){
        super("OrderUnpaidTask-" + orderId, delayInMilliseconds);
        this.orderId = orderId;
    }

    public OrderUnpaidTask(Integer orderId){
        super("OrderUnpaidTask-" + orderId, SystemConfig.getOrderUnpaid() * 60 * 1000);
        this.orderId = orderId;
    }

    @Override
    public void run() {
        logger.info("系统开始处理延时任务---订单超时未付款---" + this.orderId);

        GemallOrderService orderService = BeanUtil.getBean(GemallOrderService.class);
        GemallOrderGoodsService orderGoodsService = BeanUtil.getBean(GemallOrderGoodsService.class);
        GemallGoodsProductService productService = BeanUtil.getBean(GemallGoodsProductService.class);

        GemallOrder order = orderService.getById(this.orderId);
        if(order == null){
            return;
        }
        if(!OrderUtil.isCreateStatus(order)){
            return;
        }

        // 设置订单已取消状态
        order.setOrderStatus(OrderUtil.STATUS_AUTO_CANCEL);
        order.setEndTime(LocalDateTime.now());
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            throw new RuntimeException("更新数据已失效");
        }

        // 商品货品数量增加
        Integer orderId = order.getId();
        List<GemallOrderGoods> orderGoodsList = orderGoodsService.queryByOid(orderId);
        for (GemallOrderGoods orderGoods : orderGoodsList) {
            Integer productId = orderGoods.getProductId();
            Integer number = orderGoods.getNumber();
            if (productService.addStock(productId, number) == 0) {
                throw new RuntimeException("商品货品库存增加失败");
            }
        }

        //返还优惠券

        logger.info("系统结束处理延时任务---订单超时未付款---" + this.orderId);
    }
}
