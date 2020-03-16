package com.javademo.common.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hawk
 * @date 2019/10/18
 * 统一返回结果类
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -6748650462459739004L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体的内容，返回的数据或者异常详情
     */
    private T data;

    /**
     * 列表总数
     */
    private Integer count;

}
