package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.common.DeleteRequest;
import com.madou.springbootinit.common.ErrorCode;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.exception.BusinessException;
import com.madou.springbootinit.mapper.GemallGoodsMapper;
import com.madou.springbootinit.model.dto.adminGoods.GemallGoodsQueryRequest;
import com.madou.springbootinit.model.dto.gamallUser.GemallUserQueryRequest;
import com.madou.springbootinit.model.entity.GemallGoods;
import com.madou.springbootinit.model.entity.GemallUser;
import com.madou.springbootinit.model.vo.GemallGoodsVO;
import com.madou.springbootinit.model.vo.GemallUserVO;
import com.madou.springbootinit.service.GemallGoodsAttributeService;
import com.madou.springbootinit.service.GemallGoodsProductService;
import com.madou.springbootinit.service.GemallGoodsService;
import com.madou.springbootinit.service.GemallGoodsSpecificationService;
import com.madou.springbootinit.utils.SqlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MA_dou
 * @description 针对表【gemall_goods(商品基本信息表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallGoodsServiceImpl extends ServiceImpl<GemallGoodsMapper, GemallGoods>
        implements GemallGoodsService {
    @Resource
    private GemallGoodsSpecificationService specificationService;
    @Resource
    private GemallGoodsAttributeService attributeService;
    @Resource
    private GemallGoodsProductService productService;
    @Override
    public GemallGoods findByIdVO(Integer i) {

        QueryWrapper<GemallGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", i);
        queryWrapper.eq("is_on_sale", true);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<GemallGoods> querySelective(Integer categoryId, Integer brandId, String keyword, Boolean isHot, Boolean isNew, Integer page, Integer limit, String sort, String order) {

        QueryWrapper<GemallGoods> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(categoryId) && categoryId != 0) {
            queryWrapper.eq("category_id", categoryId);
        }
        if (!StringUtils.isEmpty(brandId)) {
            queryWrapper.eq("brand_id", brandId);
        }
        if (!StringUtils.isEmpty(isNew)) {
            queryWrapper.eq("is_new", true);
        }
        if (!StringUtils.isEmpty(isHot)) {
            queryWrapper.eq("is_hot", true);
        }
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("name", keyword);
        }
        queryWrapper.eq("is_on_sale", 1);
        if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
            queryWrapper.orderByDesc(sort);
        } else {
            queryWrapper.orderByAsc(sort);
        }
        Page<GemallGoods> page1 = new Page<>(page, limit);
        IPage<GemallGoods> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }

    @Override
    public List<Integer> getCatIds(Integer brandId, String keyword, Boolean isHot, Boolean isNew) {
        QueryWrapper<GemallGoods> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(brandId)) {
            queryWrapper.eq("brand_id", brandId);
        }
        if (!StringUtils.isEmpty(isNew)) {
            queryWrapper.eq("is_new", true);
        }
        if (!StringUtils.isEmpty(isHot)) {
            queryWrapper.eq("is_hot", true);
        }
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("name", keyword);
            queryWrapper.like("keywords", keyword);
        }
        queryWrapper.like("is_on_sale", true);

        if (!StringUtils.isEmpty(brandId)) {
            queryWrapper.eq("brand_id", brandId);
        }
        List<GemallGoods> goodsList = this.list(queryWrapper);
        List<Integer> cats = new ArrayList<Integer>();
        for (GemallGoods goods : goodsList) {
            cats.add(goods.getCategoryId());
        }
        return cats;
    }

    @Override
    public List<GemallGoods> queryByCategory(int cid, int offset, int limit) {
        QueryWrapper<GemallGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", cid);
        queryWrapper.eq("is_on_sale", true);
        queryWrapper.orderByDesc("add_time");
        Page<GemallGoods> page1 = new Page<>(offset, limit);
        IPage<GemallGoods> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();

    }

    @Override
    public Integer queryOnSale() {
        QueryWrapper<GemallGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_on_sale", true);
        return this.list(queryWrapper).size();
    }

    @Override
    public List<GemallGoods> queryByNew(int offset, int limit) {
        QueryWrapper<GemallGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_on_sale", 1);
        queryWrapper.eq("is_new", 1);
        queryWrapper.orderByDesc("add_time");
        Page<GemallGoods> page1 = new Page<>(offset, limit);
        IPage<GemallGoods> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }

    @Override
    public List<GemallGoods> queryByHot(int offset, int limit) {
        QueryWrapper<GemallGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_on_sale", 1);
        queryWrapper.eq("is_hot", 1);
        queryWrapper.orderByDesc("add_time");
        Page<GemallGoods> page1 = new Page<>(offset, limit);
        IPage<GemallGoods> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();

    }

    @Override
    public List<GemallGoods> queryByCategory(List<Integer> catList, int offset, int limit) {
        QueryWrapper<GemallGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id", catList);
        queryWrapper.eq("is_on_sale", 1);
        queryWrapper.orderByDesc("add_time");
        return list(queryWrapper);
    }

    @Override
    public Page<GemallGoodsVO> getList(GemallGoodsQueryRequest queryRequest) {

        long current = queryRequest.getCurrent();
        long size = queryRequest.getPageSize();

        Page<GemallGoods> page = page(new Page<>(current, size),
                this.getQueryWrapper(queryRequest));
        Page<GemallGoodsVO> goodsVOPage = new Page<>(current, size, page.getTotal());
        List<GemallGoodsVO> userVOPage = goodsVOList(page.getRecords());
        goodsVOPage.setRecords(userVOPage);
        return  goodsVOPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(DeleteRequest deleteRequest) {
        if (deleteRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer id = Math.toIntExact(deleteRequest.getId());
        boolean remove1 = removeById(id);
        boolean remove2 = specificationService.removeById(id);
        boolean remove3 = attributeService.removeById(id);
        boolean remove4 = productService.removeById(id);
        return remove1&&remove2&&remove3&&remove4;
    }

    public GemallGoodsVO gemallGoodsVO(GemallGoods user) {
        if (user == null) {
            return null;
        }
        GemallGoodsVO userVO = new GemallGoodsVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
    /**
     * 用户脱敏
     * @param records
     * @return
     */
    private List<GemallGoodsVO> goodsVOList(List<GemallGoods> records) {
        if (CollectionUtils.isEmpty(records)) {
            return new ArrayList<>();
        }
        return records.stream().map(this::gemallGoodsVO).collect(Collectors.toList());
    }

    public QueryWrapper<GemallGoods> getQueryWrapper(GemallGoodsQueryRequest queryRequest) {

        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        String name = queryRequest.getName();
        Boolean isOnSale = queryRequest.getIsOnSale();
        String sortField = queryRequest.getSortField();
        String sortOrder = queryRequest.getSortOrder();
        QueryWrapper<GemallGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(isOnSale!=null, "is_on_sale", isOnSale);
        queryWrapper.like(org.apache.commons.lang3.StringUtils.isNotBlank(name), "name", name);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




