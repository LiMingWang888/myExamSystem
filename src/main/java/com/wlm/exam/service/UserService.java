package com.wlm.exam.service;

import com.wlm.exam.VO.ResultResponse;
import com.wlm.exam.form.ModifyUserFormVO;
import com.wlm.exam.pojo.User;

/**
 * @author wlm
 * @date 2023/8/14 - 15:54
 */
public interface UserService {
    User getUserData(String username);

    ResultResponse modifyUser(ModifyUserFormVO form);

    User selectById(String userId);

    void checkPassWord(String password, User userData);

    void checkUserType(Integer userType, User userData);
}
