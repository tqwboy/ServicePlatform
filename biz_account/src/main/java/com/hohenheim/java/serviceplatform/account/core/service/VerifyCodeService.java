package com.hohenheim.java.serviceplatform.account.core.service;

import cn.hutool.core.util.RandomUtil;
import com.hohenheim.java.serviceplatform.account.cache.redis.ops.RegisterVerifyCodeRedisOps;
import com.hohenheim.java.serviceplatform.account.define.AccountResultCodes;
import com.hohenheim.java.serviceplatform.account.exception.AccountAssert;
import com.hohenheim.java.serviceplatform.account.exception.AccountException;
import com.hohenheim.java.serviceplatform.account.model.data.RegisterVerifyCodeCacheModel;
import com.hohenheim.java.serviceplatform.core.exception.BizAssert;
import com.hohenheim.java.serviceplatform.message.mail.MailService;
import com.hohenheim.java.serviceplatform.message.mail.params.SimpleMailParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description 验证码发送服务
 */
@Service
@Slf4j
public class VerifyCodeService {
    @Autowired
    private MailService mMailService;
    @Autowired
    private MailProperties mMailProperties;

    @Autowired
    private RegisterVerifyCodeRedisOps mCodeRedisOps;

    /**
     * 发送注册验证码
     * @
     */
    public boolean sendRegisterCode(String account) {
        AccountAssert.isEmail(account);

        //保存验证码
        final int maxRetry = 3;
        LocalDateTime expireTime = null;
        String verifyCode = "";
        int retry = 0;
        for(; retry < maxRetry; ++retry) {
            verifyCode = RandomUtil.randomNumbers(6);
            expireTime = mCodeRedisOps.setIfAbsent(verifyCode, account);
            if(null != expireTime) {
                break;
            }
        }
        if(retry >= maxRetry) {
            throw new AccountException(AccountResultCodes.REGISTER_VERIFICATION_CODE_SAVE_FAILURE);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String content = String.format("您的注册验证码为：%s，有效期至 %s", verifyCode, dateTimeFormatter.format(expireTime));

        //发送验证码
        SimpleMailParams mailParams = SimpleMailParams.newBuilder()
                .from(mMailProperties.getUsername())
                .to(account)
                .title("注册验证码")
                .content(content)
                .build();

        CompletableFuture<Boolean> sendResultFuture = mMailService.sendSimpleMail(mailParams);
        boolean sendResult = false;
        try {
            sendResult = sendResultFuture.get(18, TimeUnit.SECONDS);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e) {
            String errorMsg = String.format("发送注册验证码失败，发送人：%s，接收人：%s，主题：%s，内容：%s",
                    mailParams.getFrom(), mailParams.getToString("|"), mailParams.getTitle(), mailParams.getContent());
            log.error(errorMsg, e);

            if(e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }

        if(!sendResult) {
            //删除缓存
            mCodeRedisOps.delCache(account);
        }

        return sendResult;
    }

    /**
     * 获取注册验证码缓存信息，如果验证码过期，抛出 AccountResultCodes.VERIFY_CODE_EXPIRED 异常信息
     * @param verifyCode 验证码
     * @return 缓存数据
     */
    public RegisterVerifyCodeCacheModel getRegisterVerifyData(String verifyCode) {
        RegisterVerifyCodeCacheModel cacheData = mCodeRedisOps.getCache(verifyCode);
        BizAssert.isTrue(null!=cacheData && LocalDateTime.now().isBefore(cacheData.getExpireTime()),
                AccountResultCodes.VERIFY_CODE_EXPIRED, AccountException.class);
        return cacheData;
    }
}