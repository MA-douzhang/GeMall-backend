package com.madou.springbootinit.aop;

import com.madou.springbootinit.annotation.LoginUser;
import com.madou.springbootinit.manager.UserTokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Token校验 AOP
 *
 */
@Slf4j
public class LoginUserInterceptor implements HandlerMethodArgumentResolver {
    public static final String LOGIN_TOKEN_KEY = "X-Gemall-Token";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {

        String token = request.getHeader(LOGIN_TOKEN_KEY);
        log.debug("aop切面获取登录信息"+token);
        if (token == null || token.isEmpty()) {
            return null;
        }
        return UserTokenManager.getUserId(token);
    }

}

