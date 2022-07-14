package com.hohenheim.java.serviceplatform.account.service;

import cn.hutool.core.util.RandomUtil;
import com.hohenheim.java.serviceplatform.account.cache.redis.ops.RegisterVerifyCodeRedisOps;
import com.hohenheim.java.serviceplatform.account.exception.AccountAssert;
import com.hohenheim.java.serviceplatform.core.define.CoreResultCodes;
import com.hohenheim.java.serviceplatform.core.exception.BizAssert;
import com.hohenheim.java.serviceplatform.message.mail.MailService;
import com.hohenheim.java.serviceplatform.message.mail.params.SimpleMailParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description 验证码发送服务
 */
@Service
public class VerifyCodeService {
    @Autowired
    private MailService mMailService;
    @Autowired
    private RegisterVerifyCodeRedisOps mCodeRedisOps;

    /**
     * 发送注册验证码
     * @
     */
    public boolean sendRegisterCode(String account) {
        boolean sendResult = true;
        AccountAssert.isEmail(account);

        //生成验证码
        String verifyCode = RandomUtil.randomString("1234567890", 6);
        BizAssert.notNull(mCodeRedisOps.saveCache(account, verifyCode), CoreResultCodes.REQ_OPT_FAILURE.getMsg());
        if(null != mCodeRedisOps.saveCache(account, verifyCode)) {
            String content = String.format("您的注册验证码为：%s，有效期5分钟", verifyCode);

            SimpleMailParams mailParams = SimpleMailParams.newBuilder()
                    .to(account)
                    .title("注册验证码")
                    .content(content)
                    .build();

            sendResult = mMailService.sendSimpleMail(mailParams);
            if(!sendResult) {
                //删除缓存
                mCodeRedisOps.delCache(account);
            }
        }

        return sendResult;
    }
}