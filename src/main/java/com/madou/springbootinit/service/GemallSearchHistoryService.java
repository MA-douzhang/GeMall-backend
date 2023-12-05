package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallSearchHistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_search_history(搜索历史表)】的数据库操作Service
* @createDate 2023-12-01 23:40:26
*/
public interface GemallSearchHistoryService extends IService<GemallSearchHistory> {

    /**
     * 取出用户历史关键字
     * @param userId
     * @return
     */
    List<GemallSearchHistory> queryByUid(Integer userId);

    /**
     * 根据用户id删除记录
     * @param userId
     */
    void deleteByUid(Integer userId);
}
