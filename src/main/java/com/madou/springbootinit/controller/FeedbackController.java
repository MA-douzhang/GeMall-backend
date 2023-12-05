package com.madou.springbootinit.controller;

import com.madou.springbootinit.annotation.LoginUser;
import com.madou.springbootinit.model.entity.GemallFeedback;
import com.madou.springbootinit.model.entity.GemallUser;
import com.madou.springbootinit.service.GemallFeedbackService;
import com.madou.springbootinit.service.GemallUserService;
import com.madou.springbootinit.utils.RegexUtil;
import com.madou.springbootinit.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 意见反馈服务
 *
 * @author Yogeek
 * @date 2018/8/25 14:10
 */
@RestController
@RequestMapping("/feedback")
@Validated
public class FeedbackController {

    @Resource
    private GemallFeedbackService feedbackService;
    @Resource
    private GemallUserService userService;

    private Object validate(GemallFeedback feedback) {
        String content = feedback.getContent();
        if (StringUtils.isEmpty(content)) {
            return ResponseUtil.badArgument();
        }

        String type = feedback.getFeedType();
        if (StringUtils.isEmpty(type)) {
            return ResponseUtil.badArgument();
        }

        Boolean hasPicture = feedback.getHasPicture() == 1;
        if (hasPicture == null || !hasPicture) {
            feedback.setPicUrls(new  String());
        }

        // 测试手机号码是否正确
        String mobile = feedback.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isMobileSimple(mobile)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    /**
     * 添加意见反馈
     *
     * @param userId   用户ID
     * @param feedback 意见反馈
     * @return 操作结果
     */
    @PostMapping("submit")
    public Object submit(@LoginUser Integer userId, @RequestBody GemallFeedback feedback) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(feedback);
        if (error != null) {
            return error;
        }

        GemallUser user = userService.getById(userId);
        String username = user.getUsername();
        feedback.setId(null);
        feedback.setUserId(userId);
        feedback.setUsername(username);
        //状态默认是0，1表示状态已发生变化
        feedback.setStatus(1);
        feedbackService.save(feedback);

        return ResponseUtil.ok();
    }

}
