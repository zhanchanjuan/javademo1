package com.javademo.common.basic.dto.thread;

/**
 * @author shuyi
 * @date 2020/5/8
 */
public class ThreadPoolDemo02 implements Runnable {

    private int threadNum;

    public ThreadPoolDemo02(int num){
        this.threadNum=num;
    }

    @Override
    public void run() {
        System.out.println("正在执行的{}"+threadNum);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行结束{}"+threadNum);

    }
}
