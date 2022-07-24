package com.hohenheim.java.serviceplatform.account.core.service;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.Cached;
import com.hohenheim.java.serviceplatform.account.db.dao.UserInfoDAO;
import com.hohenheim.java.serviceplatform.account.db.entity.UserInfoEntity;
import com.hohenheim.java.serviceplatform.account.db.entity.association.UserWithRoleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Hohenheim
 * @date 2022/7/17
 * @description 用户管理服务
 */
@Service
@Slf4j
public class UserManagerService {
    @Autowired
    private UserInfoDAO mUserInfoDAO;

    /**
     * 根据用户ID息，获取用户角色信息与角色信息
     */
    @Cached(area = "account", name = "account:user:info:", expire = 1, timeUnit = TimeUnit.HOURS, key = "#userId")
    @CachePenetrationProtect(timeout = 8)
    public UserWithRoleEntity getUserRoleInfo(Long userId) {
        return mUserInfoDAO.getUserWithRoleInfo(userId);
    }

    /**
     * 根据账号，获取用户信息
     */
    public UserInfoEntity getUserInfoByAccount(String account) {
        return mUserInfoDAO.getUserInfoByAccount(account);
    }

    /**
     * 更新用户信息
     */
    @CacheInvalidate(area = "account", name = "account:user:info:", key = "#userInfo.userId", condition = "#result == true")
    public boolean updateUserInfoById(UserInfoEntity userInfo) {
        return mUserInfoDAO.updateById(userInfo);
    }

}