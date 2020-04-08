package com.javademo.common.basic.dto;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("hello-->第一条Runnable线程");
    }
}
