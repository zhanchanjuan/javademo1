package com.javademo.common.model;


import lombok.Data;


import java.io.Serializable;

/**
 * @author Hawk
 * @date 2019/12/6
 * 第三方平台注册信息通用类
 */
@Data
public class ThirdPartyRegisterStatusDto implements Serializable {

    private static final long serialVersionUID = -5052316336517051338L;

    //微信扫码后用户状态，1：已扫码未注册，2：已扫码已注册
    private Integer status;

    //用户已注册，设置用户登录信息
    private LoginReturnDto loginData;

}
