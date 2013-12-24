package com.kingdee.pear.controller;

import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/greeting")
public class GreetingController {

	@RequestMapping(method = RequestMethod.GET)
	public String greeting(ModelMap model) {
		CasAuthenticationToken token = (CasAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("username", token.getName());

		return "greeting";
	}

}
