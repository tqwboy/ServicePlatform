package com.hohenheim.java.serviceplatform.account.db.dao.impl;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hohenheim.java.serviceplatform.account.db.dao.UserInfoDAO;
import com.hohenheim.java.serviceplatform.account.db.entity.UserInfoEntity;
import com.hohenheim.java.serviceplatform.account.db.entity.association.UserWithRoleEntity;
import com.hohenheim.java.serviceplatform.account.db.mapper.UserInfoMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Hohenheim
 * @since 2022-03-30
 */
@Repository
public class UserInfoDAOImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoDAO {
    @Override
    public UserInfoEntity getUserInfoByAccount(String account) {
        LambdaQueryWrapper<UserInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(Validator.isEmail(account)) {
            queryWrapper.eq(UserInfoEntity::getEmail, account);
        }
        else if(Validator.isMobile(account)){
            queryWrapper.eq(UserInfoEntity::getMobile, account);
        }
        else {
            queryWrapper.eq(UserInfoEntity::getUserName, account);
        }

        return getOne(queryWrapper);
    }

    @Override
    public UserWithRoleEntity getUserWithRoleInfo(Long userId) {
        return getBaseMapper().getUserWithRoleInfo(userId);
    }
}
