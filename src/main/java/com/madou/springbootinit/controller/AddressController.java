package com.madou.springbootinit.controller;

import com.madou.springbootinit.annotation.LoginUser;
import com.madou.springbootinit.model.entity.GemallAddress;
import com.madou.springbootinit.service.GemallAddressService;
import com.madou.springbootinit.service.GemallRegionService;
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
@RequestMapping("/address")
@Validated
public class AddressController{

	@Resource
	private GemallAddressService addressService;

	@Resource
	private GemallRegionService regionService;


	/**
	 * 用户收货地址列表
	 *
	 * @param userId 用户ID
	 * @return 收货地址列表
	 */
	@GetMapping("/list")
	public Object list(@LoginUser Integer userId) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		List<GemallAddress> addressList = addressService.queryByUserId(userId);
		return ResponseUtil.okList(addressList);
	}

	/**
	 * 收货地址详情
	 *
	 * @param userId 用户ID
	 * @param id     收货地址ID
	 * @return 收货地址详情
	 */
	@GetMapping("/detail")
	public Object detail(@LoginUser Integer userId, @NotNull Integer id) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		GemallAddress address = addressService.queryByIdInfo(userId, id);
		if (address == null) {
			return ResponseUtil.badArgumentValue();
		}
		return ResponseUtil.ok(address);
	}

	private Object validate(GemallAddress address) {
		String name = address.getName();
		if (StringUtils.isEmpty(name)) {
			return ResponseUtil.badArgument();
		}

		// 测试收货手机号码是否正确
		String mobile = address.getTel();
		if (StringUtils.isEmpty(mobile)) {
			return ResponseUtil.badArgument();
		}
		if (!RegexUtil.isMobileSimple(mobile)) {
			return ResponseUtil.badArgument();
		}

		String province = address.getProvince();
		if (StringUtils.isEmpty(province)) {
			return ResponseUtil.badArgument();
		}

		String city = address.getCity();
		if (StringUtils.isEmpty(city)) {
			return ResponseUtil.badArgument();
		}

		String county = address.getCounty();
		if (StringUtils.isEmpty(county)) {
			return ResponseUtil.badArgument();
		}


		String areaCode = address.getAreaCode();
		if (StringUtils.isEmpty(areaCode)) {
			return ResponseUtil.badArgument();
		}

		String detailedAddress = address.getAddressDetail();
		if (StringUtils.isEmpty(detailedAddress)) {
			return ResponseUtil.badArgument();
		}

		Integer isDefault = address.getIsDefault();
		if (isDefault == null) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	/**
	 * 添加或更新收货地址
	 *
	 * @param userId  用户ID
	 * @param address 用户收货地址
	 * @return 添加或更新操作结果
	 */
	@PostMapping("/save")
	public Object save(@LoginUser Integer userId, @RequestBody GemallAddress address) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Object error = validate(address);
		if (error != null) {
			return error;
		}

		if (address.getId() == null || address.getId().equals(0)) {
			if (address.getIsDefault() == 1) {
				// 重置其他收货地址的默认选项
				addressService.resetDefault(userId);
			}
			address.setId(null);
			address.setUserId(userId);
			addressService.add(address);
		} else {
			GemallAddress gemallAddress = addressService.queryByIdInfo(userId, address.getId());
			if (gemallAddress == null) {
				return ResponseUtil.badArgumentValue();
			}
			if (address.getIsDefault()==1) {
				// 重置其他收货地址的默认选项
				addressService.resetDefault(userId);
			}
			address.setUserId(userId);
			addressService.updateById(address);
		}
		return ResponseUtil.ok(address.getId());
	}

	/**
	 * 删除收货地址
	 *
	 * @param userId  用户ID
	 * @param address 用户收货地址，{ id: xxx }
	 * @return 删除操作结果
	 */
	@PostMapping("/delete")
	public Object delete(@LoginUser Integer userId, @RequestBody GemallAddress address) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Integer id = address.getId();
		if (id == null) {
			return ResponseUtil.badArgument();
		}
		GemallAddress GemallAddress = addressService.queryByIdInfo(userId, id);
		if (GemallAddress == null) {
			return ResponseUtil.badArgumentValue();
		}
		addressService.removeById(id);
		return ResponseUtil.ok();
	}
}
