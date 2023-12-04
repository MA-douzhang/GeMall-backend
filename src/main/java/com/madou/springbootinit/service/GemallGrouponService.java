package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallGroupon;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_groupon(团购活动表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallGrouponService extends IService<GemallGroupon> {

    /**
     * 根据订单id查询团购信息
     * @param id
     * @return
     */
    GemallGroupon queryByOrderId(Integer id);

    /**
     * 查询团购人数是否以满
     * @param grouponLinkId
     * @return
     */
    Integer countGroupon(Integer grouponLinkId);

    /**
     * 根据用户id和团购id查询是否已经入团
     * @param userId
     * @param grouponLinkId
     * @return
     */
    boolean hasJoin(Integer userId, Integer grouponLinkId);

    /**
     * 获取某个团购活动参与的记录
     * @param grouponId
     * @return
     */
    List<GemallGroupon> queryJoinRecord(Integer grouponId);
}
