package com.du.common.util;

import com.google.common.net.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * Http工具类
 */
public class HttpUtils {
    private HttpUtils() {}
    /**
     * 获取终端ip
     * @param request   HttpServletRequest
     * @return  String(ip地址)
     */
    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader(HttpHeaders.X_FORWARDED_FOR) == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader(HttpHeaders.X_FORWARDED_FOR);
    }

}
