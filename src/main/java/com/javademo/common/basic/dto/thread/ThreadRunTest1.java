package com.javademo.common.basic.dto.thread;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class ThreadRunTest1 {
    public static void main(String[] args) {
        /*1111myThread*/
//        MyThread myThread=new MyThread();
//        myThread.run();//执行主main 线程方法
//        myThread.start();//开辟一条新的线程方法

        //像上面这样开辟2条方法甚至多条方法，他们之间是互不影响的，由cpu自主选择走哪条线程方法


        /*2222
        *myRunnable
        *避免了单线程的局限性，降低了程序的解耦性：把设置线程和开启线程进行了分离
        * */
//        MyRunnable myRunnable=new MyRunnable();
//        Thread thread=new Thread(myRunnable);
//        thread.start();


        /*
        *33333--
        * getName()方法的使用--RandomThread
        * */
//        Thread[] threads=new Thread[10];
//        for(int i=0;i<threads.length;i++){
//            threads[i]=new RandomThread("RandomThread线程名字111"+i);
//            threads[i].start();
//        }

        //获取main方法主线程的名称
        System.out.println(Thread.currentThread().getName());



        /*44444线程之间变量共享*/
        Runnable runnable=new ShareRunnable();
//        Thread[] threads1=new Thread[5];
//        for(int i=0;i<threads1.length;i++){
//            threads1[i]=new Thread(runnable,"thread"+(i+1));
//            threads1[i].start();
//        }

        //以数组的形式，然后循环创建线程，循环启动线程
        Thread[] threads1=new Thread[3];
        for(int i=0;i<threads1.length;i++){
            threads1[i]=new Thread(runnable);
            threads1[i].start();
        }
        // 分别new-->创建3个线程，分别执行买票操作，加上synchronized修饰代码块，这样就避免出现重复买票或者卖出不合格的票
        Thread thread1=new Thread(runnable);
        Thread thread2=new Thread(runnable);
        Thread thread3=new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
