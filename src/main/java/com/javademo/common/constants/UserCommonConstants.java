package com.javademo.common.constants;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
 * 用户模块普通常量类
 */
public class UserCommonConstants {

    // 自定义生成微信带参公众号二维码的过期时间(3分钟)
    public static Integer WECHAT_QR_CODE_EXPIRE_SECONDS = 180;

    // 第三方用户未在蜂巢平台注册
    public static Integer THIRD_PARTY_REGISTER_STATUS_NOT_REGISTER = 1;

    // 第三方用户已在蜂巢平台注册
    public static Integer THIRD_PARTY_REGISTER_STATUS_REGISTERED = 2;

    // 第三方用户状态 生效
    public static Integer THIRD_PARTY_ACCOUNT_STATUS_WORK = 0;

    // 第三方用户状态 暂停
    public static Integer THIRD_PARTY_ACCOUNT_STATUS_SUSPEND = 1;

    // 第三方用户状态 冻结
    public static Integer THIRD_PARTY_ACCOUNT_STATUS_BLOCK = 2;

    // 第三方用户状态 解绑
    public static Integer THIRD_PARTY_ACCOUNT_STATUS_UNBIND = 3;

    // 解析Url工具需要的前缀（解析QQ数据使用）
    public static String QQ_TEMP_URL = "www.baidu.com?";

    //获取微信token地址
    public static String WECHAT_GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?" +
            "grant_type={grant_type}&appid={appid}&secret={secret}";

    // 微信绑定回调地址
    public static String WECHAT_BIND_CALLBACK_URL = "http://njtesthoneycomb.zhizaoyun.com/bind/3";

    // 获取微信开放平台token及openid
    public static String WECHAT_OPEN_GETTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token" +
            "?grant_type=authorization_code&appid=%s&secret=%s&code=%s";

    // 获取微信用户信息url
    public static String WECHAT_OPEN_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?" +
            "access_token={access_token}&openid={openid}";

    // 微信创建票据URL
    public static String WECHAT_CREATE_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    //微信授权地址
    public static String WECHAT_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s" +
            "&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s";

    // 微信展示公众号二维码图片地址
    public static String WECHAT_SHOW_QR_CODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

    // 微信公众平台获取用户信息URL
    public static String WECHAT_GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?" +
            "access_token={access_token}&openid={openid}";

    // QQ认证url
    public static String QQ_AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize?" +
            "client_id=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s";

    // QQ回调地址
    public static String QQ_CALLBACK_URL = "http://njtesthoneycomb.zhizaoyun.com/skip/2";

    // QQ获取token地址
    public static String QQ_GETTOKEN_URL = "https://graph.qq.com/oauth2.0/token?" +
            "client_id=%s&client_secret=%s&code=%s&grant_type=authorization_code&redirect_uri=%s";

    // QQ获取unionid地址
    public static String QQ_GET_UNION_ID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s&unionid=1";

    // QQ获取openid地址
    public static String QQ_GET_OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    // QQ获取用户信息地址
    public static String QQ_GET_USER_INFO_URL = "https://graph.qq.com/user/get_user_info?" +
            "access_token=%s&oauth_consumer_key=%s&openid=%s";

    // QQ绑定回调地址
    public static String QQ_BIND_CALLBACK_URL = "http://njtesthoneycomb.zhizaoyun.com/bind/2";

    // 新浪微博认证url
    public static String SINA_BLOG_AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize?" +
            "client_id=%s&redirect_uri=%s&response_type=code&state=%s";

    // 新浪微博回调地址
    public static String SINA_BLOG_CALLBACK_URL = "http://njtesthoneycomb.zhizaoyun.com/skip/1";

    // 新浪微博绑定回调地址
    public static String SINA_BLOG_BIND_CALLBACK_URL = "http://njtesthoneycomb.zhizaoyun.com/bind/1";

    // 新浪微博获取token地址
    public static String SINA_BLOG_GETTOKEN_URL = "https://api.weibo.com/oauth2/access_token?" +
            "grant_type=authorization_code&client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";

    // 新浪微博获取用户信息地址
    public static String SINA_BLOG_GETUSERINFO_URL = "https://api.weibo.com/2/users/show.json?" +
            "access_token=%s&uid=%s";

    // 用户最大登录错误次数
    public static Integer USER_LOGIN_FAILURE_MAX_TIME = 5;

    // 注册类型
    public static String REGISTER_TYPE_PHONE = "1";

    public static String REGISTER_TYPE_QQ = "11";

    public static String REGISTER_TYPE_WECHAT = "12";

    public static String REGISTER_TYPE_SINA_BLOG = "13";

    // 用户数据来源 制造云
    public static String DATA_RESOURCE_ZHIZAOYUN = "LYLX001";

    // 用户数据来源 江苏省两化深度融合诊断服务平台
    public static String DATA_RESOURCE_JS_ZHENDUAN = "LYLX002";

    // 用户数据来源 江苏省汽车零部件工业互联网平台
    public static String DATA_RESOURCE_JS_QCLBJ_GYHLW = "LYLX003";

    // 成都电子信息工业互联网平台
    public static String DATA_RESOURCE_CD_DXXXGYHLW = "LYLX004";

    // 蜂巢平台加密类型
    public static String PWD_TYPE_HONEYCOMB = "1";

    // 新迪加密类型
    public static String PWD_TYPE_XINDI = "2";

    public static String RES_SYSTEM_MESSAGE_XINDI = "true";

    public static String STR_REGISTER_SUCCESS_TITLE = "制造云用户注册成功通知";

    public static String STR_REGISTER_SUCCESS_CONTENT = "尊敬的%s，您已经注册成为制造云平台会员，如您有什么疑问请联系我们！";

