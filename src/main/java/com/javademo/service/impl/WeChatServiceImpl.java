package com.javademo.service.impl;

import com.alibaba.fastjson.JSON;
import com.javademo.common.constants.UserCommonConstants;
import com.javademo.common.constants.UserRedisConstants;
import com.javademo.common.exception.CommonException;
import com.javademo.common.model.ThirdPartyIdInfoDto;
import com.javademo.common.model.WeChatOfficialAccountQRCodeDto;
import com.javademo.entity.system.WeChatUserInfo;
import com.javademo.service.WeChatService;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by cj on 2020/3/16.
 */
@Slf4j
@Service
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired

    StringRedisTemplate stringRedisTemplate;


    /**
     * 获取微信公众号二维码
     */
    @Override
    public WeChatOfficialAccountQRCodeDto getWeChatOfficialAccountQRCode() {
        String token = getWeChatAccessToken();
        //生成二维码唯一参数
        String codeParam = UUID.randomUUID().toString().replace("-", "");
        WeChatOfficialAccountQRCodeDto weChatOfficialAccountQRCodeDto = new WeChatOfficialAccountQRCodeDto();
        weChatOfficialAccountQRCodeDto.setRandomCode(codeParam);
        Map<String, Object> params = new HashMap<>();
        params.put("expire_seconds", UserCommonConstants.WECHAT_QR_CODE_EXPIRE_SECONDS);  //过期时间
        params.put("action_name", "QR_STR_SCENE"); // 临时字符串参
        Map<String, Object> paramsZ2 = new HashMap<>();
        paramsZ2.put("scene_str", codeParam); //二维码唯一参数
        Map<String, Object> paramsZ1 = new HashMap<>();
        paramsZ1.put("scene", paramsZ2);
        params.put("action_info", paramsZ1);
        Map<String, Object> map = restTemplate.postForEntity(UserCommonConstants.WECHAT_CREATE_TICKET_URL + token,
                JSONObject.toJSONString(params), Map.class).getBody();
        log.info("map打印出来是{}——————————",map);
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
                Map.class, params).getBody();//通过微信获取token的地址，获取最新token
        return map.get("access_token");
    }


    /**
     * 获取微信openid和unionid
     *
     * @param code
     * @param state
     * @return
     */
    @Override
    public ThirdPartyIdInfoDto getWeChatIdInfo(String code, String state) {
        try {
            String getTokenUrl = String.format(UserCommonConstants.WECHAT_OPEN_GETTOKEN_URL,
                    UserCommonConstants.WECHAT_BIND_CLIENT_ID,
                    UserCommonConstants.WECHAT_BIND_CLIENT_SECRET, code);
            String str = restTemplate.getForEntity(getTokenUrl, String.class).getBody();
            if (StringUtils.isNotEmpty(str)) {
                JSONObject jo = JSON.parseObject(str);
                if (jo.get("errmsg") != null && StringUtils.isNotEmpty(jo.get("errmsg").toString())) {
                    throw new CommonException("调用微信获取token失败：" + jo.get("errmsg").toString());
                } else {
                    // 将state记入redis
                    String openid = jo.get("openid").toString();
                    String token = jo.get("access_token").toString();
                    stringRedisTemplate.opsForValue().set(
                            UserRedisConstants.KEY_THIRD_PARTY_BIND_STATE + state,
                            openid,
                            UserRedisConstants.EXPIRE_TIME_THIRD_PARTY_BIND_STATE,
                            UserRedisConstants.TIME_UNIT_THIRD_PARTY_BIND_STATE);
                    ThirdPartyIdInfoDto dto = new ThirdPartyIdInfoDto();
                    dto.setOpenid(openid);
                    // 查询微信用户信息
                    WeChatUserInfo weChatUserInfo = getWeChatPublicUserInfo(token, openid);
                    if (null != weChatUserInfo) {
                        dto.setUnionid(weChatUserInfo.getUnionid());
                    }
                    return dto;
                }
            }
        } catch (Exception e) {
            throw new CommonException("获取微信token信息失败");
        }
        return null;
    }


    /**
     * 获取微信开放平台用户信息
     *
     * @param token
     * @param openid
     * @return
     */
    private WeChatUserInfo getWeChatPublicUserInfo(String token, String openid) {
        Map<String, String> params = new HashMap<>();
        params.put("lang", "zh_CN");
        params.put("access_token", token);
        params.put("openid", openid);
        try {
            String weChatUserInfoStr = restTemplate.getForEntity(UserCommonConstants.WECHAT_OPEN_USER_INFO_URL,
                    String.class, params).getBody();
            WeChatUserInfo wechatUserInfo = JSONObject.parseObject(weChatUserInfoStr, WeChatUserInfo.class);
            return wechatUserInfo;
        } catch (Exception e) {
            throw new CommonException("获取微信开放平台用户信息失败");
        }
    }

}
