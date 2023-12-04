package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.model.entity.GemallComment;
import com.madou.springbootinit.service.GemallCommentService;
import com.madou.springbootinit.mapper.GemallCommentMapper;
import org.springframework.stereotype.Service;

/**
* @author MA_dou
* @description 针对表【gemall_comment(评论表)】的数据库操作Service实现
* @createDate 2023-12-01 23:40:26
*/
@Service
public class GemallCommentServiceImpl extends ServiceImpl<GemallCommentMapper, GemallComment>
    implements GemallCommentService{

}




