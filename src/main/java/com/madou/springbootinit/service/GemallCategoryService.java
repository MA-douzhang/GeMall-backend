package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_category(类目表)】的数据库操作Service
* @createDate 2023-12-01 23:40:25
*/
public interface GemallCategoryService extends IService<GemallCategory> {

    /**
     * 查询一级目录
     * @return
     */
    List<GemallCategory> queryL1();

    /**
     * 根据父级id查询子目录
     * @param id
     * @return
     */
    List<GemallCategory> queryByPid(Integer id);

    /**
     * 查询二级类目
     * @param goodsCatIds
     * @return
     */
    List<GemallCategory> queryL2ByIds(List<Integer> goodsCatIds);

    /**
     * 查询目录列表
     * @return
     */
    List<GemallCategory> queryChannel();

    /**
     * 查询一级目录
     * @param offset
     * @param limit
     * @return
     */
    List<GemallCategory> queryL1WithoutRecommend(int offset,  int limit);

}
