package com.javademo.common.basic.dto;

/**
 * @author shuyi
 * @date 2020/4/9
 */
public class InterruptThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"InterruptThread线程开始");
        for(int i=0;i<=10;i++){
            try {
                Thread.sleep(0);
                System.out.println("线程执行内容"+(i+1));
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"线程捕获异常，退出循环！");
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"线程结束！");
    }
}
