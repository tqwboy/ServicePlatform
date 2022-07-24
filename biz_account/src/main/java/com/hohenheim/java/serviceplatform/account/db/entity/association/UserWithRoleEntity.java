package com.hohenheim.java.serviceplatform.account.db.entity.association;

import com.hohenheim.java.serviceplatform.account.db.entity.RoleEntity;
import com.hohenheim.java.serviceplatform.account.db.entity.UserInfoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hohenheim
 * @date 2022/7/24
 * @description 用户角色与基本信息关联实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserWithRoleEntity extends UserInfoEntity {
    private RoleEntity role;
}