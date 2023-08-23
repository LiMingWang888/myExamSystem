package com.wlm.exam.Controller;

import com.wlm.exam.VO.ResultResponse;
import com.wlm.exam.constant.CookieConstant;
import com.wlm.exam.constant.RedisConstant;
import com.wlm.exam.enums.ResultEnum;
import com.wlm.exam.exception.LoginException;
import com.wlm.exam.form.ModifyUserFormVO;
import com.wlm.exam.form.UserLoginRequestVO;
import com.wlm.exam.pojo.User;
import com.wlm.exam.service.UserService;
import com.wlm.exam.utils.CookieUtil;
import com.wlm.exam.utils.ResultVOUtil;
import com.wlm.exam.utils.UserTypeUtil;
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
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    public ModelAndView login(@Valid UserLoginRequestVO userLoginRequestVO, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            if (log.isErrorEnabled()) {
                log.error("【登录操作】输入数据格式不符规范");
            }
            throw new LoginException(ResultEnum.INVALID_PARAM.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        //获取当前用户
        User userData = userService.getUserData(userLoginRequestVO.getName());
        Integer userType = userData.getUserType();
        //校验用户密码，如果正确则存储至redis中
        userService.checkPassWord(userLoginRequestVO.getPassword(), userData);

        //设置token到redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), String.valueOf(userData.getUserId()), expire, TimeUnit.SECONDS);

        //设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

//        userService.checkUserType(userLoginRequestVO.getUserType(), userData);

        if (userData.getUserType() == 2) {
            return new ModelAndView("user/adminHome");
        } else if (userData.getUserType() == 1) {
            return new ModelAndView("user/studentHome");
        }
        return null;
//        return ResultResponse.success(userData);
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
            return ResultVOUtil.success();
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
