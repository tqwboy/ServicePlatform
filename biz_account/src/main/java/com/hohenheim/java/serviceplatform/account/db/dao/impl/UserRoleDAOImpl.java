package com.hohenheim.java.serviceplatform.account.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hohenheim.java.serviceplatform.account.db.dao.UserRoleDAO;
import com.hohenheim.java.serviceplatform.account.db.entity.UserRoleEntity;
import com.hohenheim.java.serviceplatform.account.db.mapper.UserRoleMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户角色信息 服务实现类
 * </p>
 *
 * @author Hohenheim
 * @since 2022-03-30
 */
@Repository
public class UserRoleDAOImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleDAO {

}
