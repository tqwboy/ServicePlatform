package com.hohenheim.java.serviceplatform.core.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;

/**
 * @author Hohenheim
 * @date 2020/2/11
 * @description
 */
public class ServletUtils {
    public static String springGetReqIp() {
        String ip = "";

        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if(null != attributes) {
            ServletRequestAttributes servletAttributes = (ServletRequestAttributes) attributes;
            HttpServletRequest request = servletAttributes.getRequest();
            ip = getReqIp(request);
        }

        return ip;
    }

    public static String getReqIp(HttpServletRequest request) {
        String[] ipArray = getAllReqIp(request);
        return ipArray[0];
    }

    public static String[] getAllReqIp(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            //Proxy-Client-IP：apache 服务代理
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            //X-Real-IP：nginx服务代理
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip)) {
                //根据网卡取本机配置的IP
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ip = inetAddress.getHostAddress();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return ip.split(",");
    }
}