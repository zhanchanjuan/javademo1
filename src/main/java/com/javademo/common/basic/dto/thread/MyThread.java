package com.javademo.common.basic.dto.thread;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello-->第一条Thread线程");
    }
}
