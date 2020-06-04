package com.javademo.common.basic.dto.thread;

import java.util.concurrent.*;

/**
 * @author shuyi
 * @date 2020/5/8
 */
public class ThreadPoolTest02 {
    public static void main(String[] args) {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(5,10,200,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));

        /**
         * Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
         Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池
         Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池
         */
        Executors.newFixedThreadPool(11);

        for(int i=0;i<6;i++){
            ThreadPoolDemo02 threadPoolDemo02=new ThreadPoolDemo02(i);
            executor.execute(threadPoolDemo02);
            System.out.println("线程池中的线程数目："+executor.getPoolSize()+
                    ",队列中等待执行的数目:"+executor.getQueue()+",已经执行完任务的数目:"+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
