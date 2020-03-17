package com.javademo.service;

import com.javademo.common.model.QQRegisterStatusDto;
import com.javademo.common.model.SinaBlogRegisterStatusDto;
import com.javademo.common.model.ThirdPartyIdInfoDto;
import com.javademo.entity.system.AppUser;
import com.javademo.entity.system.UserCredential;

/**
 * 第三方登录服务
 */
public interface ThirdPartyLoginService {

    /**
     * 根据第三方用户unionid获取蜂巢平台用户信息
     *
     * @param unionid
     * @param type
     * @return
     */
    AppUser getUserInfoByThirdPartyUnionId(String unionid, Integer type);

    /**
     * 根据第三方用户openid获取蜂巢平台用户信息
     *
     * @param openid
     * @param type
     * @return
     */
    AppUser getUserInfoByThirdPartyOpenId(String openid, Integer type);

    /**
     * 第三方用户绑定到蜂巢平台
     *
     * @param username
     * @param state
     * @param openid
     * @param type
     * @return
     */
    UserCredential thirdPartyBindingUser(String username, String state, String openid, String unionid, String type);

    /**
     * 第三方用户输入密码绑定到现有平台
     *
     * @param username
     * @param password
     * @param openid
     * @param type
     * @return
     */
    UserCredential thirdPartyBindUserByPwd(String username, String password, String openid, String unionid
            , String type, Boolean bind);

    /**
     * 获取QQ授权路径
     *
     * @param toUrl 登录后跳转地址
     * @return
     */
    String getQQAuthorizeUrl(String toUrl);

    /**
     * 获取qq授权绑定地址
     *
     * @param params 自定义参数
     * @return
     */
    String getQQBindUrl(String params);

    /**
     * 获取QQ用户在本平台是否已注册状态信息
     *
     * @return
     */
    QQRegisterStatusDto getQQLoginInfo(String code, String state);

    /**
     * 获取新浪微博授权路径
     *
     * @param toUrl 登录后跳转地址
     * @return
     */
    String getSinaBlogAuthorizeUrl(String toUrl);

    /**
     * 获取新浪微博授权路径
     *
     * @param params 自定义参数
     * @return
     */
    String getSinaBlogBindUrl(String params);

    /**
     * 取新浪微博用户在本平台是否已注册状态信息
     *
     * @return
     */
    SinaBlogRegisterStatusDto getSinaBlogLoginInfo(String code, String state);

    /**
     * 验证第三方登录state是否有效
     *
     * @param state
     * @param openid
     * @param delete 是否删除缓存
     * @return true: 有效， false：无效
     */
    Boolean checkLoginStateUseful(String state, String openid, Boolean delete);

    /**
     * 验证第三方绑定state是否有效
     *
     * @param state
     * @param openid
     * @param delete 是否删除缓存
     * @return true: 有效， false：无效
     */
    Boolean checkBindStateUseful(String state, String openid, Boolean delete);

    /**
     * 获取QQ openid unionid
     *
     * @param code  微信授权码
     * @param state 自定义state
     * @return
     */
    ThirdPartyIdInfoDto getQQIdInfo(String code, String state);

    /**
     * 获取新浪微博openid unionid
     *
     * @param code  微信授权码
     * @param state 自定义state
     * @return
     */
    ThirdPartyIdInfoDto getSinaBlogIdInfo(String code, String state);

}
