package com.hohenheim.java.serviceplatform.account.db.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hohenheim.java.serviceplatform.account.db.entity.UserPlatformsEntity;
import com.hohenheim.java.serviceplatform.account.db.mapper.UserPlatformsMapper;
import com.hohenheim.java.serviceplatform.account.db.dao.UserPlatformsDAO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户与第三方平台关系表 服务实现类
 * </p>
 *
 * @author Hohenheim
 * @since 2022-10-03
 */
@Repository
public class UserPlatformsDAOImpl extends ServiceImpl<UserPlatformsMapper, UserPlatformsEntity> implements UserPlatformsDAO {
    @Override
    public List<UserPlatformsEntity> selectByUnionId(String unionId) {
        LambdaQueryWrapper<UserPlatformsEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPlatformsEntity::getUnionId, unionId);
        return list(queryWrapper);
    }
}
