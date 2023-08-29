package com.wlm.exam.service;

import com.wlm.exam.pojo.User;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wlm
 * @date 2023/8/14 - 15:54
 */
public interface UserService {
    User getUserData(String username);

//    ResultResponse modifyUser(ModifyUserFormVO form);

    User selectById(String userId);

    void checkPassWord(String password, User userData, HttpServletResponse response);

    void checkUserType(Integer userType, User userData);
}
