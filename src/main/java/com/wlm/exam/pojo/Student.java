package com.wlm.exam.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
@author wlm
@date 2023/8/22 - 9:58
*/
@Data
@Entity
public class Student {
    /**
    * 用户名/学号
    */
    @Id
    private String stuNo;

    /**
    * 姓名
    */
    private String stuName;

    /**
    * 性别
    */
    private String stuGender;

    /**
    * 入学年份
    */
    private String stuGrade;

    /**
    * 专业
    */
    private String stuMajor;

    /**
    * 学院
    */
    private String stuInstitution;

    /**
    * 是否在校，1表示在校，0表示不在校
    */
    private String resident;
}