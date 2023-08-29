//package com.wlm.exam.aspect;
//
//import com.wlm.exam.constant.CookieConstant;
//import com.wlm.exam.constant.RedisConstant;
//import com.wlm.exam.exception.BusiException;
//import com.wlm.exam.utils.CookieUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//@Slf4j
//public class AdminAuthorizeAspect {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Pointcut("execution(public * com.wlm.exam.controller..*.*(..))" +
//            "&&!execution(public * com.wlm.exam.controller.UserController.*(..))")
//    public void verify() {
//    }
//
//    @Before("verify()")
//    public void doVerify() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        //查询cookie
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
//        if (cookie == null) {
//            if (log.isWarnEnabled()) {
//                log.warn("【登录校验】Cookie中查不到token");
//            }
//            throw new BusiException(999, "Cookie里没有token");
//        }
//
//        //去redis查询
//        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//        if (StringUtils.isEmpty(tokenValue)) {
//            if (log.isWarnEnabled()) {
//                log.warn("【登录校验】Redis中查不到token");
//            }
//            throw new BusiException(1000, "redis里没有token");
//        }
//    }
//}
