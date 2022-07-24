package com.hohenheim.java.serviceplatform.account.core.resolver;

import cn.dev33.satoken.stp.StpUtil;
import com.hohenheim.java.serviceplatform.account.core.anno.GetLoginUserId;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Hohenheim
 * @date 2021/1/17
 * @description 从 Token 中获取 UserID
 */
@Component
public class GetLoginUserIdResolver implements HandlerMethodArgumentResolver  {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        boolean support = false;
        if(methodParameter.hasParameterAnnotation(GetLoginUserId.class)
                && methodParameter.getParameterType().isAssignableFrom(GetLoginUserId.class)) {

            support = true;
        }

        return support;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        return StpUtil.getLoginIdAsLong();
    }
}
