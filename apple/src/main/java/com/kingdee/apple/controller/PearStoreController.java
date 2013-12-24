package com.kingdee.apple.controller;

import java.net.URLEncoder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pear-store")
public class PearStoreController {

	private String lastProxyTicket = null;

	@RequestMapping(method = RequestMethod.GET)
	public String queryPearAmount(ModelMap model,
			@RequestParam("which") String which) throws Exception {

		// username
		CasAuthenticationToken token = (CasAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("username", token.getName());

		// storeAmount
		PearService pearService = this.createPearServiceProxy(which);
		int pearAmount = pearService.queryPearAmount();
		model.addAttribute("pearAmount", pearAmount);

		return "pear-store";
	}

	private PearService createPearServiceProxy(String which) throws Exception {
		String targetUrl = "http://www.pear.com:18080/sss-pear/services/PearService";

		// 取pt
		String pt;
		if ("old".equals(which) && this.lastProxyTicket != null) {
			pt = this.lastProxyTicket;
		}
		else {
			CasAuthenticationToken token = (CasAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
			this.lastProxyTicket = token.getAssertion().getPrincipal().getProxyTicketFor(targetUrl);
			pt = this.lastProxyTicket;
		}

		// 传pt
		String serviceUrl = targetUrl + "?ticket=" + URLEncoder.encode(pt, "UTF-8");

		// 创建WEB服务的本地代理
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress(serviceUrl);
		return factoryBean.create(PearService.class);
	}

}
