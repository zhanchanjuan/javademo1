package com.javademo.service;

import com.alipay.api.AlipayApiException;
import com.javademo.common.model.AlipayBean;

/**
 * 支付服务
 * @author shuyi
 * @date 2020/3/22
 */
public interface PayService {

	/**
	 * 支付宝支付接口
	 * @param alipayBean
	 * @return
	 * @throws AlipayApiException
	 */
	String aliPay(AlipayBean alipayBean) throws AlipayApiException;

}
