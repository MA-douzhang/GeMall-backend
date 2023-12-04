package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallLog;
import com.madou.springbootinit.service.GemallLogService;
import com.madou.springbootinit.mapper.GemallLogMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_log(操作日志表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallLogServiceImpl extends ServiceImpl<GemallLogMapper, GemallLog>
    implements GemallLogService{

}




