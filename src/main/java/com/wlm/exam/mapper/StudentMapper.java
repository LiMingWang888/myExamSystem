package com.wlm.exam.mapper;

import com.wlm.exam.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
@author wlm
@date 2023/8/22 - 9:58
*/
@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(String stuNo);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String stuNo);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectAll();

    List<String> selectDistinctStuInstitution();



}