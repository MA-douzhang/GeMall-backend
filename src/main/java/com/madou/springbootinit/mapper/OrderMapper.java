package com.madou.springbootinit.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.model.dto.adminOrder.GemallOrderQueryRequest;
import com.madou.springbootinit.model.entity.GemallOrder;
import com.madou.springbootinit.model.vo.GemallOrderVO;
import com.madou.springbootinit.model.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderMapper {
    int updateWithOptimisticLocker(@Param("lastUpdateTime") LocalDateTime lastUpdateTime, @Param("order") GemallOrder order);

    List<Map> getOrderIds(@Param("query") String query, @Param("orderByClause") String orderByClause);

    List<OrderVo> getOrderList(@Param("query") String query, @Param("orderByClause") String orderByClause);

    Page<GemallOrderVO> getOrderVOPage(@Param("query") GemallOrderQueryRequest queryRequest, IPage<GemallOrderVO> iPage);
}
