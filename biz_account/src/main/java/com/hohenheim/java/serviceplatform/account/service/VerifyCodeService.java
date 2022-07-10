package com.hohenheim.java.serviceplatform.account.service;

import cn.hutool.core.util.RandomUtil;
import com.hohenheim.java.serviceplatform.account.exception.AccountAssert;
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

    /**
     * 发送注册验证码
     * @
     */
    public boolean sendRegisterCode(String account) {
        AccountAssert.isEmail(account);

        //生成验证码
        int verifyCode = RandomUtil.randomInt(100000, 1000000);
        String content = "您的注册验证码为：" + verifyCode;

        SimpleMailParams mailParams = SimpleMailParams.newBuilder()
                .to(account)
                .title("注册验证码")
                .content(content)
                .build();

        return mMailService.sendSimpleMail(mailParams);
    }
}