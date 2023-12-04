package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallKeyword;
import com.madou.springbootinit.service.GemallKeywordService;
import com.madou.springbootinit.mapper.GemallKeywordMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_keyword(关键字表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallKeywordServiceImpl extends ServiceImpl<GemallKeywordMapper, GemallKeyword>
    implements GemallKeywordService{

}




