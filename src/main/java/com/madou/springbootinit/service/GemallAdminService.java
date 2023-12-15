package com.madou.springbootinit.service;

import com.madou.springbootinit.model.entity.GemallAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.madou.springbootinit.model.vo.AdminUserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author MA_dou
* @description 针对表【gemall_admin(管理员表)】的数据库操作Service
* @createDate 2023-12-01 23:40:25
*/
public interface GemallAdminService extends IService<GemallAdmin> {

    /**
     * 管理-查询用户
     * @param username
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    List<GemallAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order);

    /**
     * 管理-新增管理用户
     * @param username
     * @return
     */
    List<GemallAdmin> findAdmin(String username);

    /**
     * 管理用户登录
     * @param username
     * @param password
     * @param request
     * @return
     */
    AdminUserVO userLogin(String username, String password, HttpServletRequest request);
}
