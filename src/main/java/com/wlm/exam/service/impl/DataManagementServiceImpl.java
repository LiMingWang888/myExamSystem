package com.wlm.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlm.exam.mapper.CourseMapper;
import com.wlm.exam.mapper.StudentMapper;
import com.wlm.exam.pojo.Course;
import com.wlm.exam.pojo.Student;
import com.wlm.exam.service.DataManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wlm
 * @date 2023/8/16 - 14:20
 */
@Service
public class DataManagementServiceImpl implements DataManagementService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void deleteCourse(String courseNo) {
        courseMapper.deleteByPrimaryKey(courseNo);
    }

    @Override
    public void addCourse(Course course) {
        courseMapper.insert(course);
    }

    @Override
    public List<Integer> getLimits() {
        return courseMapper.selectDistinctCLimit();
    }

    @Override
    public PageInfo<Course> selectAllCoursewithPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(courseMapper.selectAll());
    }

    @Override
    public PageInfo<Student> selectAllStudentwithPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(studentMapper.selectAll());
    }

    @Override
    public List<String> getInstitutions() {
        return studentMapper.selectDistinctStuInstitution();
    }

    @Override
    public Student getStudentByStuNo(String stuNo) {
        return studentMapper.selectByPrimaryKey(stuNo);
    }

    @Override
    public void deleteStudentByStuNo(String stuNo) {
        studentMapper.deleteByPrimaryKey(stuNo);
    }
}
