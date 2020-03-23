package com.javademo.service.impl;

import com.javademo.common.model.Alipay;
import com.javademo.common.model.AlipayBean;
import com.javademo.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;

/**
 * 支付实现类
 * @author shuyi
 * @date 2020/3/22
 */

@Service
@Slf4j
public class PayServiceImpl implements PayService {

	@Autowired
	private Alipay alipay;
	
	@Override
	public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
		return alipay.pay(alipayBean);
	}

}
