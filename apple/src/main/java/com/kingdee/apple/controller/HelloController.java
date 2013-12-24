package com.kingdee.apple.controller;

import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) throws Exception {
		// username
		CasAuthenticationToken token = (CasAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("username", token.getName());

		return "hello";
	}

}
