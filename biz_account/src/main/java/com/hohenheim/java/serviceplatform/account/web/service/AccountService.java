package com.hohenheim.java.serviceplatform.account.web.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.yitter.contract.IIdGenerator;
import com.hohenheim.java.serviceplatform.account.db.dao.LoginRecordDAO;
import com.hohenheim.java.serviceplatform.account.db.dao.UserExtendDAO;
import com.hohenheim.java.serviceplatform.account.db.dao.UserInfoDAO;
import com.hohenheim.java.serviceplatform.account.db.dao.UserRoleDAO;
import com.hohenheim.java.serviceplatform.account.db.entity.LoginRecordEntity;
import com.hohenheim.java.serviceplatform.account.db.entity.UserExtendEntity;
import com.hohenheim.java.serviceplatform.account.db.entity.UserInfoEntity;
import com.hohenheim.java.serviceplatform.account.db.entity.UserRoleEntity;
import com.hohenheim.java.serviceplatform.account.define.AccountResultCodes;
import com.hohenheim.java.serviceplatform.account.define.AccountStatus;
import com.hohenheim.java.serviceplatform.account.define.LoginType;
import com.hohenheim.java.serviceplatform.account.exception.AccountException;
import com.hohenheim.java.serviceplatform.account.model.data.RegisterVerifyCodeCacheModel;
import com.hohenheim.java.serviceplatform.account.model.web.resp.LoginResp;
import com.hohenheim.java.serviceplatform.core.exception.BizAssert;
import com.hohenheim.java.serviceplatform.core.utils.ServletUtils;
import com.hohenheim.java.serviceplatform.core.utils.SimpleJacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description
 */
@Service
@Slf4j
public class AccountService {
    @Autowired
    private UserInfoDAO mUserInfoDAO;
    @Autowired
    private LoginRecordDAO mLoginRecordDAO;
    @Autowired
    private UserRoleDAO mUserRoleDAO;
    @Autowired
    private UserExtendDAO mUserExtendDAO;

    @Autowired
    @Lazy
    private AccountService mSelfService;
    @Autowired
    private VerifyCodeService mVerifyCodeService;
    @Autowired
    private UserManagerService mUserManagerService;

    @Resource(name = "userIdGenerator")
    private IIdGenerator mUserIdGenerator;

    /**
     * 邮箱注册
     * @param account 账号
     * @param pwd 密码
     * @return 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public Long register(String account, String pwd) {
        // 检查邮箱或者手机号码是否已被注册
        UserInfoEntity userInfo = mUserManagerService.getUserInfoByAccount(account);
        BizAssert.isNull(userInfo, AccountResultCodes.USER_EXIST, AccountException.class);

        //创建用户信息
        userInfo = mSelfService.createAccount(account, pwd, false);

        // 发送注册验证码
        if(!mVerifyCodeService.sendRegisterCode(account)) {
            throw new AccountException(AccountResultCodes.SEND_VERIFY_CODE_FAILURE);
        }

        return userInfo.getUserId();
    }

    /**
     * 创建用户信息
     * @param account 账号
     * @param pwd 密码
     * @param activeUser 是否激活用户
     * @return 用户ID
     */
    @Transactional(rollbackFor = Exception.class)
    public UserInfoEntity createAccount(String account, String pwd, boolean activeUser) {
        // 创建用户信息
        String salt = RandomUtil.randomString(8);
        Long userId = mUserIdGenerator.newLong();

        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setUserId(userId);
        userInfo.setPwd(DigestUtil.sha1Hex(pwd + salt));
        userInfo.setSalt(salt);
        userInfo.setCreateTime(LocalDateTime.now());

        if(activeUser) {
            userInfo.setStatus(AccountStatus.ACTIVE.getCode());
        }
        else {
            userInfo.setStatus(AccountStatus.NOT_VERIFY.getCode());
        }

        if(Validator.isEmail(account)) {
            userInfo.setUserName(userId.toString());
            userInfo.setEmail(account);
            userInfo.setMobile(userId.toString());
        }
        else {
            userInfo.setUserName(account);
            userInfo.setMobile(userId.toString());
            userInfo.setEmail(userId.toString());
        }

        // 保存用户信息
        BizAssert.isTrue(mUserInfoDAO.save(userInfo), AccountResultCodes.REGISTER_FAILURE, AccountException.class);

        //创建用户角色信息
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUserId(userId);
        BizAssert.isTrue(mUserRoleDAO.save(userRole), AccountResultCodes.REGISTER_FAILURE, AccountException.class);

        //创建用户拓展信息
        UserExtendEntity userExtend = new UserExtendEntity();
        userExtend.setUserId(userId);
        BizAssert.isTrue(mUserExtendDAO.save(userExtend), AccountResultCodes.REGISTER_FAILURE, AccountException.class);

        return userInfo;
    }

