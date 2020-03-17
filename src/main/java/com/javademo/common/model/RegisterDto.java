package com.javademo.common.model;




import com.javademo.entity.system.AppUser;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ZCJ
 * @date 2020/03/15
 * 通用注册dto
 */
@Data
public class RegisterDto extends AppUser {

    private static final long serialVersionUID = -6605882060221220117L;

    // 注册类型 QQ:11 微信:12 微博：13
    @NotNull(groups = {RegisterDto.ThirdParty.class}, message = "第三方注册用户类型不能为空")
    private Integer type;

    // 唯一主键
    @NotBlank(groups = {RegisterDto.ThirdParty.class}, message = "第三方注册用户唯一键不能为空")
    private String openid;

    // 联合id
    @NotBlank(groups = {RegisterDto.ThirdParty.class}, message = "第三方注册用户联合id不能为空")
    private String unionid;

    // 唯一随机码
    @NotBlank(groups = {RegisterDto.ThirdParty.class}, message = "第三方注册用户state不能为空")
    private String state;

    // 第三方注册验证
    public @interface ThirdParty {
    }

}
