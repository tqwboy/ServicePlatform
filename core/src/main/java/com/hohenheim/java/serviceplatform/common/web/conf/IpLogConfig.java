package com.hohenheim.java.serviceplatform.common.web.conf;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.hohenheim.java.serviceplatform.common.utils.ServletUtils;

/**
 * @author Hohenheim
 * @date 2020/3/12
 * @description 请求IP获取，提供给logback使用
 */
public class IpLogConfig extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return ServletUtils.springGetReqIp();
    }
}