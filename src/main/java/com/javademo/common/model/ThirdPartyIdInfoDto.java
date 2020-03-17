package com.javademo.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zcj
 * @date 2020/3/16
 * 记录第三方openid和unionid
 */
@Getter
@Setter
@ToString
public class ThirdPartyIdInfoDto implements Serializable {

    private static final long serialVersionUID = 3637754456325197186L;

    private String openid;

    private String unionid;

}
