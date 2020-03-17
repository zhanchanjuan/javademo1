package com.javademo.common.constants;

import java.util.concurrent.TimeUnit;

/**
 * RedisKey
 */
public interface UserRedisConstants {

    // 微信公众号扫码关注，缓存用户信息，前台会轮询请求，所以不必设置太久（设置回调后30秒过期）
    String KEY_WECHAT_PUBLIC_LOGIN = "wechat:public-login:";

    // 过期时间
    Integer EXPIRE_TIME_WECHAT_PUBLIC_LOGIN = 30;

    // 过期时间单位
    TimeUnit TIME_UNIT_WECHAT_PUBLIC_LOGIN = TimeUnit.SECONDS;

    // 第三方登录生成授权网址唯一码state（设置10分钟过期，扫码后10分钟未注册，再次注册state将失效）
    String KEY_THIRD_PARTY_LOGIN_STATE = "third-party:login-state:";

    // 过期时间
    Integer EXPIRE_TIME_THIRD_PARTY_LOGIN_STATE = 10;

    // 过期时间单位
    TimeUnit TIME_UNIT_THIRD_PARTY_LOGIN_STATE = TimeUnit.MINUTES;

    // 系统授权二维码key
    String KEY_SYSTEM_AUTH_QR_CODE = "system-auth:qr-code:";

    // 过期时间
    Integer EXPIRE_TIME_SYSTEM_AUTH_QR_CODE = 10;

    // 过期时间单位
    TimeUnit TIME_UNIT_SYSTEM_AUTH_QR_CODE = TimeUnit.MINUTES;

    // 第三方绑定state缓存过期时间 30秒（扫码后30秒必须进行绑定处理）
    String KEY_THIRD_PARTY_BIND_STATE = "third-party:bind-state:";

    // 过期时间
    Integer EXPIRE_TIME_THIRD_PARTY_BIND_STATE = 30;

    // 过期时间单位
    TimeUnit TIME_UNIT_THIRD_PARTY_BIND_STATE = TimeUnit.SECONDS;

    // 用户登录失败次数
    String KEY_USER_LOGIN_FAILURE_TIMES = "user:login-failure-times:";

    // 过期时间
    Integer EXPIRE_TIME_USER_LOGIN_FAILURE_TIMES = 15;

    // 过期时间单位
    TimeUnit TIME_UNIT_USER_LOGIN_FAILURE_TIMES = TimeUnit.MINUTES;

    // 用户登录userId
    String KEY_USER_LOGIN_USERID = "user:login:userid:";

    // 过期时间
    Integer EXPIRE_TIME_USER_LOGIN_USERID = 120;

    // 过期时间单位
    TimeUnit TIME_UNIT_USER_LOGIN_USERID = TimeUnit.MINUTES;

}
