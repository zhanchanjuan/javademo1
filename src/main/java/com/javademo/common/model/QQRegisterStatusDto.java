package com.javademo.common.model;


import com.javademo.entity.system.QQUserInfo;
import lombok.Data;

/**
 * @author Hawk
 * @date 2019/12/6
 * qq注册状态dto
 */
@Data
public class QQRegisterStatusDto extends ThirdPartyRegisterStatusDto {

    private static final long serialVersionUID = -6888559873982362014L;

    //用户未注册，返回QQ用户信息
    private QQUserInfo qqUserInfo;

}
