package com.wlm.exam.mapper;

import com.wlm.exam.pojo.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
@author wlm
@date 2023/8/16 - 9:44
*/

@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(String courseNo);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String courseNo);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> selectAll();

    List<Integer> selectDistinctCLimit();



}