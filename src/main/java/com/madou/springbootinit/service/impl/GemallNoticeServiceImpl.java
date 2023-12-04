package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallNotice;
import com.madou.springbootinit.service.GemallNoticeService;
import com.madou.springbootinit.mapper.GemallNoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_notice(通知表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallNoticeServiceImpl extends ServiceImpl<GemallNoticeMapper, GemallNotice>
    implements GemallNoticeService{

}




