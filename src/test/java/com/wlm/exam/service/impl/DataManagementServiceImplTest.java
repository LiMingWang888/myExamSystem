package com.wlm.exam.service.impl;

import com.github.pagehelper.PageInfo;
import com.wlm.exam.pojo.Course;
import com.wlm.exam.service.DataManagementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wlm
 * @date 2023/8/21 - 9:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DataManagementServiceImplTest {

    @Autowired
    private DataManagementService dataManagementService;

    @Test
    public void selectAllwithPage() {
        PageInfo<Course> coursePageInfo = dataManagementService.selectAllCoursewithPage(1, 15);
        System.out.println(coursePageInfo);
    }
}