package com.javademo.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 微信appid和secret对象
 * @author
 */
@Getter
@Setter
@ToString
public class ThirdPartyLoginInfo implements Serializable {

    private static final long serialVersionUID = 5642714689230399982L;

    private String appid;

    private String secret;

}
