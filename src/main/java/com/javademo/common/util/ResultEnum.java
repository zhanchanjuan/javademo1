package com.javademo.common.util;

import lombok.Getter;

/**
 * 统一返回结果枚举类，此处可自定义状态
 */
@Getter
public enum ResultEnum {

    SUCCESS(200, "请求成功"),
    SYSTEM_ERROR(500, "后台程序异常"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
