package com.madou.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.model.dto.adminAddress.GemallAddressQueryRequest;
import com.madou.springbootinit.model.entity.GemallAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import com.madou.springbootinit.model.vo.GemallAddressVO;

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

    /**
     * 根据用户id和地址id查询地址信息
     * @param userId
     * @param addressId
     * @return
     */
    GemallAddress query(Integer userId, Integer addressId);

    /**
     * 查询用户默认地址
     * @param userId
     * @return
     */
    GemallAddress findDefault(Integer userId);

    /**
     * 管理-查询地址
     * @param userId
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    List<GemallAddress> querySelective(Integer userId, String name, Integer page, Integer limit, String sort, String order);

    /**
     * 管理-查询地址列表
     * @param queryRequest
     * @return
     */
    Page<GemallAddressVO> getList(GemallAddressQueryRequest queryRequest);
}
