package com.javademo.common.basic.dto;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class InterruptThreadTest1 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new InterruptThread();
        thread.start();
        Thread.sleep(1);
        System.out.println(thread.getName()+"线程设置：interrupt");
        thread.interrupt();
    }


}
