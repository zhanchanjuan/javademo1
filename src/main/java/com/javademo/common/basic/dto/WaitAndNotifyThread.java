package com.javademo.common.basic.dto;

/**
 * 模拟顾客买包子，老板做包子
 * Created by 书一 on 2020/4/12.
 * 等待唤醒案例：线程之间的通信
 */
public class WaitAndNotifyThread {
    public static void main(String[] args) {
        //同步方法里的锁对象应该是一致的
        //只有锁对象object才能用wait()和notify()方法
        //创建锁对象，保证唯一性
        Object obj=new Object();

        //创建一个顾客线程
        new Thread(){
            @Override
            public void run(){
                while (true){
                    synchronized (obj){
                        System.out.println("顾客1告知老板要的包子数量和种类");
                        //调用wait()方法，放弃cpu执行，进入waiting状态(无效等待)
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //唤醒之后执行的代码
                        System.out.println("包子已经做好了，顾客1开吃！");
                        System.out.println("______________________________");
                    }
                }
            }
        }.start();

        //创建一个顾客线程
        new Thread(){
            @Override
            public void run(){
                while (true){
                    synchronized (obj){
                        System.out.println("顾客2告知老板要的包子数量和种类");
                        //调用wait()方法，放弃cpu执行，进入waiting状态(无效等待)
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //唤醒之后执行的代码
                        System.out.println("包子已经做好了，顾客2开吃！");
                        System.out.println("______________________________");
                    }
                }
            }
        }.start();


        //创建一个老板线程
        new Thread(){
            @Override
            public void run(){
                while (true){
                    try {
                        //花5s时间做包子
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (obj){
                        System.out.println("包子做好了，给顾客打包好，说可以开吃了");
                        //唤醒顾客线程notify()
                        obj.notify();  //随机唤醒其中一顾客线程
//                        obj.notifyAll(); //唤醒所有的顾客线程
                    }
                }
            }
        }.start();


    }
}
