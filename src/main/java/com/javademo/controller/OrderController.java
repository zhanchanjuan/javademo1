package com.javademo.controller;

import com.javademo.common.model.AlipayBean;
import com.javademo.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;


/**
 * 模拟订单接口
 * @author shuyi
 * @date 2020/3/22
 */
@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	private PayService payService;

	/**
	 * Alipay支付
	 * @param outTradeNo
	 * @param subject
	 * @param totalAmount
	 * @param body
	 * @return
	 * @throws AlipayApiException
	 */
	@PostMapping(value = "alipay")
	public String alipay(String outTradeNo, String subject, String totalAmount, String body) throws AlipayApiException {
		AlipayBean alipayBean = new AlipayBean();
		alipayBean.setOut_trade_no(outTradeNo);
		alipayBean.setSubject(subject);
		alipayBean.setTotal_amount(totalAmount);
		alipayBean.setBody(body);
		return payService.aliPay(alipayBean);
	}
}
