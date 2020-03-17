package com.javademo.controller;

import com.javademo.common.model.ThirdPartyIdInfoDto;
import com.javademo.common.model.WeChatOfficialAccountQRCodeDto;
import com.javademo.common.util.Result;
import com.javademo.common.util.ResultUtil;
import com.javademo.service.WeChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * Created by cj on 2020/3/16.
 */

@Slf4j
@RestController
public class WeChatController {

    @Autowired
    private WeChatService weChatService;

    /**
     * 获取微信公众号二维码
     */
    @GetMapping(value = "/users-anon/getWeChatOfficialAccountQRCode")
    public Result<WeChatOfficialAccountQRCodeDto> getWeChatOfficialAccountQRCode() {
        return ResultUtil.success(weChatService.getWeChatOfficialAccountQRCode());
    }

    /**
     * 获取微信openid和unionid
     *
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/users-anon/wechat/getWechatIdInfo")
    public Result<ThirdPartyIdInfoDto> getWechatIdInfo(@NotBlank(message = "code不能为空") String code,
                                                       @NotBlank(message = "state不能为空") String state) {
        return ResultUtil.success(weChatService.getWeChatIdInfo(code, state));
    }



}
