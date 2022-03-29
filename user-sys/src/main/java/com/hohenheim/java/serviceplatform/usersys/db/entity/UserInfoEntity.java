package com.hohenheim.java.serviceplatform.usersys.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Hohenheim
 * @since 2022-03-30
 */
@Data
@TableName("user_info")
public class UserInfoEntity implements Serializable {

    private static final long serialVersionUID = 9015389113360790151L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    /**
     * 租户ID，预方便未来改造为SaaS多租户系统
     */
    @TableField("tenant_id")
    private Integer tenantId;

    /**
     * 密码
     */
    @TableField("pwd")
    private String pwd;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 电子邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户手机
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 用户头像地址
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 性别：0.保密，1.男，2.女，3.不男不女
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 账户状态，0.未验证；1.正常；2.封禁
     */
    @TableField("status")
    private Integer status;

    /**
     * 是否已删除
     */
    @TableField("deleted")
    private Integer deleted;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
