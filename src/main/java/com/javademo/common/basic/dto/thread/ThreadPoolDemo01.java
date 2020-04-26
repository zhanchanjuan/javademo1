package com.javademo.common.basic.dto.thread;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * 线程池 JDK1.5 java.util.concurrent.Executors 线程池工厂类
 * @author shuyi
 * @date 2020/4/26
 */
public class ThreadPoolDemo01 {

    public static void main(String[] args) {
        ExecutorService  es=Executors.newFixedThreadPool(2);
        //调用ExecutorService中的submit方法，传递线程任务，开启线程，执行run方法
        es.submit(new RunableImpl());
        //线程池会一直开启，使用完的线程会归还给线程池，线程可以继续使用
        es.submit(new RunableImpl());
        es.submit(new RunableImpl());

        //结束线程
        es.shutdown();
        //结束完线程以后再执行线程会抛异常，所以shutdown()一般不用！
        es.submit(new RunableImpl());
    }
}
