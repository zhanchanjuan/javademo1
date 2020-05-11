package com.javademo.common.basic.dto.thread;

import com.javademo.common.exception.CommonException;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class RandomThread extends Thread {

    public RandomThread(String name){
        super(name);
    }
    /*
    *currentThread()  获取当前线程
    * Thread.currentThread().getName() 获取当前线程名称
    * */
    @Override
    public void run() {
        try{
            //方法一
//            String name=getName();
//            System.out.println(name);
            //方法二 sleep()以当前设定的毫秒数来决定线程执行时间
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
        }catch(Exception e){
            throw new CommonException("线程异常"+e);
        }
        super.run();
    }
}
