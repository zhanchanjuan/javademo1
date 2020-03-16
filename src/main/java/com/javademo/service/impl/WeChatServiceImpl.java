package com.javademo.service.impl;

import com.javademo.common.constants.UserCommonConstants;
import com.javademo.common.model.WeChatOfficialAccountQRCodeDto;
import com.javademo.service.WeChatService;
import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 13375 on 2020/3/16.
 */
@Slf4j
@Service
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeChatOfficialAccountQRCodeDto getWeChatOfficialAccountQRCode() {
        String token = getWeChatAccessToken();
        //生成二维码唯一参数
        String codeParam = UUID.randomUUID().toString().replace("-", "");
        WeChatOfficialAccountQRCodeDto weChatOfficialAccountQRCodeDto = new WeChatOfficialAccountQRCodeDto();
        weChatOfficialAccountQRCodeDto.setRandomCode(codeParam);
        Map<String, Object> params = new HashMap<>();
        params.put("expire_seconds", UserCommonConstants.WECHAT_QR_CODE_EXPIRE_SECONDS);
        params.put("action_name", "QR_STR_SCENE"); // 临时字符串参
        Map<String, Object> paramsZ2 = new HashMap<>();
        paramsZ2.put("scene_str", codeParam);
        Map<String, Object> paramsZ1 = new HashMap<>();
        paramsZ1.put("scene", paramsZ2);
        params.put("action_info", paramsZ1);
        Map<String, Object> map = restTemplate.postForEntity(UserCommonConstants.WECHAT_CREATE_TICKET_URL + token,
                JSONObject.toJSONString(params), Map.class).getBody();
        weChatOfficialAccountQRCodeDto.setExpireSeconds((Integer) map.get("expire_seconds"));
        weChatOfficialAccountQRCodeDto.setUrl((String) map.get("url"));
        weChatOfficialAccountQRCodeDto.setImageUrl(UserCommonConstants.WECHAT_SHOW_QR_CODE_URL + map.get("ticket"));
        return weChatOfficialAccountQRCodeDto;
    }

    private String getWeChatAccessToken() {
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", UserCommonConstants.WECHAT_CLIENT_ID);
        params.put("secret", UserCommonConstants.WECHAT_CLIENT_SECRET);
        Map<String, String> map = restTemplate.getForEntity(UserCommonConstants.WECHAT_GET_TOKEN_URL,
                Map.class, params).getBody();
        return map.get("access_token");
    }
}
