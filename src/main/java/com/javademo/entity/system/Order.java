package com.javademo.entity.system;

import lombok.Data;

/**
 * Created by 书一 on 2020/5/31.
 */
@Data
public class Order {

    private String id;
    private String name;

    public Order(){

    }

    public Order(String id, String name) {
        this.id=id;
        this.name=name;
    }
}
