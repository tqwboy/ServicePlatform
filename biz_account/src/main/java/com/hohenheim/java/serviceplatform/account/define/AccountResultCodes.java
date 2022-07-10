package com.hohenheim.java.serviceplatform.account.define;

import com.hohenheim.java.serviceplatform.core.define.IResultCode;

/**
 * @author Hohenheim
 * @date 2020/8/9
 * @description 请求结果代码
 * 800~1999：用户系统相关
 * 2000~2999 漫画系统相关
 */
public enum AccountResultCodes implements IResultCode {
	/**
	 * 1000~1999 用户系统
	 */

	/* 登录、注册相关信息 */
	PHONE_FORMAT_ERROR("account_1", "请输入正确手机号码"),
	EMAIL_FORMAT_ERROR("account_2", "请输入正确邮箱地址"),
	REGISTER_VERIFICATION_CODE_SAVE_FAIL("account_3", "验证码获取失败，请稍后再次尝试"),
	REGISTER_VERIFY_CODE_ALREADY_SEND_MAIL("account_4", "验证码已发送，请注意您的邮箱"),
	REGISTER_VERIFICATION_CODE_SEND_FAIL("account_5", "验证码发送失败"),
	NO_REGISTER_METHOD("account_6", "非法注册类型"),

	EMAIL_HAS_BEEN_REGISTERED("account_10", "该邮箱已存在"),
	NAME_HAS_BEEN_REGISTERED("account_11", "该用户名已存在"),

	VERIFICATION_CODE_MUST_NOT_EMPTY("account_12", "请输入验证码"),
	PWD_MUST_NOT_EMPTY("account_13", "请输入密码"),
	VERIFICATION_CODE_IS_WRONG("account_14", "验证码错误"),
	TOKEN_SING_FAIL("account_15", "TOKEN签发失败"),
	REGISTER_FAIL("account_16", "注册失败"),

	LOGIN_FAIL("account_50", "登录失败"),
	NO_LOGIN_METHOD("account_51", "非法登录类型"),
	NO_THIRD_PLATFORM("account_52", "不支持该登录方式"),

	NAME_PWD_MUST_NOT_EMPTY("account_53", "用户名和密码不能为空"),
	EMAIL_PWD_MUST_NOT_EMPTY("account_54", "邮箱和密码不能为空"),
	USER_DOES_NOT_EXIST("account_55", "用户不存在"),
	ACCOUNT_OR_PWD_ERROR("account_56", "用户名或密码错误"),

	/* Token检验 */
	TOKEN_SIGN_EXCEPTION("account_100","非法登录凭证"),
	TOKEN_EXPIRED("account_101","登录凭证已失效"),
	TOKEN_REPLACE("account_102","您的账号已在别的地方登录"),
	TOKEN_KICK_OUT("account_103","您的账号已被强制下线"),

	/* 用户信息管理相关 */
	CHANGE_USER_STATE_FAIL("account_104", "修改用户状态失败"),

	;

	private String code;
	private String msg;

	AccountResultCodes(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public String getCode() {
		return this.code;
	}
}