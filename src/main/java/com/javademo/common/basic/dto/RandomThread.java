package com.javademo.common.basic.dto;

import com.javademo.common.exception.CommonException;

/**
 * @author shuyi
 * @date 2020/4/8
 */
public class RandomThread extends Thread {

    public RandomThread(String name){
        super(name);
    }

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
        }catch(Exception e){
            throw new CommonException("线程异常"+e);
        }
        super.run();
    }
}
