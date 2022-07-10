package com.hohenheim.java.serviceplatform.account.aop;

import com.hohenheim.java.serviceplatform.account.aop.anno.RegisterVerifyCodeCacheKeyAnno;
import com.hohenheim.java.serviceplatform.core.cache.redis.RedisOpsConfig;
import com.hohenheim.java.serviceplatform.core.utils.AopUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Hohenheim
 * @date 2022/7/10
 * @description 注册码缓存 KEY 拼接切面
 */
@Component
@Aspect
public class RegisterVerifyCodeCacheKeyAop {
    @Autowired
    private RedisOpsConfig mRedisOpsConfig;;

    @Pointcut("@annotation(com.hohenheim.java.serviceplatform.account.aop.anno.RegisterVerifyCodeCacheKeyAnno)")
    public void pointCut() {
    }

    @Around(value = "pointCut() && @annotation(keyAnno)")
    public Object cacheKeyOps(ProceedingJoinPoint pjp, RegisterVerifyCodeCacheKeyAnno keyAnno) throws Throwable {
        Object[] args = pjp.getArgs();
        Object originKey = AopUtils.getValueByName(args, (CodeSignature) pjp.getSignature(), keyAnno.keyName());
        if(null != originKey) {
            String key = mRedisOpsConfig.getKeyPrefix() + originKey;
            for (int i=0; i<args.length; i++) {
                if(args[i].equals(originKey)) {
                    args[i] = key;
                    break;
                }
            }
        }

        return pjp.proceed(args);
    }
}
