package com.javademo.service;

import com.javademo.common.model.ThirdPartyIdInfoDto;
import com.javademo.common.model.WeChatOfficialAccountQRCodeDto;
import com.javademo.common.model.WechatRegisterStatusDto;

/**
 * Created by cj on 2020/3/16.
 */
public interface WeChatService {
    /**
     * 获取微信公众号二维码地址
     *
     * @return
     */
    WeChatOfficialAccountQRCodeDto getWeChatOfficialAccountQRCode();


    /**
     * 获取微信openid unionid
     * @param code  微信授权码
     * @param state 自定义state
     * @return
     */
    ThirdPartyIdInfoDto getWeChatIdInfo(String code, String state);


    /**
     * 解析微信回调xml并获取微信用户在蜂巢平台注册信息
     *
     * @param body
     * @return
     */
    WechatRegisterStatusDto parseXmlAndGetWeChatRegisterStatusInfo(String body);

    /**
     * 缓存微信用户信息
     *
     * @param wechatCodeDto
     */
    void cacheWeChatLoginInfo(WechatRegisterStatusDto wechatCodeDto);

    /**
     * 根据code获取缓存中的微信用户信息
     *
     * @param code
     * @return
     */
    WechatRegisterStatusDto getWeChatLoginInfo(String code);


    /**
     * 获取微信授权绑定地址
     *
     * @param params
     * @return
     */
    String getWechatBindUrl(String params);



}
