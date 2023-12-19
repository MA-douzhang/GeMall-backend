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
import com.madou.springbootinit.model.dto.adminGoods.GemallGoodsAddRequest;
import com.madou.springbootinit.model.dto.adminGoods.GemallGoodsQueryRequest;
import com.madou.springbootinit.model.dto.gamallUser.GemallUserQueryRequest;
import com.madou.springbootinit.model.entity.*;
import com.madou.springbootinit.model.vo.AdminGoodsVO;
import com.madou.springbootinit.model.vo.CatVO;
import com.madou.springbootinit.model.vo.GemallGoodsVO;
import com.madou.springbootinit.model.vo.GemallUserVO;
import com.madou.springbootinit.service.*;
import com.madou.springbootinit.utils.ResponseUtil;
import com.madou.springbootinit.utils.SqlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Resource
    private GemallCategoryService gemallCategoryService;
    @Resource
    private GemallCartService gemallCartService;
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

    @Override
    public Boolean createGoods(GemallGoodsAddRequest goodsAllinone) {
        if (goodsAllinone==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        GemallGoods goods = goodsAllinone.getGoods();
        GemallGoodsAttribute[] attributes = goodsAllinone.getAttributes();
        GemallGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        GemallGoodsProduct[] products = goodsAllinone.getProducts();

        String name = goods.getName();
        QueryWrapper<GemallGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        GemallGoods one = this.getOne(queryWrapper);
        if (one!=null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"该商品名称已经存在");
        }

        // 商品表里面有一个字段retailPrice记录当前商品的最低价
        BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
        for (GemallGoodsProduct product : products) {
            BigDecimal productPrice = product.getPrice();
            if(retailPrice.compareTo(productPrice) == 1){
                retailPrice = productPrice;
            }
        }
        goods.setRetailPrice(retailPrice);

        // 商品基本信息表gmall_goods
        goods.setAddTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        goods.setDetail("");
        goods.setShareUrl("");
        boolean save = save(goods);
        if (!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"商品保存失败");
        }

        // 商品规格表litemall_goods_specification
        for (GemallGoodsSpecification specification : specifications) {
            specification.setGoodsId(goods.getId());
            specification.setAddTime(LocalDateTime.now());
            specification.setUpdateTime(LocalDateTime.now());
            specificationService.save(specification);
        }

        // 商品参数表litemall_goods_attribute
        for (GemallGoodsAttribute attribute : attributes) {
            attribute.setGoodsId(goods.getId());
            attribute.setAddTime(LocalDateTime.now());
            attribute.setUpdateTime(LocalDateTime.now());
            attributeService.save(attribute);
        }

        // 商品货品表litemall_product
        for (GemallGoodsProduct product : products) {
            product.setGoodsId(goods.getId());
            product.setAddTime(LocalDateTime.now());
            product.setUpdateTime(LocalDateTime.now());
            productService.save(product);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateGoods(GemallGoodsAddRequest goodsAllinone) {

        if (goodsAllinone==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        GemallGoods goods = goodsAllinone.getGoods();
        GemallGoodsAttribute[] attributes = goodsAllinone.getAttributes();
        GemallGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        GemallGoodsProduct[] products = goodsAllinone.getProducts();


        // 商品表里面有一个字段retailPrice记录当前商品的最低价
        BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
        for (GemallGoodsProduct product : products) {
            BigDecimal productPrice = product.getPrice();
            if(retailPrice.compareTo(productPrice) == 1){
                retailPrice = productPrice;
            }
        }
        goods.setRetailPrice(retailPrice);
        goods.setUpdateTime(LocalDateTime.now());
        // 商品基本信息表litemall_goods
        if (!updateById(goods)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"商品更新失败");
        }

        // 商品规格表litemall_goods_specification
        for (GemallGoodsSpecification specification : specifications) {
                specification.setSpecification(null);
                specification.setValue(null);
                specification.setUpdateTime(LocalDateTime.now());
                specificationService.updateById(specification);
        }

        // 商品参数表litemall_goods_attribute
        for (GemallGoodsAttribute attribute : attributes) {
            if (attribute.getId() == null || attribute.getId().equals(0)){
                attribute.setGoodsId(goods.getId());
                attribute.setAddTime(LocalDateTime.now());
                attribute.setUpdateTime(LocalDateTime.now());
                attributeService.save(attribute);
            }
            else{
                attribute.setUpdateTime(LocalDateTime.now());
                attributeService.updateById(attribute);
            }
        }

        // 商品货品表litemall_product
        for (GemallGoodsProduct product : products) {
                product.setUpdateTime(LocalDateTime.now());
                productService.updateById(product);

        }
        for (GemallGoodsProduct product : products) {
            gemallCartService.updateProduct(product.getId(), goods.getGoodsSn(), goods.getName(), product.getPrice(), product.getUrl());
        }
        return true;
    }

    @Override
    public AdminGoodsVO detailGoods(Integer id) {
        GemallGoods goods = getById(id);
        List<GemallGoodsProduct> products = productService.queryByGid(id);
        List<GemallGoodsSpecification> specifications = specificationService.queryByGid(id);
        List<GemallGoodsAttribute> attributes = attributeService.queryByGid(id);

        Integer categoryId = goods.getCategoryId();
        GemallCategory category = gemallCategoryService.getById(categoryId);
        Integer[] categoryIds = new Integer[]{};
        if (category != null) {
            Integer parentCategoryId = category.getPid();
            categoryIds = new Integer[]{parentCategoryId, categoryId};
        }
        AdminGoodsVO adminGoodsVO = new AdminGoodsVO();
        adminGoodsVO.setGoods(goods);
        adminGoodsVO.setAttributes(attributes);
        adminGoodsVO.setProducts(products);
        adminGoodsVO.setSpecifications(specifications);
        adminGoodsVO.setCategoryIds(categoryIds);


        return adminGoodsVO;
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




