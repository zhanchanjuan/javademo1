package com.javademo.common.basic.dto;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class ThreadRunTest1 {
    public static void main(String[] args) {
        /*myThread*/
        MyThread myThread=new MyThread();
        myThread.start();

        /*myRunnable*/
        MyRunnable myRunnable=new MyRunnable();
        Thread thread=new Thread(myRunnable);
        thread.start();

        /*RandomThread*/
        Thread[] threads=new Thread[10];
        for(int i=0;i<threads.length;i++){
            threads[i]=new RandomThread("RandomThread"+i);
        }
        for(Thread thread2:threads){
            thread2.start();
        }


        /*线程之间变量共享*/
        Runnable runnable=new ShareRunnable();
        Thread[] threads1=new Thread[5];
        for(int i=0;i<threads1.length;i++){
            threads1[i]=new Thread(runnable,"thread"+(i+1));
        }
        for(Thread thread3:threads1){
            thread3.start();
        }

    }


}
