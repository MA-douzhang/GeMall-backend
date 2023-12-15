package com.madou.springbootinit.controller.admin;

import com.madou.springbootinit.annotation.LoginUser;
import com.madou.springbootinit.common.BaseResponse;
import com.madou.springbootinit.common.ResultUtils;
import com.madou.springbootinit.model.entity.GemallAddress;
import com.madou.springbootinit.service.GemallAddressService;
import com.madou.springbootinit.service.GemallRegionService;
import com.madou.springbootinit.utils.BeanUtil;
import com.madou.springbootinit.utils.RegexUtil;
import com.madou.springbootinit.utils.ResponseUtil;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户收货地址服务
 */
@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

	@GetMapping("/test")
	public BaseResponse<String> getTest(@LoginUser Integer userId,String str){
		return ResultUtils.success(str+userId);
	}
}
