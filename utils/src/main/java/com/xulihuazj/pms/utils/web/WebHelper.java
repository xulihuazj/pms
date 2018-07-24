package com.xulihuazj.pms.utils.web;

import com.sun.tools.corba.se.idl.constExpr.ShiftRight;
import com.xulihuazj.pms.utils.log.LogHelper;
import com.xulihuazj.pms.utils.security.SecurityHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Web 常用方法帮助类
 */
public class WebHelper {


    /**
     * 获取Cookie
     *
     * @param request    request
     * @param cookieName cookie 名
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equalsIgnoreCase(cookieName)) {
                    cookieValue = cookies[i].getValue();
                    break;
                }
            }
        }
        return cookieValue;
    }

    public static void setCookie(HttpServletResponse response, String cookieName, String value) {
        setCookie(response, cookieName, value, null);
    }

    /**
     * 设置Cookie
     *
     * @param response   response
     * @param cookieName cookie 名
     * @param value
     * @param domanin
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String value,
                                 String domanin) {
        setCookie(response, cookieName, value, null);
    }

    /**
     * 设置Cookie
     *
     * @param response   response
     * @param cookieName cookie 名
     * @param value      cookie 值
     * @param expiry     过期时间
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String value,
                                 int expiry) {
        setCookie(response, cookieName, value, expiry, null, null);
    }

    /**
     * 设置Cookie
     *
     * @param response   response
     * @param cookieName cookie 名
     * @param value      cookie 值
     * @param expiry     过期时间
     * @param domain
     * @param path       路径
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String value, Integer expiry,
                                 String domain, String path) {
        try {
            Cookie cookie = new Cookie(cookieName, URLEncoder.encode(value, "UTF-8"));
            // 过期时间不为空
            if (null != expiry) {
                cookie.setMaxAge(expiry);
            }
            if (StringUtils.isNotBlank(domain)) {
                if (!domain.startsWith(".")) {
                    domain = "." + domain;
                }
                cookie.setDomain(domain);
            }
            // path 不为空
            if (StringUtils.isNotBlank(path)) {
                cookie.setPath(path);
            } else {
                cookie.setPath("/");
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除Cookie
     *
     * @param response   response
     * @param cookieName cookie名
     */
    public static void deleteCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取客户端浏览器类型
     *
     * @param request request请求
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (null != userAgent) {
            /**
             * ***********
             * Chrome 系列
             * ***********
             * Chrome 24.0.1295.0   -   Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.15 (KHTML, like Gecko) Chrome/24.0.1295.0 Safari/537.15
             * Chrome 24.0.1292.0   -   Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.14 (KHTML, like Gecko) Chrome/24.0.1292.0 Safari/537.14
             * Chrome 24.0.1290.1   -   Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13
             * 判断依据:http://www.useragentstring.com/pages/Chrome/
             */
            if (userAgent.contains("Chrome")) {
                //拿到User Agent String "Chrome/" 之后的字符串,结果形如"24.0.1295.0 Safari/537.15"或"24.0.1295.0"
                String temp = userAgent.substring(userAgent.indexOf("Chrome/") + 7);
                String chromeVersion;
                if (temp.indexOf(" ") < 0) {
                    chromeVersion = temp;
                } else {
                    chromeVersion = temp.substring(0, temp.indexOf(" "));
                }
                return "Chrome" + chromeVersion;
            } else if (userAgent.contains("Firefox")) {
                /**
                 * *******
                 * Firefox 系列
                 * *******
                 * Firefox 16.0.1   -   Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0.1) Gecko/20121011 Firefox/16.0.1
                 * Firefox 15.0a2   -   Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20120716 Firefox/15.0a2
                 * Firefox 15.0.2   -   Mozilla/5.0 (Windows NT 6.2; WOW64; rv:15.0) Gecko/20120910144328 Firefox/15.0.2
                 * 判断依据:http://www.useragentstring.com/pages/Firefox/
                 */
                String temp = userAgent.substring(userAgent.indexOf("Firefox/") + 8);
                String firefoxVersion;
                if (temp.indexOf(" ") < 0) {
                    firefoxVersion = temp;
                } else {
                    firefoxVersion = temp.substring(0, temp.indexOf(" "));
                }
                return "Firefox" + firefoxVersion;
            } else if (userAgent.contains("MSIE") ||
                    (userAgent.contains("like Gecko") && userAgent.contains("Windows"))) {
                /**
                 * *******
                 * IE 系列
                 * *******
                 * MSIE 10.0    -   Internet Explorer 10
                 * MSIE 9.0 -   Internet Explorer 9
                 * MSIE 8.0 -   Internet Explorer 8 or IE8 Compatibility View/Browser Mode
                 * MSIE 7.0 -   Windows Internet Explorer 7 or IE7 Compatibility View/Browser Mode
                 * MSIE 6.0 -   Microsoft Internet Explorer 6
                 * 判断依据:http://msdn.microsoft.com/en-us/library/ms537503(v=vs.85).aspx
                 */
                if (userAgent.contains("MSIE 10.0")) {//Internet Explorer 10
                    return "Internet Explorer" + "10";
                } else if (userAgent.contains("MSIE 9.0")) {//Internet Explorer 9
                    return "Internet Explorer" + "9";
                } else if (userAgent.contains("MSIE 8.0")) {//Internet Explorer 8
                    return "Internet Explorer" + "8";
                } else if (userAgent.contains("MSIE 7.0")) {//Internet Explorer 7
                    return "Internet Explorer" + "7";
                } else if (userAgent.contains("MSIE 6.0")) {//Internet Explorer 6
                    return "Internet Explorer" + "6";
                } else if (userAgent.contains("like Gecko")) {
                    return "Internet Explorer" + "11";
                }
            } else if (userAgent.contains("Safari") &&
                    !userAgent.contains("Android") && !userAgent.contains("iPhone") &&
                    !userAgent.contains("iPad")) {
                String temp = userAgent.substring(userAgent.indexOf("Safari/") + 8);
                String version;
                if (temp.indexOf(" ") < 0) {
                    version = temp;
                } else {
                    version = temp.substring(0, temp.indexOf(" "));
                }
                return "Safari" + version;
            } else if (userAgent.contains("Opera")) {
                String temp = userAgent.substring(userAgent.indexOf("Opera/") + 8);//拿到User Agent String "Firefox/" 之后的字符串,结果形如"16.0.1 Gecko/20121011"或"16.0.1"
                String version;
                if (temp.indexOf(" ") < 0) {
                    version = temp;
                } else {
                    version = temp.substring(0, temp.indexOf(" "));
                }
                return "Opera" + version;
            }

            if (userAgent.contains("AppleWebKit")) {
                if (userAgent.contains("iPhone") || userAgent.contains("iphone")) {
                    return "iosHtml5";
                }

                if (userAgent.contains("iPad") || userAgent.contains("ipad")) {
                    return "iPadHtml5";
                }

                if (userAgent.contains("Android") || userAgent.contains("android")) {
                    return "androidHtml5";
                }
            }

            if (userAgent.contains("iPhone") || userAgent.contains("iphone")) {
                return "iosApp";
            }

            if (userAgent.contains("iPad") || userAgent.contains("ipad")) {
                return "iPadApp";
            }

            if (userAgent.contains("Android") || userAgent.contains("android")) {
                return "androidApp";
            }
        }
        return "";
    }

    /**
     * 过滤脚本注入
     *
     * @param value 过滤的字符串
     * @return
     */
    public static String filteScriptInject(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replaceAll("&", "&temp;").
                    replaceAll("<", "&lt;").
                    replaceAll(">", "&gt;").
                    replaceAll("\"", "&quot;").
                    replaceAll(",", "&acute;");
        }
        return value;
    }


    /**
     * 是否为爬虫
     *
     * @param request request请求
     * @return
     */
    public static Boolean isSpider(HttpServletRequest request) {
        boolean isSpider = false;
        String[] spiderAgents = {"baiduspider", "googlebot", "bingbot", "haosouspider"};
        if (request.getHeader("User-Agent") != null) {
            for (String spiderAgent : spiderAgents) {
                if (request.getHeader("User-Agent").toLowerCase().indexOf(spiderAgent) >= 0) {
                    isSpider = true;
                    break;
                }
            }
        }
        return isSpider;
    }

    /**
     * 提取主域名
     *
     * @param url
     * @return
     * @throws MalformedURLException
     */
    public static String getTopDomainWithoutSubdomain(String url) throws MalformedURLException {
        String s = "[^(\\.|www)]+\\.((com|xin|shop|club|top|wang|win|site|vip|store|net|bid|cc|ltd|ren|lol|mom|online|tech|biz|red|website|space|link|news|date|loan|mobi|press|video|market|live|studio|help|info|click|pics|photo|trade|vc|party|rocks|band|gift|wiki|design|software|social|lawyer|engineer|org|com|net|org|gov|name|tv|me|asia|co|work|game|games|pro|kim|science|group|pub|pw|men|house)(\\.\\w{2})?|\\w{2})$";
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**
     * 获取是否为AJAX请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (org.springframework.util.StringUtils.hasText(requestType)) {
            return true;
        }
        return false;
    }
}

