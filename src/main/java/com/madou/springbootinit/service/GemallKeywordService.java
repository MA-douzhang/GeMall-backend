package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallKeyword;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_keyword(关键字表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallKeywordService extends IService<GemallKeyword> {

    /**
     * 查询默认关键字
     * @return
     */
    GemallKeyword queryDefault();

    /**
     * 取出热门关键字
     * @return
     */
    List<GemallKeyword> queryHots();

    /**
     * 查询类似关键字
     * @param keyword
     * @param page
     * @param limit
     * @return
     */
    List<GemallKeyword> queryByKeyword(String keyword, Integer page, Integer limit);
}
