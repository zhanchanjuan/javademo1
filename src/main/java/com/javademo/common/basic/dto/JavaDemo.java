package com.javademo.common.basic.dto;

import org.springframework.util.ClassUtils;

/**
 * @author shuyi
 * @date 2020/3/18
 */
public class JavaDemo {

    public static void main(String[] args) {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        System.out.println(path);
    }





}
