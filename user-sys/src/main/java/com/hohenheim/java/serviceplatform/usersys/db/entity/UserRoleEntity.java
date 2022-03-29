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
 * 用户角色信息
 * </p>
 *
 * @author Hohenheim
 * @since 2022-03-30
 */
@Data
@TableName("user_role")
public class UserRoleEntity implements Serializable {

    private static final long serialVersionUID = 1871483461050207329L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.INPUT)
    private Long roleId;

    @TableField("create_time")
    private LocalDateTime createTime;
}
