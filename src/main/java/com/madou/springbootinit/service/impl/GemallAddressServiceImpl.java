package com.madou.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.madou.springbootinit.common.ErrorCode;
import com.madou.springbootinit.constant.CommonConstant;
import com.madou.springbootinit.exception.BusinessException;
import com.madou.springbootinit.model.dto.adminAddress.GemallAddressQueryRequest;
import com.madou.springbootinit.model.dto.gamallUser.GemallUserQueryRequest;
import com.madou.springbootinit.model.entity.GemallAd;
import com.madou.springbootinit.model.entity.GemallAddress;
import com.madou.springbootinit.model.entity.GemallUser;
import com.madou.springbootinit.model.vo.GemallAddressVO;
import com.madou.springbootinit.model.vo.GemallUserVO;
import com.madou.springbootinit.service.GemallAddressService;
import com.madou.springbootinit.mapper.GemallAddressMapper;
import com.madou.springbootinit.utils.SqlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MA_dou
 * @description 针对表【gemall_address(收货地址表)】的数据库操作Service实现
 * @createDate 2023-12-01 23:40:25
 */
@Service
public class GemallAddressServiceImpl extends ServiceImpl<GemallAddressMapper, GemallAddress>
        implements GemallAddressService {

    @Override
    public List<GemallAddress> queryByUserId(Integer userId) {
        QueryWrapper<GemallAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.list(queryWrapper);
    }

    @Override
    public GemallAddress queryByIdInfo(Integer userId, Integer id) {
        QueryWrapper<GemallAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("id", id);
        return this.getOne(queryWrapper);
    }

    @Override
    public void resetDefault(Integer userId) {
        QueryWrapper<GemallAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        GemallAddress address = new GemallAddress();
        address.setIsDefault(false);
        address.setUpdateTime(LocalDateTime.now());
        this.update(address, queryWrapper);
    }

    @Override
    public Boolean add(GemallAddress address) {
        address.setAddTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        return this.save(address);
    }

    @Override
    public GemallAddress query(Integer userId, Integer addressId) {
        QueryWrapper<GemallAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("id", addressId);
        return this.getOne(queryWrapper);

    }

    @Override
    public GemallAddress findDefault(Integer userId) {
        QueryWrapper<GemallAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("is_default", 1);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<GemallAddress> querySelective(Integer userId, String name, Integer page, Integer limit, String sort, String order) {

        QueryWrapper<GemallAddress> queryWrapper = new QueryWrapper<>();
        if (userId != null) {
            queryWrapper.eq("user_id",userId);
        }

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name",name);
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            if (order.equals(CommonConstant.SORT_ORDER_DESC)) {
                queryWrapper.orderByDesc(sort);
            } else {
                queryWrapper.orderByAsc(sort);
            }
        }
        Page<GemallAddress> page1 = new Page<>(page, limit);
        IPage<GemallAddress> iPage = this.page(page1, queryWrapper);
        return iPage.getRecords();
    }

    @Override
    public Page<GemallAddressVO> getList(GemallAddressQueryRequest queryRequest) {
        long current = queryRequest.getCurrent();
        long size = queryRequest.getPageSize();

        Page<GemallAddress> page = page(new Page<>(current, size),
                this.getQueryWrapper(queryRequest));
        Page<GemallAddressVO> userVOPage = new Page<>(current, size, page.getTotal());
        List<GemallAddressVO> userVO = getUserVO(page.getRecords());
        userVOPage.setRecords(userVO);
        return  userVOPage;
    }
    public GemallAddressVO getUserVO(GemallAddress address) {
        if (address == null) {
            return null;
        }
        GemallAddressVO addressVO = new GemallAddressVO();
        BeanUtils.copyProperties(address, addressVO);
        return addressVO;
    }
    /**
     * 用户脱敏
     * @param records
     * @return
     */
    private List<GemallAddressVO> getUserVO(List<GemallAddress> records) {
        if (CollectionUtils.isEmpty(records)) {
            return new ArrayList<>();
        }
        return records.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    public QueryWrapper<GemallAddress> getQueryWrapper(GemallAddressQueryRequest queryRequest) {

        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Integer id = queryRequest.getId();
        String userName = queryRequest.getUsername();
        String name = queryRequest.getName();
        String province = queryRequest.getProvince();
        String city = queryRequest.getCity();
        String county = queryRequest.getCounty();
        String tel = queryRequest.getTel();
        Boolean isDefault = queryRequest.getIsDefault();
        String sortField = queryRequest.getSortField();
        String sortOrder = queryRequest.getSortOrder();

        QueryWrapper<GemallAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(isDefault!=null, "is_default", isDefault);
        queryWrapper.like(org.apache.commons.lang3.StringUtils.isNotBlank(name), "name", name);
        queryWrapper.like(org.apache.commons.lang3.StringUtils.isNotBlank(province), "province", province);
        queryWrapper.like(org.apache.commons.lang3.StringUtils.isNotBlank(city), "city", city);
        queryWrapper.like(org.apache.commons.lang3.StringUtils.isNotBlank(county), "county", county);
        queryWrapper.like(org.apache.commons.lang3.StringUtils.isNotBlank(tel), "tel", tel);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




