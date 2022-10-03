package com.hohenheim.java.serviceplatform.account.db.dao.impl;

import com.hohenheim.java.serviceplatform.account.db.entity.PlatformsEntity;
import com.hohenheim.java.serviceplatform.account.db.mapper.PlatformsMapper;
import com.hohenheim.java.serviceplatform.account.db.dao.PlatformsDAO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 第三方平台信息表 服务实现类
 * </p>
 *
 * @author Hohenheim
 * @since 2022-09-25
 */
@Repository
public class PlatformsDAOImpl extends ServiceImpl<PlatformsMapper, PlatformsEntity> implements PlatformsDAO {

}
