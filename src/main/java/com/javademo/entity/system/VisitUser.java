package com.javademo.entity.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 在线用户监听类
 * @author shuyi
 * @date 2020/3/23
 */
@Data
public class VisitUser implements Serializable {

    private static final long serialVersionUID = 5642714689230399982L;

    /**
     * 用户sessionId
     */
    private String sessionId;

    /**
     * 用户访问ip
     */
    private String ip;

    /**
     * 用户第一次访问时间
     */
    private String firstTime;

}
