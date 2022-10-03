package com.hohenheim.java.serviceplatform.account.web.service.login;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.yitter.contract.IIdGenerator;
import com.hohenheim.java.serviceplatform.account.db.dao.UserPlatformsDAO;
import com.hohenheim.java.serviceplatform.account.db.entity.UserInfoEntity;
import com.hohenheim.java.serviceplatform.account.db.entity.UserPlatformsEntity;
import com.hohenheim.java.serviceplatform.account.define.AccountResultCodes;
import com.hohenheim.java.serviceplatform.account.define.PlatformsTypes;
import com.hohenheim.java.serviceplatform.account.define.ServiceStaticContast;
import com.hohenheim.java.serviceplatform.account.exception.AccountException;
import com.hohenheim.java.serviceplatform.account.http.api.WxApi;
import com.hohenheim.java.serviceplatform.account.http.api.conf.WxAppletConfig;
import com.hohenheim.java.serviceplatform.account.http.api.model.wx.WxAppletSessionRespModel;
import com.hohenheim.java.serviceplatform.account.model.web.params.PlatformsLoginParams;
import com.hohenheim.java.serviceplatform.account.model.web.resp.LoginResp;
import com.hohenheim.java.serviceplatform.account.web.service.AccountService;
import com.hohenheim.java.serviceplatform.account.web.service.UserManagerService;
import com.hohenheim.java.serviceplatform.core.define.CoreResultCodes;
import com.hohenheim.java.serviceplatform.core.exception.BizAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Hohenheim
 * @date 2022/9/25
 * @description 微信小程序登录服务
 */
@Component("wxAppletLogin")
@Slf4j
public class WxAppletLogin implements IPlatformsLogin {
    @Autowired
    private WxAppletConfig mWxAppletConfig;
    @Autowired
    private WxApi mWxApi;

    @Autowired
    private UserPlatformsDAO mUserPlatformsDAO;

    @Autowired
    private AccountService mAccountService;
    @Autowired
    private UserManagerService mUserManagerService;

    @Resource(name = "generalIdGenerator")
    private IIdGenerator mGeneralIdGenerator;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginResp login(PlatformsLoginParams params) {
        WxAppletSessionRespModel wxAppletSession;
        try {
            //根据授权码，获取UnionID等微信授权信息
            CompletableFuture<WxAppletSessionRespModel> wxAppSessionFuture = mWxApi.getWeChatAppletSession(mWxAppletConfig.getAppId(),
                    mWxAppletConfig.getSecret(), params.getAuthorCode());
            wxAppletSession = wxAppSessionFuture.get(8, TimeUnit.SECONDS);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("[WxLogin] 获取微信Session信息失败", e);
            throw new AccountException(CoreResultCodes.REQ_OPT_FAILURE);
        }

        // 判断微信号是否已被绑定
        List<UserPlatformsEntity> userPlatformsList = mUserPlatformsDAO.selectByUnionId(wxAppletSession.getUnionId());
        UserInfoEntity loginUser;

        if(userPlatformsList.size() == 0) {
            //未被绑定，创建用户信息绑定微信号
            UserInfoEntity userInfo = mAccountService.createAccount("wx" + wxAppletSession.getUnionId(),
                    ServiceStaticContast.DEFAULT_PWD, true);

            //创建用户第三方平台信息
            UserPlatformsEntity userPlatformsEntity = new UserPlatformsEntity();
            userPlatformsEntity.setId(mGeneralIdGenerator.newLong())
                    .setUserId(userInfo.getUserId())
                    .setPlatformId(PlatformsTypes.WX.getType())
                    .setUnionId(wxAppletSession.getUnionId())
                    .setOpenId(wxAppletSession.getOpenId())
                    .setKey(wxAppletSession.getSessionKey());

            BizAssert.isTrue(mUserPlatformsDAO.save(userPlatformsEntity), AccountResultCodes.LOGIN_FAIL);
            loginUser = userInfo;
        }
        else {
            //已被绑定，获取用户信息
            UserPlatformsEntity bindInfo = null;
            for(UserPlatformsEntity userPlatformsEntity : userPlatformsList) {
                if(userPlatformsEntity.getOpenId().equals(wxAppletSession.getOpenId())) {
                    bindInfo = userPlatformsEntity;
                    break;
                }
            }

            Long userId;
            if(null == bindInfo) {
                UserPlatformsEntity userPlatformsEntity = userPlatformsList.get(0);

                //未绑定平台应用用户唯一标识，添加用户平台应用信息
                UserPlatformsEntity saveData = new UserPlatformsEntity();
                saveData.setId(mGeneralIdGenerator.newLong());
                saveData.setUserId(userPlatformsEntity.getUserId());
                saveData.setPlatformId(PlatformsTypes.WX.getType());
                saveData.setOpenId(wxAppletSession.getOpenId());
                saveData.setUnionId(wxAppletSession.getUnionId());
                saveData.setKey(wxAppletSession.getSessionKey());
                BizAssert.isTrue(mUserPlatformsDAO.save(saveData), AccountResultCodes.LOGIN_FAIL);

                userId = userPlatformsEntity.getUserId();
            }
            else {
                userId = bindInfo.getUserId();
            }

            loginUser = mUserManagerService.getUserRoleInfo(userId);
        }

        //组装登录信息
        SaTokenInfo satokenInfo = StpUtil.getTokenInfo();
        LoginResp loginResp = BeanUtil.copyProperties(loginUser, LoginResp.class);
        loginResp.setAccessToken(satokenInfo.getTokenValue());

        return loginResp;
    }
}
