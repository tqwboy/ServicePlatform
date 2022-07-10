package com.hohenheim.java.serviceplatform.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hohenheim
 * @date 2022/5/29
 * @description
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    /**
     * 用户注册接口
     */
    @RequestMapping("/register")
    public void register() {
        
    }

    /**
     * 用户登录接口
     */
    @RequestMapping("/login")
    public void login() {

    }
}