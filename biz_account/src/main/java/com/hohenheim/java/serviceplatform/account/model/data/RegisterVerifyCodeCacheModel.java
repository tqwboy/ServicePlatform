package com.hohenheim.java.serviceplatform.account.model.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description 注册验证码缓存数据
 */
@Data
@NoArgsConstructor
public class RegisterVerifyCodeCacheModel {
    /** 用户账号 */
    private String account;

    /** 验证码失效时间 */
    private LocalDateTime expireTime;

    private RegisterVerifyCodeCacheModel(Builder builder) {
        setAccount(builder.account);
        setExpireTime(builder.expireTime);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String account;
        private LocalDateTime expireTime;

        private Builder() {
        }

        public Builder account(String account) {
            this.account = account;
            return this;
        }

        public Builder expireTime(LocalDateTime expireTime) {
            this.expireTime = expireTime;
            return this;
        }

        public RegisterVerifyCodeCacheModel build() {
            return new RegisterVerifyCodeCacheModel(this);
        }
    }
}