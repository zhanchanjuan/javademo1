package com.javademo.common.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Hawk
 * @date 2019/11/21 登录dto
 */
@Data
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 4499571418873640146L;

    // @NotBlank String类型验证，要求非null、非空字符串
    @NotBlank(groups = {PasswordLogin.class, ThirdPartyLogin.class}, message = "用户名不能为空")
    private String username;

    @NotBlank(groups = PasswordLogin.class, message = "密码不能为空")
    private String password;

    @NotBlank(groups = PhoneLogin.class, message = "手机号不能为空")
    private String phone;

    @NotBlank(groups = PhoneLogin.class, message = "key不能为空")
    private String key;

    @NotBlank(groups = {PhoneLogin.class}, message = "code不能为空")
    private String code;

    @NotBlank(groups = {ThirdPartyLogin.class}, message = "state不能为空")
    private String state;

    @NotBlank(groups = {ThirdPartyLogin.class}, message = "openid不能为空")
    private String openid;

    /**
     * 登录类型 QQ:11 微信：12 微博 13
     */
    @NotBlank(groups = {ThirdPartyLogin.class}, message = "loginType不能为空")
    private String loginType;

    // 登录类型
    private String type;

    //组别（此处为密码登录），配合@Validated使用，在确定组别时会添加验证，例如@Validated(LoginDto.PasswordLogin.class)
    public @interface PasswordLogin {
    }

    public @interface PhoneLogin {
    }

    public @interface ThirdPartyLogin {
    }

}
