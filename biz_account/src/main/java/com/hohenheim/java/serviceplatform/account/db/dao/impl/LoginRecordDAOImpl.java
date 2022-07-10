package com.hohenheim.java.serviceplatform.account.db.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hohenheim.java.serviceplatform.account.db.dao.LoginRecordDAO;
import com.hohenheim.java.serviceplatform.account.db.entity.LoginRecordEntity;
import com.hohenheim.java.serviceplatform.account.db.mapper.LoginRecordMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 登录记录 服务实现类
 * </p>
 *
 * @author Hohenheim
 * @since 2022-03-30
 */
@Repository
public class LoginRecordDAOImpl extends ServiceImpl<LoginRecordMapper, LoginRecordEntity> implements LoginRecordDAO {

}
