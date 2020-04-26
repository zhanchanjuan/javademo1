package com.javademo.common.basic.dto.thread;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class ShareRunnable implements Runnable {
    private int count=5;

    private int ticket=100;

    Object obj=new Object();

    /**
     *synchronized 加上此关键字的方法称为互斥区或者临界区
     * 只有获取到这个关键字对应的锁才能执行方法体，方法体执行完自动会释放锁
     */
    @Override
    public void run() {
//        System.out.println(""+Thread.currentThread().getName()+",count:"+count--);
        while (true){
            payTicket();
        }
    }

    /*
    * 定义一个同步方法
    * */
    public synchronized void payTicket(){
        if(ticket>0){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"---->正在卖第"+ticket+"张票");
            ticket--;
        }
    }
}
