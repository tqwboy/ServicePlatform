package com.hohenheim.java.serviceplatform.usersys.define;

import com.hohenheim.java.serviceplatform.common.define.IResultCode;

/**
 * @author Hohenheim
 * @date 2020/8/9
 * @description 请求结果代码
 * 800~1999：用户系统相关
 * 2000~2999 漫画系统相关
 */
public enum ResultCodes implements IResultCode {
	/**
	 * 1000~1999 用户系统
	 */

	/* 登录、注册相关信息 */
	PHONE_FORMAT_ERROR(1000, "请输入正确手机号码"),
	EMAIL_FORMAT_ERROR(1001, "请输入正确邮箱地址"),
	REGISTER_VERIFICATION_CODE_SAVE_FAIL(1002, "验证码获取失败，请稍后再次尝试"),
	REGISTER_VERIFICATION_CODE_SEND_BUSY(1003, "验证码已发送，请注意您的手机短信"),
	REGISTER_VERIFICATION_CODE_SEND_FAIL(1004, "验证码发送失败"),
	NO_REGISTER_METHOD(1005, "非法注册类型"),

	PHONE_HAS_BEEN_REGISTERED(1005, "该手机号已存在"),
	EMAIL_HAS_BEEN_REGISTERED(1005, "该邮箱已存在"),
	NAME_HAS_BEEN_REGISTERED(1005, "该用户名已存在"),

	VERIFICATION_CODE_MUST_NOT_EMPTY(1006, "请输入验证码"),
	PWD_MUST_NOT_EMPTY(1007, "请输入密码"),
	VERIFICATION_CODE_IS_WRONG(1008, "验证码错误"),
	TOKEN_SING_FAIL(1009, "TOKEN签发失败"),

	REGISTER_FAIL(1019, "注册失败"),

	LOGIN_FAIL(1020, "登录失败"),
	NO_LOGIN_METHOD(1021, "非法登录类型"),
	NO_THIRD_PLATFORM(1022, "不支持该登录方式"),

	NAME_PWD_MUST_NOT_EMPTY(1023, "用户名和密码不能为空"),
	EMAIL_PWD_MUST_NOT_EMPTY(1024, "邮箱和密码不能为空"),
	PHONE_PWD_MUST_NOT_EMPTY(1025, "手机号码和密码不能为空"),
	USER_DOES_NOT_EXIST(1026, "用户不存在"),
	ACCOUNT_OR_PWD_ERROR(1027, "用户名或密码错误"),

	/* Token检验 */
	TOKEN_SIGN_EXCEPTION(1100,"非法登录凭证"),
	TOKEN_EXPIRED(1101,"登录凭证已失效"),
	TOKEN_REPLACE(1102,"您的账号已在别的地方登录"),
	TOKEN_KICK_OUT(1103,"您的账号已被强制下线"),

	/* 用户信息管理相关 */
	CHANGE_USER_STATE_FAIL(1110, "修改用户状态失败"),

	;

	private int code;
	private String msg;

	ResultCodes(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public Integer getCode() {
		return this.code;
	}
}
