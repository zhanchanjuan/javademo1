package com.javademo.service;

import com.javademo.common.model.ThirdPartyIdInfoDto;
import com.javademo.common.model.WeChatOfficialAccountQRCodeDto;

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


}
