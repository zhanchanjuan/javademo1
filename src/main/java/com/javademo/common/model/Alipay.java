package com.javademo.common.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

/**
 * 支付宝支付接口
 * @author shuyi
 * @date 2020/3/22
 */
@Component
public class Alipay {

	
	/**
	 * 支付接口
	 * @param alipayBean
	 * @return
	 * @throws AlipayApiException
	 */
	public String pay(AlipayBean alipayBean) throws AlipayApiException {
		// 获得初始化的AlipayClient
		String serverUrl = AlipayProperties.getGatewayUrl();
		String appId = AlipayProperties.getAppId();
		String privateKey = AlipayProperties.getPrivateKey();
		String format = "json";
		String charset = AlipayProperties.getCharset();
		String alipayPublicKey = AlipayProperties.getPublicKey();
		String signType = AlipayProperties.getSignType();
		//根据Alipay公共开发平台的sdk开发文档
		// 1、对AlipayClient进行初始化设置(根据配置文件初始化)
		AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);

		// 2、设置请求参数
		String returnUrl = AlipayProperties.getReturnUrl();
		String notifyUrl = AlipayProperties.getNotifyUrl();
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		// 页面跳转同步通知页面路径
		alipayRequest.setReturnUrl(returnUrl);
		// 服务器异步通知页面路径  异步回调地址
		alipayRequest.setNotifyUrl(notifyUrl);
		// 封装参数  alipayBean前台传过来的订单对象
		alipayRequest.setBizContent(JSON.toJSONString(alipayBean));

		// 3、请求支付宝进行付款，并获取支付结果
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		// 返回付款信息
		return result;
	}
}
