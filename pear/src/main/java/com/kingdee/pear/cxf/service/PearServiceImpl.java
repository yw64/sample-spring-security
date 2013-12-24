package com.kingdee.pear.cxf.service;

import javax.jws.WebService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@WebService(serviceName="PearService", endpointInterface="com.kingdee.pear.cxf.service.PearService")
public class PearServiceImpl implements PearService {

	private static int amount = 10;

	@Override
	public int queryPearAmount() {
		return amount;
	}

	@Override
	public String inc() {
		amount++;
		return this.getUsernmae();
	}

	@Override
	public String dec() {
		amount--;
		return this.getUsernmae();
	}

	private String getUsernmae() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

}
