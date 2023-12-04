package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_address(收货地址表)】的数据库操作Service
* @createDate 2023-12-01 23:40:25
*/
public interface GemallAddressService extends IService<GemallAddress> {

    /**
     * 根据用户id查询用户地址信息
     * @param userId
     * @return
     */
    List<GemallAddress> queryByUserId(Integer userId);

    /**
     * 根据用户id和地址id查询地址详情
     * @param userId
     * @param id
     * @return
     */
    GemallAddress queryByIdInfo(Integer userId, Integer id);

    /**
     * 重置为用户的默认地址
     * @param userId
     */
    void resetDefault(Integer userId);

    /**
     * 添加地址信息
     * @param address
     * @return
     */
    Boolean add(GemallAddress address);
}
