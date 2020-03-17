package com.javademo.controller;

import com.javademo.common.exception.CommonException;
import com.javademo.common.model.ThirdPartyIdInfoDto;
import com.javademo.common.model.WeChatOfficialAccountQRCodeDto;
import com.javademo.common.model.WechatRegisterStatusDto;
import com.javademo.common.util.Result;
import com.javademo.common.util.ResultUtil;
import com.javademo.service.WeChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.PrintWriter;

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

    /**
     * 微信公众号回调
     *
     * @param body
     * @param request
     * @param response
     */
    @PostMapping(value = "/users-anon/wechat/publicCallback")
    public void publicCallback(@RequestBody(required = false) String body,
                               HttpServletRequest request, HttpServletResponse response) {
        log.info("================================微信URL回调=========================");
        log.info("body:" + body);
        WechatRegisterStatusDto dto = weChatService.parseXmlAndGetWeChatRegisterStatusInfo(body);
        if (dto != null) {
            //缓存信息
            weChatService.cacheWeChatLoginInfo(dto);
        } else {
            throw new CommonException("微信回调解析数据异常");
        }

    }

    @GetMapping(value = "/users-anon/wechat/publicCallback")
    public void check(@RequestBody(required = false) String body,
                      HttpServletRequest request, HttpServletResponse response) {
        log.info("================================签名验证=========================");

        /**
         * 微信签名验证
         */
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(request.getParameter("echostr"));

    }

    /**
     * 轮询查找微信扫码登录信息
     *
     * @param code
     * @return
     */
    @GetMapping(value = "/users-anon/getWeChatLoginInfo")
    public Result<WechatRegisterStatusDto> getWeChatLoginInfo(@NotBlank(message = "code不能为空") String code) {
        return ResultUtil.success(weChatService.getWeChatLoginInfo(code));
    }


    /**
     * 获取微信授权登录网址
     *
     * @param params 参数 例如 token值
     * @return
     */
    @GetMapping("/wechat/getWechatBindUrl")
    public Result<String> getWechatBindUrl(@NotBlank(message = "params不能为空") String params) {
        return ResultUtil.success(weChatService.getWechatBindUrl(params));
    }


}
