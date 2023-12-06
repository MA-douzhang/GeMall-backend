package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.model.entity.GemallIssue;
import com.madou.springbootinit.service.GemallIssueService;
import com.madou.springbootinit.mapper.GemallIssueMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MA_dou
 * @description 针对表【gemall_issue(常见问题表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:26
 */
@Service
public class GemallIssueServiceImpl extends ServiceImpl<GemallIssueMapper, GemallIssue>
        implements GemallIssueService {

    @Override
    public List<GemallIssue> querySelective(String question, Integer page, Integer size, String sort, String order) {

        QueryWrapper<GemallIssue> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(order)  && StringUtils.isNotBlank(sort)) {
            if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
                queryWrapper.orderByDesc(sort);
            } else {
                queryWrapper.orderByAsc(sort);
            }
        }

        queryWrapper.like("question", question);
        Page<GemallIssue> page1 = new Page<>(page, size);
        IPage<GemallIssue> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }
}




