package com.javademo.entity.system;


import com.javademo.common.model.RegisterDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class AppUser implements Serializable {

    private static final long serialVersionUID = 611197991672067628L;

    private String id;

    // 昵称
    private String nickname;

    @NotBlank(groups = {AppUser.CommonValidate.class, RegisterDto.ThirdParty.class}, message = "用户名不能为空")
    private String username;

    private String password;

    // 头像地址
    private String headImgUrl;

    // 生日
    private String birthday;

    // 性别 1:男,2:女,3:保密
    private Integer sex;

    @NotBlank(groups = {AppUser.CommonValidate.class, RegisterDto.ThirdParty.class}, message = "手机号不能为空")
    private String phone;

    private String mail;

    // 状态，可标识是否禁用 默认0：启用
    private Integer status;

    // 来源类型
    private String sourceType;

    // 注册类型
    private String registerType;

    private Date createTime;

    private Date updateTime;

    // 备注
    private String remarks;

    // 公司id
    private String companyId;

    // 在公司中的角色 1:创建者, 2:管理员, 3:成员
    private String role;

    // 公司状态 (-1:未认证, 0:已认证, 1:审核中, 2:审核失败)
    private String companyStatus;

    // 实名认证状态 (-1:未认证, 0:已认证, 1:审核中, 2:审核失败)
    private String isIdentification;

    // 加密规则 1：蜂巢平台， 2：新迪加密规则
    private String pwdRule;

    public @interface CommonValidate {
    }

}
