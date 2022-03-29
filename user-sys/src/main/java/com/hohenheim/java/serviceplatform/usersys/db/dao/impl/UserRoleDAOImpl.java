package com.hohenheim.java.serviceplatform.usersys.db.dao.impl;

import com.hohenheim.java.serviceplatform.usersys.db.entity.UserRoleEntity;
import com.hohenheim.java.serviceplatform.usersys.db.mapper.UserRoleMapper;
import com.hohenheim.java.serviceplatform.usersys.db.dao.UserRoleDAO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
