package com.madou.springbootinit.controller;

import com.madou.springbootinit.model.entity.GemallIssue;
import com.madou.springbootinit.service.GemallIssueService;
import com.madou.springbootinit.utils.ResponseUtil;
import com.madou.springbootinit.validator.Order;
import com.madou.springbootinit.validator.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/issue")
@Validated
public class IssueController {

    @Resource
    private GemallIssueService issueService;

    /**
     * 帮助中心
     */
    @GetMapping("/list")
    public Object list(String question,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<GemallIssue> issueList = issueService.querySelective(question, page, size, sort, order);
        return ResponseUtil.okList(issueList);
    }

}
