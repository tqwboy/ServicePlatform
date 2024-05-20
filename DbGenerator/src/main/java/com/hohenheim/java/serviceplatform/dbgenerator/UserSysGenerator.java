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
 * @description 用户系统模块数据库相关文件生成
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

        String dbUrl = encryptor.decrypt("QOYkHwJ5//RrZc/pvzKa9rp7S3643jcE1CDfXWPxqsjjfgSCuoK5N+2AnBPG8eml7orGFMlgKM6R4wjjb0i5sjzV+FYWJLL2te7sfVKsHnRP2dzCHURoUrgPHxHaUPma10l6krfAKo/d4zYp+E807HuMeYkyO6U/bjw2HmeHiFKOsR+LqfPZi1Tlo33mtsJlyEUaGkn68VWAVEibsedngw==");
        String dbUser = encryptor.decrypt("Etxoqvz0BzQz1HgXtJq26MJadXxbrguY3jhVTBI4jUTcXjaYFyXmUIGA1LszniPb");
        String dbPwd = encryptor.decrypt("wBWr6Ggvl8ni1283saz+QQ4CvUHirnvGrmOobxtfn35OkTQZFzEj1EhT67/RCq/K");
        FastAutoGenerator.create(dbUrl, dbUser, dbPwd)
                .globalConfig(builder -> {
                    builder.author("Hohenheim") // 设置作者
                            .outputDir(projectPath + "/biz_account/src/main/java")
                            .disableOpenDir()
                            .dateType(DateType.TIME_PACK);
                })
                .packageConfig(builder -> {
                    builder.parent("com.hohenheim.java.serviceplatform.account") // 设置父包名
                            .moduleName("db")
                            .mapper("mapper")
                            .entity("entity")
                            .service("dao")
                            .serviceImpl("dao.impl")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/biz_account/src/main/resources/db/mapper")); // 设置mapperXml生成路径
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
                            .fileOverride() //覆盖文件，慎用
                            .formatServiceImplFileName("%sDAOImpl");

                    builder.addInclude("user_platforms");
                })
                .templateConfig(builder -> {
                    builder.controller("");  //不生成Controller
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}