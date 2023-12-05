package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.model.entity.GemallBrand;
import com.madou.springbootinit.service.GemallBrandService;
import com.madou.springbootinit.mapper.GemallBrandMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MA_dou
 * @description 针对表【gemall_brand(品牌商表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:25
 */
@Service
public class GemallBrandServiceImpl extends ServiceImpl<GemallBrandMapper, GemallBrand>
        implements GemallBrandService {

    @Override
    public List<GemallBrand> queryList(Integer page, Integer limit, String sort, String order) {

        QueryWrapper<GemallBrand> queryWrapper = new QueryWrapper<>();
        if (sort != null) {
            if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
                queryWrapper.orderByDesc(sort);
            } else {
                queryWrapper.orderByAsc(sort);
            }
        }

        Page<GemallBrand> userPage = new Page<>(page, limit);
        IPage<GemallBrand> userIPage = this.page(userPage, queryWrapper);
        return userIPage.getRecords();

    }

    @Override
    public List<GemallBrand> query(int page, Integer limit) {
        return queryList(page, limit, null, null);
    }
}




