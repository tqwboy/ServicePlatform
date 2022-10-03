package com.hohenheim.java.serviceplatform.account.web.resolver;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.NumberUtil;
import com.hohenheim.java.serviceplatform.account.web.anno.GetUserInfo;
import com.hohenheim.java.serviceplatform.account.web.service.UserManagerService;
import com.hohenheim.java.serviceplatform.account.db.entity.association.UserWithRoleEntity;
import com.hohenheim.java.serviceplatform.account.define.AccountResultCodes;
import com.hohenheim.java.serviceplatform.account.exception.AccountException;
import com.hohenheim.java.serviceplatform.core.exception.BizAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Hohenheim
 * @date 2022/7/31
 * @description
 */
@Component
public class GetLoginUserInfoResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserManagerService mUserManagerService;
    @Autowired
    private SaTokenConfig mSaTokenConfig;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        boolean support = false;
        if(methodParameter.hasParameterAnnotation(GetUserInfo.class)
                || methodParameter.getParameterType().isAssignableFrom(GetUserInfo.class)) {

            support = true;
        }

        return support;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String token = webRequest.getHeader(mSaTokenConfig.getTokenName());
        BizAssert.notBlank(token, AccountResultCodes.NOT_LOGIN, AccountException.class);

        Object userIdObj = StpUtil.getLoginIdByToken(token);
        BizAssert.notNull(userIdObj, AccountResultCodes.NO_LOGIN_METHOD, AccountException.class);

        Long userId = NumberUtil.parseLong(userIdObj.toString());
        UserWithRoleEntity userWithRole = mUserManagerService.getUserRoleInfo(userId);
        if(parameter.getParameterAnnotation(GetUserInfo.class).userExist()) {
            BizAssert.notNull(userWithRole, AccountResultCodes.USER_NOT_EXIST, AccountException.class);
        }

        return userWithRole;
    }
}
