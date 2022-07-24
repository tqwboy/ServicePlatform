package com.hohenheim.java.serviceplatform.account.db.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hohenheim.java.serviceplatform.account.db.entity.UserInfoEntity;
import com.hohenheim.java.serviceplatform.account.db.entity.association.UserWithRoleEntity;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Hohenheim
 * @since 2022-03-30
 */
public interface UserInfoDAO extends IService<UserInfoEntity> {
    /**
     * 根据邮箱或者手机号码，查询用户信息
     * @param account 用户邮箱或者手机号
     * @return 用户信息
     */
    UserInfoEntity getUserInfoByAccount(String account);

    /**
     * 获取包含用户角色信息的用户信息
     */
    UserWithRoleEntity getUserWithRoleInfo(Long userId);
}
