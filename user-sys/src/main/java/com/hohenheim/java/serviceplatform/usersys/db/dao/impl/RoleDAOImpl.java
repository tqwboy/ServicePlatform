package com.hohenheim.java.serviceplatform.usersys.db.dao.impl;

import com.hohenheim.java.serviceplatform.usersys.db.entity.RoleEntity;
import com.hohenheim.java.serviceplatform.usersys.db.mapper.RoleMapper;
import com.hohenheim.java.serviceplatform.usersys.db.dao.RoleDAO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色信息 服务实现类
 * </p>
 *
 * @author Hohenheim
 * @since 2022-03-30
 */
@Repository
public class RoleDAOImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleDAO {

}
