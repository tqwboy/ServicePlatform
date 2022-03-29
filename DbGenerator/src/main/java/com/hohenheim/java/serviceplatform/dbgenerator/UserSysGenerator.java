package com.hohenheim.java.serviceplatform.dbgenerator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import java.util.Collections;

/**
 * @author Hohenheim
 * @date 2022/3/20
 * @description
 */
public class UserSysGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        String encKey = args[0];

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(encKey);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_128");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        String dbUrl = encryptor.decrypt("zBzbVkxmWezvkgFXQbB7+cipYL1J8uQHpYBA8UiAxRiSnzg7n3oHl9W1m9QMO9mT/kHOTPehKZQnSexQF7kGX4pjDrjyVjcSFYcdjKqX21ZPSlGJIxbjSGOrhdhjmjmpSj8RJN0sGZwYcEPpIUSqL0DpnaneeFgm4qMyJke4nD1rDjTEN60p7r677+JrWxEWx1K03CnADzf6VguGzaAG0bBII20msuCuQW6VkhmWEezxbzcp/BQ62e8WBr7Ymix6");
        String dbUser = encryptor.decrypt("jlFfDqIgZ3PTEbv9TcXqp+8dTYjfNomiKmG4Off9/Hk4Z4ocrV8MS1X9WghzTRTH");
        String dbPwd = encryptor.decrypt("ACypfZhg5boYv1CggNcWX3EsGlI21w7fiviKT0Y7ukHSYETI+/Wazs/rFAEUOXb2");
        FastAutoGenerator.create(dbUrl, dbUser, dbPwd)
                .globalConfig(builder -> {
                    builder.author("Hohenheim") // 设置作者
                            .outputDir(projectPath + "/user-sys/src/main/java")
                            .disableOpenDir()
                            .dateType(DateType.TIME_PACK);
                })
                .packageConfig(builder -> {
                    builder.parent("com.hohenheim.java.serviceplatform.usersys") // 设置父包名
                            .moduleName("db")
                            .mapper("mapper")
                            .entity("entity")
                            .mapper("mapper")
                            .service("dao")
                            .serviceImpl("dao.impl")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/user-sys/src/main/resources/db/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.enableCapitalMode()
                            .entityBuilder()
                            .formatFileName("%sEntity")
                            .idType(IdType.INPUT)
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .mapperBuilder()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .serviceBuilder()
                            .formatServiceFileName("%sDAO")
                            .formatServiceImplFileName("%sDAOImpl");

                    builder.addInclude("login_record", "role", "user_extend", "user_info", "user_role");
                })
                .templateConfig(builder -> {
                    builder.controller("");  //不生成Controller
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}