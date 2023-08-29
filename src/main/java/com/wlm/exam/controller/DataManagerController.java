package com.wlm.exam.Controller;

import com.github.pagehelper.PageInfo;
import com.wlm.exam.VO.ResultResponse;
import com.wlm.exam.constant.CookieConstant;
import com.wlm.exam.constant.RedisConstant;
import com.wlm.exam.pojo.Course;
import com.wlm.exam.pojo.Student;
import com.wlm.exam.pojo.User;
import com.wlm.exam.service.DataManagementService;
import com.wlm.exam.service.UserService;
import com.wlm.exam.utils.CookieUtil;
import com.wlm.exam.utils.UserTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author wlm
 * @date 2023/8/16 - 9:14
 */
@Controller
@RequestMapping("admin")
@Slf4j
public class DataManagerController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private DataManagementService dataManagementService;

    @GetMapping("/courseData")
    public ModelAndView courseData(Map<String, Object> map,
                                   HttpServletRequest request,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "15") Integer size) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String userId = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//        Pageable pageable = PageRequest.of(page - 1, size);
//        //通过Jpa，获取Course分页结果
//        Page<Course> allCourse = dataManagementService.findAllCourse(pageable);
        PageInfo<Course> coursePage = dataManagementService.selectAllCoursewithPage(page, size);
        //获取表单中不重复的limit选项
        List<Integer> limits = dataManagementService.getLimits();
        User user = userService.selectById(userId);
        map.put("limits", limits);
        map.put("coursePage", coursePage);
        map.put("currentPage", page);
        map.put("size", size);
        map.put("userName", user.getNickname());
        map.put("userType", UserTypeUtil.userType(user.getUserType()));
        return new ModelAndView("dataManagement/courseData", map);
    }

    @PostMapping("/addCourse")
    @ResponseBody
    public ResultResponse addCourse(Course course) {
        if ("无".equals(course.getRemark())) {
            course.setRemark("0");
        }
        dataManagementService.addCourse(course);
        return ResultResponse.success();
    }

    @PostMapping("/modifyCourse")
    @ResponseBody
    public ResultResponse modifyCourse(Course course) {
        if ("无".equals(course.getRemark())) {
            course.setRemark("0");
        }
        dataManagementService.addCourse(course);
        return ResultResponse.success();
    }

    @GetMapping("/deleteCourse")
    public ModelAndView deleteCourse(Map<String, Object> map, @RequestParam String courseNo) {
        dataManagementService.deleteCourse(courseNo);
        map.put("msg", "删除成功");
        map.put("url", "/exam/admin/courseData");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/studentData")
    public ModelAndView studentData(Map<String, Object> map,
                                    HttpServletRequest request,
                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "15") Integer size) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        String userId = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

        PageInfo<Student> allStudent = dataManagementService.selectAllStudentwithPage(page, size);
        List<String> institutions = dataManagementService.getInstitutions();
        User user = userService.selectById(userId);
        map.put("institutions", institutions);
        map.put("allStudent", allStudent);
        map.put("currentPage", page);
        map.put("size", size);
        map.put("userName", user.getNickname());
        map.put("userType", UserTypeUtil.userType(user.getUserType()));
        return new ModelAndView("dataManagement/studentData", map);

    }

    @PostMapping("/deleteStudent")
    public ResultResponse deleteStudent(@RequestParam String stuNo) {
        dataManagementService.deleteStudentByStuNo(stuNo);
        return ResultResponse.success();
    }

}