    public static String QQ_CLIENT_ID = "101844088";

    public static String QQ_CLIENT_SECRET = "76bb759704e78d07960d15c0f6f56976";

    public static String QQ_BIND_CLIENT_ID = "101845086";

    public static String QQ_BIND_CLIENT_SECRET = "8728497e049fdd51ddc620fde529ac01";

    public static String SINA_BLOG_CLIENT_ID = "3953874581";

    public static String SINA_BLOG_CLIENT_SECRET = "15f5eedf831783b426264bf65f1c2b53";

    public static String SINA_BLOG_BIND_CLIENT_ID = "3399090010";

    public static String SINA_BLOG_BIND_CLIENT_SECRET = "7e544d7bc5174a1c6327158048c825a7";

    public static String WECHAT_CLIENT_ID = "wxb853b7c906ab7548";// "wxb853b7c906ab7548";

    public static String WECHAT_CLIENT_SECRET = "bcd1a8fb570b077f191326436eeb440e";// "bcd1a8fb570b077f191326436eeb440e";

    public static String WECHAT_BIND_CLIENT_ID = "wxf222a8e6fe013124";

    public static String WECHAT_BIND_CLIENT_SECRET = "a9542caa7fc34f737da9a5bfe7f6811b";

    @Value("${honeycom.third-party.qq.login.appid}")
    public String QQ_CLIENT_ID_TEMP;

    @Value("${honeycom.third-party.qq.login.secret}")
    public String QQ_CLIENT_SECRET_TEMP;

    @Value("${honeycom.third-party.qq.bind.appid}")
    public String QQ_BIND_CLIENT_ID_TEMP;

    @Value("${honeycom.third-party.qq.bind.secret}")
    public String QQ_BIND_CLIENT_SECRET_TEMP;

    @Value("${honeycom.third-party.sina-blog.login.appid}")
    public String SINA_BLOG_CLIENT_ID_TEMP;

    @Value("${honeycom.third-party.sina-blog.login.secret}")
    public String SINA_BLOG_CLIENT_SECRET_TEMP;

    @Value("${honeycom.third-party.sina-blog.bind.appid}")
    public String SINA_BLOG_BIND_CLIENT_ID_TEMP;

    @Value("${honeycom.third-party.sina-blog.bind.secret}")
    public String SINA_BLOG_BIND_CLIENT_SECRET_TEMP;

    @Value("${honeycom.third-party.wechat.login.appid}")
    public String WECHAT_CLIENT_ID_TEMP;

    @Value("${honeycom.third-party.wechat.login.secret}")
    public String WECHAT_CLIENT_SECRET_TEMP;

    @Value("${honeycom.third-party.wechat.bind.appid}")
    public String WECHAT_BIND_CLIENT_ID_TEMP;

    @Value("${honeycom.third-party.wechat.bind.secret}")
    public String WECHAT_BIND_CLIENT_SECRET_TEMP;

    @Value("${honeycom.third-party.wechat.bind.callback}")
    public String WECHAT_BIND_CALLBACK_URL_TEMP;

    @Value("${honeycom.third-party.sina-blog.login.callback}")
    public String SINA_BLOG_CALLBACK_URL_TEMP;

    @Value("${honeycom.third-party.sina-blog.bind.callback}")
    public String SINA_BLOG_BIND_CALLBACK_URL_TEMP;

    @Value("${honeycom.third-party.qq.login.callback}")
    public String QQ_CALLBACK_URL_TEMP;

    @Value("${honeycom.third-party.qq.bind.callback}")
    public String QQ_BIND_CALLBACK_URL_TEMP;

    /**
     * PostConstruct方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次
     */
    @PostConstruct
    public void transValues() {
        QQ_CLIENT_ID = this.QQ_CLIENT_ID_TEMP;
        QQ_CLIENT_SECRET = this.QQ_CLIENT_SECRET_TEMP;
        QQ_BIND_CLIENT_ID = this.QQ_BIND_CLIENT_ID_TEMP;
        QQ_BIND_CLIENT_SECRET = this.QQ_BIND_CLIENT_SECRET_TEMP;
        SINA_BLOG_CLIENT_ID = this.SINA_BLOG_CLIENT_ID_TEMP;
        SINA_BLOG_CLIENT_SECRET = this.SINA_BLOG_CLIENT_SECRET_TEMP;
        SINA_BLOG_BIND_CLIENT_ID = this.SINA_BLOG_BIND_CLIENT_ID_TEMP;
        SINA_BLOG_BIND_CLIENT_SECRET = this.SINA_BLOG_BIND_CLIENT_SECRET_TEMP;
        WECHAT_CLIENT_ID = this.WECHAT_CLIENT_ID_TEMP;
        WECHAT_CLIENT_SECRET = this.WECHAT_CLIENT_SECRET_TEMP;
        WECHAT_BIND_CLIENT_ID = this.WECHAT_BIND_CLIENT_ID_TEMP;
        WECHAT_BIND_CLIENT_SECRET = this.WECHAT_BIND_CLIENT_SECRET_TEMP;
        WECHAT_BIND_CALLBACK_URL = this.WECHAT_BIND_CALLBACK_URL_TEMP;
        SINA_BLOG_CALLBACK_URL = this.SINA_BLOG_CALLBACK_URL_TEMP;
        SINA_BLOG_BIND_CALLBACK_URL = this.SINA_BLOG_BIND_CALLBACK_URL_TEMP;
        QQ_CALLBACK_URL = this.QQ_CALLBACK_URL_TEMP;
        QQ_BIND_CALLBACK_URL = this.QQ_BIND_CALLBACK_URL_TEMP;
    }

}
