package com.hohenheim.java.serviceplatform;

import org.jasypt.encryption.StringEncryptor;
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

    @Test
    void encryptor() {

    }

}
