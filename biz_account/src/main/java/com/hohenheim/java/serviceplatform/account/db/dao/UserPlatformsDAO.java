package com.hohenheim.java.serviceplatform.account.db.dao;

import com.hohenheim.java.serviceplatform.account.db.entity.UserPlatformsEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户与第三方平台关系表 服务类
 * </p>
 *
 * @author Hohenheim
 * @since 2022-10-03
 */
public interface UserPlatformsDAO extends IService<UserPlatformsEntity> {
    /**
     * 查询平台信息
     * @param unionId 平台唯一标识
     * @return 用户关联平台信息列表
     */
    List<UserPlatformsEntity> selectByUnionId(String unionId);
}
