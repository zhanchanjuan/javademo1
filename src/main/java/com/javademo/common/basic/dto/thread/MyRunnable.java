package com.javademo.common.basic.dto.thread;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runnable线程测试"+Thread.currentThread().getName()+i);
        }

    }
}
