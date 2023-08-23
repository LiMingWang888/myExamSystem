package com.wlm.exam.service.impl;

import com.wlm.exam.VO.ResultResponse;
import com.wlm.exam.enums.ResultEnum;
import com.wlm.exam.exception.LoginException;
import com.wlm.exam.form.ModifyUserFormVO;
import com.wlm.exam.mapper.UserMapper;
import com.wlm.exam.pojo.User;
import com.wlm.exam.service.UserService;
import com.wlm.exam.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wlm
 * @date 2023/8/14 - 15:54
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserData(String username) {
        List<User> users = userMapper.selectByUsername(username);
        if (users.isEmpty()) {
            if (log.isDebugEnabled()) {
                log.error("用户信息不存在，错误的登录名：{}", username);
                throw new LoginException(ResultEnum.LOGINTYPE_DISABLE.getCode(), ResultEnum.LOGINTYPE_DISABLE.getMessage());
            }
        }
        return users.get(0);
    }

    @Override
    public ResultResponse modifyUser(ModifyUserFormVO form) {
        if (form.getNewPassword().equals(form.getRenewPassword())) {
            return ResultVOUtil.error(-1, "两次输入的密码不一致");
        }
        User user = userMapper.selectByPrimaryKey(form.getId());
        if (form.getOldPassword().equals(user.getPassword())) {
            user.setPassword(form.getNewPassword());
            user.setNickname(form.getNickName());
            userMapper.updateByPrimaryKey(user);
            return ResultVOUtil.success();
        } else {
            return ResultVOUtil.error(-100, "原密码错误");
        }
    }

    @Override
    public User selectById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void checkPassWord(String password, User userData) {
        String passwordCompared = userData.getPassword();
        if (passwordCompared.equals(password)) {

        } else {
            if (log.isErrorEnabled()) {
                log.error("【登录操作】用户密码不正确！");
            }
            throw new LoginException(ResultEnum.LOGINPASSWORD_WRONG.getCode(), ResultEnum.LOGINPASSWORD_WRONG.getMessage());
        }
    }

    @Override
    public void checkUserType(Integer userType, User userData) {
        Integer userTypeCompared = userData.getUserType();
        if (!userTypeCompared.equals(userType)) {
            if (log.isErrorEnabled()) {
                log.error("【登录操作】用户类型不正确！");
            }
            throw new LoginException(ResultEnum.LOGINTYPE_DISABLE.getCode(), ResultEnum.LOGINTYPE_DISABLE.getMessage());
        }
    }
}
