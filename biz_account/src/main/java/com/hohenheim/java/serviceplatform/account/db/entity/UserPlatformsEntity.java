package com.hohenheim.java.serviceplatform.account.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户与第三方平台关系表
 * </p>
 *
 * @author Hohenheim
 * @since 2022-10-03
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_platforms")
public class UserPlatformsEntity implements Serializable {

    private static final long serialVersionUID = 7387394457265338697L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 平台ID
     */
    @TableField("platform_id")
    private Integer platformId;

    /**
     * 平台用户唯一标识
     */
    @TableField("union_id")
    private String unionId;

    /**
     * 平台子账号用户唯一ID
     */
    @TableField("open_id")
    private String openId;

    /**
     * 平台加密数据解密秘钥，部分平台需要，例如微信小程序
     */
    @TableField("key")
    private String key;

    /**
     * 访问令牌
     */
    @TableField("access_token")
    private String accessToken;

    /**
     * 刷新令牌
     */
    @TableField("refresh_token")
    private String refreshToken;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}
