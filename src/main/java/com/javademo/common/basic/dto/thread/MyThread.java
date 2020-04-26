package com.javademo.common.basic.dto.thread;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("hello-->第一条Thread线程");
    }
}
