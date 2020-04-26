package com.javademo.common.basic.dto.thread;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class StopThread extends Thread {
    private boolean interrupt=true;

    public StopThread(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始运行");
        int i=0;
        while(interrupt){
            System.out.println(""+(i++));
        }
        System.out.println("停止！timer:"+System.currentTimeMillis());
    }



}
