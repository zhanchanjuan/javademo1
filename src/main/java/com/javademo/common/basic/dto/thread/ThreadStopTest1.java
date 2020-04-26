package com.javademo.common.basic.dto.thread;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class ThreadStopTest1 {

    public static void main(String[] args) throws InterruptedException {
        StopThread thread=new StopThread("newThread");
        thread.start();
        Thread.sleep(1);
        thread.stop();
    }


}
