package com.madou.springbootinit.model.dto.adminUser;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求
 *
 * @author <a href="https://github.com/limadou">程序员鱼皮</a>
 * @from <a href="https://madou.icu">编程导航知识星球</a>
 */
@Data
public class AdminUserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String username;

    private String password;
}
