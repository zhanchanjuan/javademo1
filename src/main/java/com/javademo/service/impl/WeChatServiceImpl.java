package com.javademo.service.impl;

import com.alibaba.fastjson.JSON;
import com.javademo.common.constants.UserCommonConstants;
import com.javademo.common.constants.UserLoginType;
import com.javademo.common.constants.UserRedisConstants;
import com.javademo.common.exception.CommonException;
import com.javademo.common.model.*;

import com.javademo.entity.system.WeChatUserInfo;

import com.javademo.service.ThirdPartyLoginService;
import com.javademo.service.WeChatService;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;


import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    private StringRedisTemplate stringRedisTemplate;



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
            log.info("format后的获取token的url地址是{}"+getTokenUrl);
            //format拼接后的获取token的url地址是{}https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code&appid=wxf222a8e6fe013124&secret=a9542caa7fc34f737da9a5bfe7f6811b&code=111111
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
                        dto.setUnionid(weChatUserInfo.getUnionid()); //开放平台帐号下的应用，同一用户的unionid是唯一的
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

    @Override
    public WechatRegisterStatusDto parseXmlAndGetWeChatRegisterStatusInfo(String body) {
        WechatRegisterStatusDto dto = new WechatRegisterStatusDto();
        if (StringUtils.isNotEmpty(body)) {
            SAXReader saxReader = new SAXReader();
            Document document;
            try {
                document = saxReader.read(new ByteArrayInputStream(body.getBytes("UTF-8")));
                Element rootElt = document.getRootElement();
                String event = rootElt.elementText("Event");
                if (StringUtils.isNotEmpty(event)) {
                    if (event.equals("subscribe") || event.equals("SCAN")) {
                        String openid = rootElt.elementText("FromUserName");
                        String state = rootElt.elementText("EventKey");
                        if (event.equals("subscribe")) {
                            // 订阅会添加qrscene_，需要截取掉
                            state = state.substring(8);
                        }
                        WeChatUserInfo wechatUserInfo = getWeChatUserInfo(openid);
//                        AppUser appUser = thirdPartyLoginService.getUserInfoByThirdPartyUnionId(
//                                wechatUserInfo.getUnionid(),
//                                UserLoginType.USER_LOGIN_TYPE_WECHAT);
//                        if (null == appUser) {
//                            // 适配新迪数据用户，利用openid再查一次
//                            appUser = thirdPartyLoginService.getUserInfoByThirdPartyOpenId(
//                                    openid,
//                                    UserLoginType.USER_LOGIN_TYPE_WECHAT);
//                        }
//                        // 缓存第三方登录state与openid
//                        stringRedisTemplate.opsForValue().set(
//                                UserRedisConstants.KEY_THIRD_PARTY_LOGIN_STATE + state,
//                                openid,
//                                UserRedisConstants.EXPIRE_TIME_THIRD_PARTY_LOGIN_STATE,
//                                UserRedisConstants.TIME_UNIT_THIRD_PARTY_LOGIN_STATE);
//                        log.info("缓存第三方登录state信息:{}", state);
//                        if (appUser != null) {
//                            //已注册
//                            LoginDto loginDto = new LoginDto();
//                            loginDto.setUsername(appUser.getUsername());
//                            loginDto.setState(state);
//                            loginDto.setOpenid(openid);
//                            loginDto.setLoginType(UserLoginType.USER_LOGIN_TYPE_WECHAT.toString());
////                            LoginReturnDto loginReturnDto = gateWayClient.thirdPartyLogin(loginDto).getData();  从网关调用登录信息接口，这个是在cloud里配置的网关模块的接口 boot项目用不了，后期再进行优化调整
////                            dto.setLoginData(loginReturnDto);
//                            dto.setStatus(UserCommonConstants.THIRD_PARTY_REGISTER_STATUS_REGISTERED); // 已扫码已注册
                        } else {
                            //未注册
//                            String newUserName = UserCommonUtil.generateUserName(wechatUserInfo.getNickname(),
//                                    UserLoginType.USER_LOGIN_TYPE_WECHAT.toString());
//                            wechatUserInfo.setRegisterName(newUserName);
//                            dto.setWechatUserInfo(wechatUserInfo);
//                            dto.setStatus(UserCommonConstants.THIRD_PARTY_REGISTER_STATUS_NOT_REGISTER); // 已扫码未注册
//                        }
//                        dto.setState(state);
                    }
                } else {
                    throw new CommonException("微信回调event为空");
                }
            } catch (Exception e) {
                throw new CommonException("微信回调解析异常");
            }
        }
        return dto;
    }

    /**
     * 获取微信公众平台用户信息
     *
     * @param openid
     * @return
     */
    private WeChatUserInfo getWeChatUserInfo(String openid) {
        String token = getWeChatAccessToken();
        Map<String, String> params = new HashMap<>();
        params.put("lang", "zh_CN");
        params.put("access_token", token);
        params.put("openid", openid);
        WeChatUserInfo wechatUserInfo = restTemplate.getForEntity(UserCommonConstants.WECHAT_GET_USER_INFO_URL,
                WeChatUserInfo.class, params).getBody();
        return wechatUserInfo;
    }

    /**
     * 缓存微信用户信息
     *
     * @param wechatCodeDto
     */

    @Override
    public void cacheWeChatLoginInfo(WechatRegisterStatusDto wechatCodeDto) {
        stringRedisTemplate.opsForValue().set(
                UserRedisConstants.KEY_WECHAT_PUBLIC_LOGIN + wechatCodeDto.getState(),
                JSONObject.toJSONString(wechatCodeDto),
                UserRedisConstants.EXPIRE_TIME_WECHAT_PUBLIC_LOGIN,
                UserRedisConstants.TIME_UNIT_WECHAT_PUBLIC_LOGIN);
        log.info("缓存微信登录code信息:{},{}", wechatCodeDto);
    }

    /**
     * 根据code获取缓存中的微信用户信息
     *
     * @param code
     * @return
     */

    @Override
    public WechatRegisterStatusDto getWeChatLoginInfo(String code) {
        String str = stringRedisTemplate.opsForValue().get(UserRedisConstants.KEY_WECHAT_PUBLIC_LOGIN + code);
        if (str == null) {
            throw new CommonException("无效的code");
        }
        WechatRegisterStatusDto wechatUserInfo = JSONObject.parseObject(str, WechatRegisterStatusDto.class);
        stringRedisTemplate.delete(UserRedisConstants.KEY_WECHAT_PUBLIC_LOGIN + code);
        return wechatUserInfo;
    }


    /**
     * 获取微信授权绑定地址
     *
     * @param params
     * @return
     */
    @Override
    public String getWechatBindUrl(String params) {
        String redirect_uri = null;
        String code = "";
        try {
            redirect_uri = URLEncoder.encode(UserCommonConstants.WECHAT_BIND_CALLBACK_URL, "utf-8");
            code = UUID.randomUUID().toString().replace("-", "");
            if (StringUtils.isNotEmpty(params)) {
                code += URLEncoder.encode("|", "utf-8") + params;
            }
        } catch (UnsupportedEncodingException e) {
            throw new CommonException("getWechatAuthorizeUrl转码失败");
        }
        return String.format(UserCommonConstants.WECHAT_AUTHORIZE_URL, UserCommonConstants.WECHAT_BIND_CLIENT_ID,
                redirect_uri, code);
    }


}
