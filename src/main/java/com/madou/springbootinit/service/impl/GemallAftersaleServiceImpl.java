package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallAftersale;
import com.madou.springbootinit.service.GemallAftersaleService;
import com.madou.springbootinit.mapper.GemallAftersaleMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_aftersale(售后表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:25
*/
@Service
public class GemallAftersaleServiceImpl extends ServiceImpl<GemallAftersaleMapper, GemallAftersale>
    implements GemallAftersaleService{


    @Override
    public void deleteByOrderId(Integer userId, Integer orderId) {
        QueryWrapper<GemallAftersale> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("order_id",orderId);
        remove(queryWrapper);
    }
}




