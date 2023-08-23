package com.wlm.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wlm.exam.mapper")
public class MyExamSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyExamSystemApplication.class, args);
    }

}
