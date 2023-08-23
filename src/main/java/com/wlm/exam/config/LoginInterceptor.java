package com.wlm.exam.config;

import com.wlm.exam.constant.CookieConstant;
import com.wlm.exam.utils.CookieUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wlm
 * @date 2023/8/21 - 14:10
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        return cookie != null;
    }
}
