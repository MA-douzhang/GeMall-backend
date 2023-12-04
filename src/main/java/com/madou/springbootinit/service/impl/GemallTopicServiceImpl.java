package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallTopic;
import com.madou.springbootinit.service.GemallTopicService;
import com.madou.springbootinit.mapper.GemallTopicMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_topic(专题表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallTopicServiceImpl extends ServiceImpl<GemallTopicMapper, GemallTopic>
    implements GemallTopicService{

}




