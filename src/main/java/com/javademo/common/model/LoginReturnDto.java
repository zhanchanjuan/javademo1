package com.javademo.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hawk
 * @date 2019/11/27
 * 自定义oauth登录返回结果类
 */
@Data
public class LoginReturnDto implements Serializable {

    private static final long serialVersionUID = 511328366089846353L;
    private UserSimpleDto userInfo; //用户信息
    private String access_token; // 鉴权token
    private String refresh_token; // 刷新token
    private Integer expires_in; // 过期时间（秒）
    private String token_type;  // token类型

}
