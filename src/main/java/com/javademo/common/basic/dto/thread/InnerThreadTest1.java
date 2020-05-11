package com.javademo.common.basic.dto.thread;

/**
 * 匿名测试类 测试
 * @author shuyi
 * @date 2020/4/9
 */
public class InnerThreadTest1 {
 
    public static void main(String[] args) {
        /*
        第一种情况
        直接new Thread(){} 重写Thread的run方法
        */
//        Thread thread=new Thread(){
//            @Override
//            public void run() {
//                super.run();
//            }
//        }.start();
//
        new Thread(){
            @Override
            public void run(){
                for (int i=0;i<=100;i++){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"匿名测试类Thread线程"+i);
                }
            }
        }.start();

        Runnable runnable=new Runnable(){
            @Override
            public void run() {
              for (int i=0;i<=10;i++){
                  try {
                      Thread.sleep(1);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  System.out.println(Thread.currentThread().getName()+"runnable线程匿名测试类"+i);
            } }
        };
        new Thread(runnable).start();

    }
}
