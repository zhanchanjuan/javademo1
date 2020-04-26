package com.javademo.common.basic.dto.thread;

/**
 * @author shuyi
 * @date 2020/4/26
 */
public class RunableImpl implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"创建了一个新的线程！");
    }
}
