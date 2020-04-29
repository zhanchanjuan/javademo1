package com.javademo.common.basic.dto.Lambda;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author shuyi
 * @date 2020/4/26
 */
public class ArraysDemo01 {
    public static void main(String[] args) {
        Person[] arr={
                new Person("娜娜",21),
                new Person("丹丹",19),
                new Person("爽爽",23)
        };
        //对数组中的person对象使用Arrays的sort方法，通过年龄进行升序
        Arrays.sort(arr, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge()-o2.getAge();
            }
        });
        //用lambda表达式进行比较排序
        Arrays.sort(arr,(Person o1, Person o2)->{
            return o1.getAge()-o2.getAge();
        });

        //遍历数组
        for(Person p:arr){
            System.out.println(p);
        }
    }
}
