package com.hohenheim.java.serviceplatform.account.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色信息
 * </p>
 *
 * @author Hohenheim
 * @since 2022-03-30
 */
@Data
@TableName("role")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 6311005537702521107L;

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.INPUT)
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
}