    /**
     * 用户账户激活
     * @param verifyCode 验证码
     * @param userInfo 用户信息
     * @return 用户ID
     */
    public Long verifyAccount(String verifyCode, UserInfoEntity userInfo) {
        RegisterVerifyCodeCacheModel cacheModel = mVerifyCodeService.getRegisterVerifyData(verifyCode);
        BizAssert.isTrue(null != cacheModel, AccountResultCodes.VERIFY_CODE_EXPIRED, AccountException.class);

        String account;
        if(Validator.isEmail(cacheModel.getAccount())) {
            account = userInfo.getEmail();
        }
        else if(Validator.isMobile(cacheModel.getAccount())) {
            account = userInfo.getMobile();
        }
        else {
            account = userInfo.getUserName();
        }

        BizAssert.isTrue(CharSequenceUtil.equals(account, cacheModel.getAccount()),
                AccountResultCodes.VERIFY_CODE_MISTAKE, AccountException.class);

        //验证通过，修改用户状态
        BizAssert.notNull(userInfo, AccountResultCodes.USER_NOT_EXIST, AccountException.class);

        UserInfoEntity updateEntity = new UserInfoEntity();
        updateEntity.setUserId(userInfo.getUserId());
        updateEntity.setStatus(AccountStatus.ACTIVE.getCode());
        BizAssert.isTrue(mUserManagerService.updateUserInfoById(updateEntity), AccountResultCodes.VERIFY_ACCOUNT_FAILURE,
                AccountException.class);

        return userInfo.getUserId();
    }

    /**
     * 账号、密码登录
     * @param account 账号
     * @param pwd 密码
     * @return 登录结果
     */
    public LoginResp login(String account, String pwd) {
        // 检查账号
        UserInfoEntity userInfo = mUserManagerService.getUserInfoByAccount(account);
        BizAssert.notNull(userInfo, AccountResultCodes.USER_NOT_EXIST, AccountException.class);
        BizAssert.isTrue(userInfo.getStatus() != AccountStatus.LOCKED.getCode() ,
                AccountResultCodes.ACCOUNT_LOCKED, AccountException.class);

        // 检查密码是否正确
        BizAssert.isTrue(userInfo.getPwd().equals(DigestUtil.sha1Hex(pwd + userInfo.getSalt())),
                AccountResultCodes.ACCOUNT_OR_PWD_ERROR, AccountException.class);

        // 登录成功，颁发AccessToken
        StpUtil.login(userInfo.getUserId());
        SaTokenInfo satokenInfo = StpUtil.getTokenInfo();

        // 记录登录时间
        LoginRecordEntity recordEntity = new LoginRecordEntity();
        recordEntity.setUserId(userInfo.getUserId());
        recordEntity.setLoginDevice("");
        recordEntity.setLoginIp(ServletUtils.springGetReqIp());
        recordEntity.setLoginTime(LocalDateTime.now());
        recordEntity.setLoginType(LoginType.LOGIN_TYPE_ACCOUNT.getType());
        recordEntity.setLoginToken(satokenInfo.getTokenValue());
        try {
            mLoginRecordDAO.saveOrUpdate(recordEntity);
        }
        catch (Exception e) {
            log.error("[ServicePlatform] 记录登录日志失败：" + SimpleJacksonUtils.objectToJsonStr(recordEntity), e);
        }

        // 登录成功，返回登录信息
        LoginResp loginResp = BeanUtil.copyProperties(userInfo, LoginResp.class);
        loginResp.setAccessToken(satokenInfo.getTokenValue());

        return loginResp;
    }
}