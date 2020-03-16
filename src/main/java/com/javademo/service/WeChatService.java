package com.javademo.service;

import com.javademo.common.model.WeChatOfficialAccountQRCodeDto;

/**
 * Created by 13375 on 2020/3/16.
 */
public interface WeChatService {
    /**
     * 获取微信公众号二维码地址
     *
     * @return
     */
    WeChatOfficialAccountQRCodeDto getWeChatOfficialAccountQRCode();


}
