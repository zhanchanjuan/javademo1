package com.javademo.common.basic.dto;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Runnable线程测试"+Thread.currentThread().getName()+i);
        }

    }
}
