package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallAddress;
import com.madou.springbootinit.service.GemallAddressService;
import com.madou.springbootinit.mapper.GemallAddressMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_address(收货地址表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:25
*/
@Service
public class GemallAddressServiceImpl extends ServiceImpl<GemallAddressMapper, GemallAddress>
    implements GemallAddressService{

    @Override
    public List<GemallAddress> queryByUserId(Integer userId) {
        QueryWrapper<GemallAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return this.list(queryWrapper);
    }

    @Override
    public GemallAddress queryByIdInfo(Integer userId, Integer id) {
        QueryWrapper<GemallAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("id",id);
        return this.getOne(queryWrapper);
    }

    @Override
    public void resetDefault(Integer userId) {
        QueryWrapper<GemallAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        GemallAddress address = new GemallAddress();
        address.setIsDefault(0);
        address.setUpdateTime(LocalDateTime.now());
        this.update(address,queryWrapper);
    }

    @Override
    public Boolean add(GemallAddress address) {
        address.setAddTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        return this.save(address);
    }
}




