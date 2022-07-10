package com.hohenheim.java.serviceplatform.account.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hohenheim.java.serviceplatform.account.db.dao.UserInfoDAO;
import com.hohenheim.java.serviceplatform.account.db.entity.UserInfoEntity;
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

}
