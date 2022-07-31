package com.hohenheim.java.serviceplatform.web.conf;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hohenheim.java.serviceplatform.account.core.resolver.GetLoginUserInfoResolver;
import com.hohenheim.java.serviceplatform.core.utils.SimpleJacksonUtils;
import com.hohenheim.java.serviceplatform.web.converter.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Hohenheim
 * @date 2018/4/27
 * @description
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	@Autowired
	private GetLoginUserInfoResolver mGetLoginUserInfoResolver;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		JsonConverter jsonDataConvert = new JsonConverter();

		//设置日期格式
		ObjectMapper jsonMapper = SimpleJacksonUtils.getJsonMapper();
		jsonDataConvert.setObjectMapper(jsonMapper);
		converters.add(jsonDataConvert);

		//增加字符串数据转换器
		StringHttpMessageConverter strConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
		converters.add(strConverter);

		super.configureMessageConverters(converters);
	}

	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(mGetLoginUserInfoResolver);
		super.addArgumentResolvers(argumentResolvers);
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		//需要排除鉴权的Swagger路径
		String[] swaggerPath = {"/doc.html/**", "/swagger-ui.html", "/swagger-resources/**", "/v2/**", "/webjars/**", "/images/**"};
		//其他库需要排除鉴权的路径
		String[] thirdPlatformPath = {"/druid/**"};
		//需要排除鉴权的Controller路径
		String[] excludePath = {"/account/register", "/account/login"};

		//注册登录验证与鉴权拦截器
		registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {
			//登录检测
			SaRouter.match("/account/**", StpUtil::checkLogin);

			//角色权限认证
			SaRouter.match("/cm/c/**", () -> StpUtil.checkRoleOr("ROLE_ADMIN", "ROLE_MANAGER"));
			SaRouter.match("/cm/account/**", () -> StpUtil.checkRoleOr("ROLE_ADMIN", "ROLE_MANAGER"));

		})).excludePathPatterns(swaggerPath)
		   .excludePathPatterns(thirdPlatformPath)
		   .excludePathPatterns(excludePath);
	}
}