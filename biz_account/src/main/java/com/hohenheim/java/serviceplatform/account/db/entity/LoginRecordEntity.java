package com.hohenheim.java.serviceplatform.account.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录记录
 * </p>
 *
 * @author Hohenheim
 * @since 2022-03-30
 */
@Data
@TableName("login_record")
public class LoginRecordEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2767007904579248448L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 登录IP
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 登录设备信息
     */
    @TableField("login_device")
    private String loginDevice;

    /**
     * 登录token
     */
    @TableField("login_token")
    private String loginToken;

    /**
     * 0：账号密码；1：微信；2：QQ；3：新浪微博
     */
    @TableField("login_type")
    private Integer loginType;

    /**
     * 登录时间
     */
    @TableField("login_time")
    private LocalDateTime loginTime;
}
