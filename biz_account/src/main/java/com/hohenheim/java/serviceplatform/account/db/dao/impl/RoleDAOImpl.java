package com.hohenheim.java.serviceplatform.account.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hohenheim.java.serviceplatform.account.db.dao.RoleDAO;
import com.hohenheim.java.serviceplatform.account.db.entity.RoleEntity;
import com.hohenheim.java.serviceplatform.account.db.mapper.RoleMapper;
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
