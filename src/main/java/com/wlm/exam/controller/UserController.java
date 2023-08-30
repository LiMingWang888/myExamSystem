package com.wlm.exam.controller;

import com.wlm.exam.constant.CookieConstant;
import com.wlm.exam.constant.RedisConstant;
import com.wlm.exam.enums.LoginEnum;
import com.wlm.exam.exception.LoginException;
import com.wlm.exam.form.ModifyUserFormVO;
import com.wlm.exam.form.UserLoginRequestVO;
import com.wlm.exam.pojo.User;
import com.wlm.exam.service.UserService;
import com.wlm.exam.utils.CookieUtil;
import com.wlm.exam.utils.UserTypeUtil;
import com.wlm.exam.vo.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * @Author WangLiMing
 * @Description: 用户Controller
 * @Date 2023/8/14 14:29
 **/
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public ResultResponse login(@Valid UserLoginRequestVO userLoginRequestVO, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            if (log.isErrorEnabled()) {
                log.error("【登录操作】账号格式不符合规范");
            }
            throw new LoginException(LoginEnum.INVALID_PARAM.getMessage());
        }

        //获取当前用户
        User userData = userService.getUserData(userLoginRequestVO.getName());
        Integer userType = userData.getUserType();
        //校验用户类型
        if (!userType.equals(userLoginRequestVO.getUserType())) {
            if (log.isErrorEnabled()) {
                log.error("【登录操作】账号格式不符合规范");
            }
            throw new LoginException(LoginEnum.INVALID_PARAM.getMessage());
        }
        //校验用户密码，如果正确则存储至redis中
        userService.checkPassWord(userLoginRequestVO.getPassword(), userData, response);

        userService.checkUserType(userLoginRequestVO.getUserType(), userData);

        return ResultResponse.success(userData);
    }


    @GetMapping("/userInfo")
    public ModelAndView userInfo(Map<String, Object> map, HttpServletRequest request) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String userId = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        User user = userService.selectById(userId);
        map.put("userNo", user.getUsername());
        map.put("userName", user.getNickname());
        map.put("userType", UserTypeUtil.userType(user.getUserType()));
        return new ModelAndView("user/userInfo", map);
    }

    @PostMapping("/modifyUser")
    @ResponseBody
    public ResultResponse modifyUser(ModifyUserFormVO modifyUserFormVO, HttpServletRequest request) {
        if (modifyUserFormVO.getNewPassword() == null || modifyUserFormVO.getOldPassword() == null || modifyUserFormVO.getRenewPassword() == null) {
            return ResultResponse.success();
        }
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String userId = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        modifyUserFormVO.setId(userId);
        return userService.modifyUser(modifyUserFormVO);
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        redisTemplate.delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        return new ModelAndView("user/index");
    }

}
