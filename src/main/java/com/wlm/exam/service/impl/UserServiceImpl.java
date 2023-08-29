package com.wlm.exam.service.impl;

import com.wlm.exam.constant.CookieConstant;
import com.wlm.exam.constant.RedisConstant;
import com.wlm.exam.exception.LoginEnum;
import com.wlm.exam.exception.LoginException;
import com.wlm.exam.mapper.UserMapper;
import com.wlm.exam.pojo.User;
import com.wlm.exam.service.UserService;
import com.wlm.exam.utils.CookieUtil;
import com.wlm.exam.vo.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wlm
 * @date 2023/8/14 - 15:54
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User getUserData(String username) {
        List<User> users = userMapper.selectByUsername(username);
        if (users.isEmpty()) {
            if (log.isWarnEnabled()) {
                log.warn("用户信息不存在，错误的登录名：{}", username);
                throw new LoginException(LoginEnum.LOGINTYPE_DISABLE.getMessage());
            }
        }
        return users.get(0);
    }

//    @Override
//    public ResultResponse modifyUser(ModifyUserFormVO form) {
//        if (form.getNewPassword().equals(form.getRenewPassword())) {
//            return ResultResponse.error(-1, "两次输入的密码不一致");
//        }
//        User user = userMapper.selectByPrimaryKey(form.getId());
//        if (form.getOldPassword().equals(user.getPassword())) {
//            user.setPassword(form.getNewPassword());
//            user.setNickname(form.getNickName());
//            userMapper.updateByPrimaryKey(user);
//            return ResultResponse.success();
//        } else {
//            return ResultResponse.error(-100, "原密码错误");
//        }
//    }

    @Override
    public User selectById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void checkPassWord(String password, User userData, HttpServletResponse response) {
        String passwordCompared = userData.getPassword();
        if (passwordCompared.equals(password)) {
            //设置token到redis
            String token = UUID.randomUUID().toString();
            Integer expire = RedisConstant.EXPIRE;
            redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), String.valueOf(userData.getUserId()), expire, TimeUnit.SECONDS);
            //设置token至cookie
            CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
        } else {
            if (log.isWarnEnabled()) {
                log.warn("【登录操作】用户密码不正确！");
            }
            throw new LoginException(LoginEnum.LOGINPASSWORD_WRONG.getMessage());
        }
    }

    @Override
    public void checkUserType(Integer userType, User userData) {
        Integer userTypeCompared = userData.getUserType();
        if (!userTypeCompared.equals(userType)) {
            if (log.isWarnEnabled()) {
                log.warn("【登录操作】用户类型不正确！");
            }
            throw new LoginException(LoginEnum.LOGINTYPE_DISABLE.getMessage());
        }
    }
}
