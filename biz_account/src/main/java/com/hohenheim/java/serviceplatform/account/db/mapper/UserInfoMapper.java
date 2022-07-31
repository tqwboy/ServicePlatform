package com.hohenheim.java.serviceplatform.account.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hohenheim.java.serviceplatform.account.db.entity.UserInfoEntity;
import com.hohenheim.java.serviceplatform.account.db.entity.association.UserWithRoleEntity;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Hohenheim
 * @since 2022-03-30
 */
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {
    /**
     * 取包含用户角色信息的用户信息
     * @param userId 用户id
     */
    UserWithRoleEntity getUserWithRoleInfo(@Param("userId") Long userId);
}
