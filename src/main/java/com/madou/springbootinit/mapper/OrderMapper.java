package com.madou.springbootinit.mapper;

import com.madou.springbootinit.model.entity.GemallOrder;
import com.madou.springbootinit.model.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderMapper {
    int updateWithOptimisticLocker(@Param("lastUpdateTime") LocalDateTime lastUpdateTime, @Param("order") GemallOrder order);

    List<Map> getOrderIds(@Param("query") String query, @Param("orderByClause") String orderByClause);

    List<OrderVo> getOrderList(@Param("query") String query, @Param("orderByClause") String orderByClause);
}
