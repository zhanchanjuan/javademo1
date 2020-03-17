package com.javademo.entity.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户账号类型
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredential implements Serializable {

    private static final long serialVersionUID = -2248371531589109768L;

    private Long id;

    private String userId;

    // 类型标记 (1:手机号, 2:用户名, 11:QQ, 12:微信, 13:新浪微博)
    private Integer type;

    // 第三方登录唯一主键
    private String openid;

    // 第三方应用联合id（不同app间通用）
    private String unionid;

    // 状态 (0:生效, 1:暂停, 2:冻结, 3:解绑)
    private Integer status;

    private Date createTime;

    private Date updateTime;

    // 备注
    private String remarks;

}
