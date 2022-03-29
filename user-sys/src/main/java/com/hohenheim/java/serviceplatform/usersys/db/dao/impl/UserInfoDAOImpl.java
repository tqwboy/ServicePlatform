package com.hohenheim.java.serviceplatform.usersys.db.dao.impl;

import com.hohenheim.java.serviceplatform.usersys.db.entity.UserInfoEntity;
import com.hohenheim.java.serviceplatform.usersys.db.mapper.UserInfoMapper;
import com.hohenheim.java.serviceplatform.usersys.db.dao.UserInfoDAO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
