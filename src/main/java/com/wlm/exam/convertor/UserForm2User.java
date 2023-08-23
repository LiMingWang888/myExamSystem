package com.wlm.exam.convertor;

import com.wlm.exam.form.UserLoginRequestVO;
import com.wlm.exam.pojo.User;

public class UserForm2User {
    public static User converter(UserLoginRequestVO userLoginRequestVO) {
        User user = new User();
        user.setUsername(userLoginRequestVO.getName());
        user.setPassword(userLoginRequestVO.getPassword());
        user.setUserType(userLoginRequestVO.getUserType());
        return user;
    }
}