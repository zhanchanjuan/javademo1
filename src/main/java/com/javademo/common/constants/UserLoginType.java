package com.javademo.common.constants;

/**
 * 用户登录类型
 *
 * @author
 */
public interface UserLoginType {

    /**
     * 手机号
     */
    Integer USER_LOGIN_TYPE_PHONE = 1;

    /**
     * 用户名
     */
    Integer USER_LOGIN_TYPE_USERNAME = 2;

    /**
     * 邮箱
     */
    Integer USER_LOGIN_TYPE_MAIL = 3;

    /**
     * QQ
     */
    Integer USER_LOGIN_TYPE_QQ = 11;

    /**
     * 微信
     */
    Integer USER_LOGIN_TYPE_WECHAT = 12;

    /**
     * 新浪微博
     */
    Integer USER_LOGIN_TYPE_SINA_BLOG = 13;

    /**
     * 微信公众号进入网页注册
     */
    Integer USER_LOGIN_TYPE_WECHAT_PUBLIC_WEB = 14;

}
