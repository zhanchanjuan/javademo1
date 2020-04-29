package com.javademo.common.basic.dto.Lambda;

/**
 * @author shuyi
 * @date 2020/4/29
 */
public class CalculatorDemo1 {
    public static void main(String[] args) {
        //使用invokeCalc方法，方法参数是一个接口，可以使用匿名内部类
        invokeCalc(10, 20, new Calculator() {
            @Override
            public int calc(int a, int b) {
                return a+b;
            }
        });
        //用lambda表达式
        invokeCalc(120,110,(int a,int b)->{
            return a+b;
        });

    }

    public static void invokeCalc(int a ,int b,Calculator c){
        int sum=c.calc(a,b);
        System.out.println("计算结果是{}"+sum);
    }
}
