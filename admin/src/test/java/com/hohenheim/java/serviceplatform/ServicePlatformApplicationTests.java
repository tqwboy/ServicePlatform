package com.hohenheim.java.serviceplatform;

import com.hohenheim.java.serviceplatform.account.db.dao.UserInfoDAO;
import com.hohenheim.java.serviceplatform.message.mail.MailService;
import com.hohenheim.java.serviceplatform.message.mail.params.SimpleMailParams;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ServicePlatformApplicationTests {
    @Autowired
    private StringEncryptor mEncryptor;
    @Autowired
    private MailService mMailService;
    @Autowired
    private UserInfoDAO mUserInfoDAO;

    @Test
    void encryptor() {
        //JetCache lettuce url 加密
        String redisUrl = mEncryptor.encrypt("redis://密码@127.0.0.1:6379/1?timeout=3000");
        System.out.println("密文:" + redisUrl);

        Assertions.assertEquals("", "");
    }

    @Test
    void senMailTest() {
        mUserInfoDAO.getUserWithRoleInfo(1L);

//        SimpleMailParams params = SimpleMailParams.newBuilder()
//                .to("a@b.com")
//                .title("测试")
//                .content("SpringBoot 测试邮件")
//                .build();
//
//        Assertions.assertTrue(mMailService.sendSimpleMail(params));
    }
}