package com.javademo.common.basic.dto.Lambda;

/**
 * Lambda表达式-->简化线程代码
 * @author shuyi
 * @date 2020/4/26
 */
public class Lambda {
    public static void main(String[] args) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"111创建一个新线程");
            }
        }).start();

        //Lambda表达式
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"222创建一个新线程");
        }).start();

        //简化Lambda表达式
        new Thread(()->
            System.out.println(Thread.currentThread().getName()+"111创建一个新线程")
        ).start();
    }
}
