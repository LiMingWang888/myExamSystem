package com.wlm.exam.service;

import com.github.pagehelper.PageInfo;
import com.wlm.exam.pojo.Course;
import com.wlm.exam.pojo.Student;

import java.util.List;

/**
 * @author wlm
 * @date 2023/8/16 - 14:20
 */
public interface DataManagementService {

    void deleteCourse(String courseNo);

    void addCourse(Course course);

    List<Integer> getLimits();

    PageInfo<Course> selectAllCoursewithPage(int page, int pageSize);

    PageInfo<Student> selectAllStudentwithPage(int page, int pageSize);

    List<String> getInstitutions();

    Student getStudentByStuNo(String stuNo);

    void deleteStudentByStuNo(String stuNo);
}
