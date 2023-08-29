package com.wlm.exam.pojo;

import lombok.Data;

@Data
public class Person {
    private Long id;
    private String name;

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 省略 getter/setter 方法
}