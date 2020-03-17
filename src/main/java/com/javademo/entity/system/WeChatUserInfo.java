package com.javademo.entity.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信个人用户信息
 * @author
 */
@Data
public class WeChatUserInfo implements Serializable {

    private static final long serialVersionUID = 6750304307961875043L;
    private String openid; // 用户的标识，对当前公众号唯一
    private String unionid; // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
    private String nickname; // 用户的昵称
    private String sex; // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String province; // 用户所在省份
    private String city; // 用户所在城市
    private String country; // 用户所在国家
    private String headimgurl; // 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
    private String registerName; // 平台注册名

}
