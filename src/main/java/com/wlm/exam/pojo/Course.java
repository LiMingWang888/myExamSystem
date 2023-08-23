package com.wlm.exam.pojo;

import lombok.Data;

/**
 * @author wlm
 * @date 2023/8/16 - 9:44
 */
@Data
public class Course {
    private String courseNo;

    private String courseName;

    private String courseSemester;

    /**
     * 字段为0为公共无限制必修课程，1为学生自选选修课，2为专业课不能跨选
     */
    private Integer cLimit;

    private String remark;
}