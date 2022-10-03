package com.hohenheim.java.serviceplatform.account.web.controller;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.crypto.SecureUtil;
import com.hohenheim.java.serviceplatform.account.model.web.params.PlatformsLoginParams;
import com.hohenheim.java.serviceplatform.account.model.web.resp.LoginResp;
import com.hohenheim.java.serviceplatform.account.web.service.PlatformsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Hohenheim
 * @date 2022/9/25
 * @description 第三方平台控制器
 */
@Slf4j
@RestController
@RequestMapping("/platforms")
public class PlatformsController {
    @Autowired
    private PlatformsService mPlatformsService;

    /**
     * 第三方平台
     * @param params 登录参数
     * @return 登录结果响应信息实体
     */
    @PostMapping(value = "/login")
    public LoginResp platformsLogin(@RequestBody @Validated PlatformsLoginParams params) {
        return mPlatformsService.login(params);
    }

    /**
     * 微信平台接入校验
     */
    @GetMapping(value = "/auth/wx/check", produces = MediaType.TEXT_PLAIN_VALUE)
    public String wxCheckReq(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
                             @RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) {
        log.info("[ServicePlatform-SSO] 收到微信平台认证请求");

        if(CharSequenceUtil.hasBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        String token = "serviceplatform__token";
        List<String> signList = Arrays.asList(token, timestamp, nonce);
        Collections.sort(signList);
        StringBuilder signBuilder = new StringBuilder();
        for(String str : signList) {
            signBuilder.append(str);
        }

        String signStr = SecureUtil.sha1().digestHex(signBuilder.toString());
        if(!signStr.equals(signature)) {
            throw new RuntimeException("[ServicePlatform] 验证失败！微信发送的signature：" + signature
                    + "   自己计算得到的signature：" + signStr);
        }

        return echostr;
    }
}