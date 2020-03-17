package com.javademo.common.model;


import com.javademo.entity.system.SysRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Set;

/**
 * 简单用户信息模型
 */
@Getter
@Setter
@ToString
public class UserSimpleDto implements Serializable {

    private static final long serialVersionUID = 5844151921088220341L;

    // 未加密id不进行序列化
    private transient String id;

    private String userid;

    private String username;

    private String headImgUrl;

    private String phone;

    // 状态，可标识是否禁用 默认0：启用
    private Integer status;

    private String remarks;

    //性别 1:男,2:女,3:保密
    private Integer sex;

    // 公司id
    private String companyId;

    // 在公司中的角色 1:创建者, 2:管理员, 3:成员
    private String role;

    // 公司状态 (-1:未认证, 0:已认证, 1:审核中, 2:审核失败)
    private String companyStatus;

    // 实名认证状态 (-1:未认证, 0:已认证, 1:审核中, 2:审核失败)
    private String isIdentification;

    private Set<SysRole> sysRoles;

    private Set<String> permissions;

//    public String getUserid() {
//        return (StringUtils.isEmpty(userid) && StringUtils.isNotEmpty(id)) ? DesUtil.desEncrypt(id) : userid;
//    }

}

