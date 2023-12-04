package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallSearchHistory;
import com.madou.springbootinit.service.GemallSearchHistoryService;
import com.madou.springbootinit.mapper.GemallSearchHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_search_history(搜索历史表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallSearchHistoryServiceImpl extends ServiceImpl<GemallSearchHistoryMapper, GemallSearchHistory>
    implements GemallSearchHistoryService{

}




