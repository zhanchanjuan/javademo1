package com.javademo.common.basic.dto;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class ShareRunnable implements Runnable {
    private int count=5;

    /**
     *synchronized 加上此关键字的方法称为互斥区或者临界区
     * 只有获取到这个关键字对应的锁才能执行方法体，方法体执行完自动会释放锁
     */
    @Override
    public synchronized void run() {
        System.out.println(""+Thread.currentThread().getName()+",count:"+count--);
    }
}
