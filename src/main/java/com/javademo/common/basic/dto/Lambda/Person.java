package com.javademo.common.basic.dto.Lambda;

import lombok.Data;

/**
 * @author shuyi
 * @date 2020/4/26
 */
@Data
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }



}
