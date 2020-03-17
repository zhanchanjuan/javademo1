package com.javademo.common.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author CJ
 * @date 2019/12/11
 */
@Data
public class ThirdPartyBindingUserDto implements Serializable {

    private static final long serialVersionUID = -176870696670164340L;

    @NotBlank(groups = {StateBind.class, PasswordBind.class}, message = "用户名不能为空")
    private String username;

    @NotBlank(groups = {StateBind.class}, message = "state不能为空")
    private String state;

    @NotBlank(groups = {PasswordBind.class}, message = "password不能为空")
    private String password;

    // 是否绑定入库，是：入库 否：只校验不入库
    private Boolean bind;

    @NotBlank(groups = {StateBind.class, PasswordBind.class}, message = "openid不能为空")
    private String openid;

    @NotBlank(groups = {StateBind.class, PasswordBind.class}, message = "unionid不能为空")
    private String unionid;

    @NotBlank(groups = {StateBind.class, PasswordBind.class}, message = "类型不能为空")
    private String type;

    // 密码绑定
    public @interface PasswordBind {
    }

    // state绑定
    public @interface StateBind {
    }

}
