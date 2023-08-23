package com.wlm.exam.mapper;

import com.wlm.exam.pojo.Course;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wlm
 * @date 2023/8/22 - 9:20
 */
public class CourseMapperTest {
    private static CourseMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(CourseMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/CourseMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(CourseMapper.class, builder.openSession(true));

    }

    @Test
    public void testInsert() {
        Course course = new Course();
        course.setCourseName("test");
        course.setCLimit(13);
        course.setCourseSemester("4");
        mapper.insert(course);
    }
}
