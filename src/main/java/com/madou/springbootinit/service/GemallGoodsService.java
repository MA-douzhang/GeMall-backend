package com.madou.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.madou.springbootinit.common.DeleteRequest;
import com.madou.springbootinit.model.dto.adminGoods.GemallGoodsQueryRequest;
import com.madou.springbootinit.model.entity.GemallGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.madou.springbootinit.model.vo.GemallGoodsVO;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_goods(商品基本信息表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallGoodsService extends IService<GemallGoods> {

    /**
     * 查询商品信息
     * @param i
     * @return
     */
    GemallGoods findByIdVO(Integer i);

    /**
     * 商品分页列表
     * @param categoryId
     * @param brandId
     * @param keyword
     * @param isHot
     * @param isNew
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    List<GemallGoods> querySelective(Integer categoryId, Integer brandId, String keyword, Boolean isHot, Boolean isNew, Integer page, Integer limit, String sort, String order);

    /**
     * 查询商品所属目录
     * @param brandId
     * @param keyword
     * @param isHot
     * @param isNew
     * @return
     */
    List<Integer> getCatIds(Integer brandId, String keyword, Boolean isHot, Boolean isNew);

    /**
     * 获取分类下的商品
     * @param cid
     * @param i
     * @param related
     * @return
     */
    List<GemallGoods> queryByCategory(int cid, int i, int related);

    /**
     * 查询在售商品数量
     * @return
     */
    Integer queryOnSale();

    /**
     * 查询新品
     * @param offset
     * @param limit
     * @return
     */
    List<GemallGoods> queryByNew(int offset, int limit);

    /**
     * 查询热卖
     * @param offset
     * @param limit
     * @return
     */
    List<GemallGoods> queryByHot(int offset, int limit);
    /**
     * 获取分类下的商品
     *
     * @param catList
     * @param offset
     * @param limit
     * @return
     */
    List<GemallGoods> queryByCategory(List<Integer> catList, int offset, int limit);

    /**
     * 管理-查询分页商品
     * @param queryRequest
     * @return
     */
    Page<GemallGoodsVO> getList(GemallGoodsQueryRequest queryRequest);

    /**
     * 管理-删除商品
     * @param deleteRequest
     * @return
     */
    Boolean delete(DeleteRequest deleteRequest);
}
