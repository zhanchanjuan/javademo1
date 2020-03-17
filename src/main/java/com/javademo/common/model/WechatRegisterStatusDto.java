package com.javademo.common.model;


import com.javademo.entity.system.WeChatUserInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Hawk
 * @date 2019/12/5
 * 微信注册状态dto
 */
@Getter
@Setter
public class WechatRegisterStatusDto extends ThirdPartyRegisterStatusDto {

    private static final long serialVersionUID = -594282688046911940L;

    //自定义二维码唯一码state
    private String state;

    //用户未注册，返回微信用户信息
    private WeChatUserInfo wechatUserInfo;

}
