package com.hohenheim.java.serviceplatform.account.model.web.resp;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Hohenheim
 * @date 2022/7/24
 * @description 用户信息
 */
@Data
public class UserInfoModel {
    private Long userId;

    private String userName;

    private String email;

    private String mobile;

    private String avatar;

    private Integer sex;

    private LocalDateTime createTime;
}
