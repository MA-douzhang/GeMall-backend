package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallCategory;
import com.madou.springbootinit.model.vo.CatVO;
import com.madou.springbootinit.service.GemallCategoryService;
import com.madou.springbootinit.mapper.GemallCategoryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MA_dou
 * @description 针对表【gemall_category(类目表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:25
 */
@Service
public class GemallCategoryServiceImpl extends ServiceImpl<GemallCategoryMapper, GemallCategory>
        implements GemallCategoryService {

    @Override
    public List<GemallCategory> queryL1() {
        QueryWrapper<GemallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", "L1");
        return this.list(queryWrapper);
    }

    @Override
    public List<GemallCategory> queryByPid(Integer id) {
        QueryWrapper<GemallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", id);
        return this.list(queryWrapper);
    }

    @Override
    public List<GemallCategory> queryL2ByIds(List<Integer> goodsCatIds) {
        QueryWrapper<GemallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", goodsCatIds);
        queryWrapper.eq("level", "L2");
        return this.list(queryWrapper);

    }

    @Override
    public List<GemallCategory> queryChannel() {
        QueryWrapper<GemallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", "L1");
        return this.list(queryWrapper);

    }

    @Override
    public List<GemallCategory> queryL1WithoutRecommend(int offset, int limit) {
        QueryWrapper<GemallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", "L1");
        queryWrapper.notIn("name","推荐");
        Page<GemallCategory> page1 = new Page<>(offset, limit);
        IPage<GemallCategory> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();

    }

    @Override
    public List<CatVO> getCatList() {

        // http://element-cn.eleme.io/#/zh-CN/component/cascader
        // 管理员设置“所属分类”
        List<GemallCategory> l1CatList = queryL1();
        List<CatVO> categoryList = new ArrayList<>(l1CatList.size());

        for (GemallCategory l1 : l1CatList) {
            CatVO l1CatVO = new CatVO();
            l1CatVO.setValue(l1.getId());
            l1CatVO.setLabel(l1.getName());

            List<GemallCategory> l2CatList = queryByPid(l1.getId());
            List<CatVO> children = new ArrayList<>(l2CatList.size());
            for (GemallCategory l2 : l2CatList) {
                CatVO l2CatVO = new CatVO();
                l2CatVO.setValue(l2.getId());
                l2CatVO.setLabel(l2.getName());
                children.add(l2CatVO);
            }
            l1CatVO.setChildren(children);

            categoryList.add(l1CatVO);
        }

        return categoryList;
    }

}




