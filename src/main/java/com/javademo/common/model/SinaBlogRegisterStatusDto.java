package com.javademo.common.model;


import com.javademo.entity.system.SinaBlogUserInfo;
import lombok.Data;

/**
 * @author CJ
 * @date
 * 新浪微博注册状态dto
 */
@Data
public class SinaBlogRegisterStatusDto extends ThirdPartyRegisterStatusDto {

    //用户未注册，返回QQ用户信息
    private SinaBlogUserInfo sinaBlogUserInfo;

}
