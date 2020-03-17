package com.javademo.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cj
 * @date 2020/3/16
 *
 * 微信二维码dto
 */
@Data
public class WeChatOfficialAccountQRCodeDto implements Serializable {

    //二维码地址
    private String imageUrl;

    //二维码唯一随机参数
    private String randomCode;

    //过期时间
    private Integer expireSeconds;

    //二维码url地址(供自定义生成二维码使用)
    private String url;

}
