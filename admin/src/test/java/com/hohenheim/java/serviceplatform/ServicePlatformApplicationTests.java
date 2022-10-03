package com.hohenheim.java.serviceplatform;

import com.hohenheim.java.serviceplatform.account.web.service.UserManagerService;
import com.hohenheim.java.serviceplatform.account.web.service.VerifyCodeService;
import com.hohenheim.java.serviceplatform.account.db.dao.UserInfoDAO;
import com.hohenheim.java.serviceplatform.account.db.entity.association.UserWithRoleEntity;
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
    private VerifyCodeService mVerifyCodeService;
    @Autowired
    private UserManagerService mUserManagerService;

    @Autowired
    private UserInfoDAO mUserInfoDAO;

    @Test
    void encryptor() {
        //JetCache lettuce url 加密
        String redisUrl = mEncryptor.encrypt("redis://tqw1986216$@192.168.88.5:6379/1?timeout=3000");
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
        UserWithRoleEntity userWithRole = mUserManagerService.getUserRoleInfo(6042150805L);
        Assertions.assertTrue(true);
    }
}