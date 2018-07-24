package com.xulihuazj.pms.utils.net;

import com.xulihuazj.pms.utils.log.LogHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * IP地址
 */
public class IP {

    private Logger logger = LogManager.getLogger(IP.class);

    /**
     * 获取本机IP地址
     *
     * @return
     */
    public static String getLocalIP() {
        String localIp = null;
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
            if (null != inetAddress) {
                localIp = inetAddress.getHostAddress();
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return localIp;
    }

    /**
     * 在使用nginx等反向代理服务器的时候，获取用户IP地址的
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        //获取真实IP
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String[] ips = ip.split(",");
        return ips[0];
    }

    public static String getAliIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (StringUtils.isNotEmpty(ip) && ip.split(",").length > 0) {
            return ip.split(",")[0];
        }
        ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
