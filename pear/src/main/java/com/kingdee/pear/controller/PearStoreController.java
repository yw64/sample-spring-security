package com.kingdee.pear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingdee.pear.cxf.service.PearService;

@Controller
@RequestMapping("/store")
public class PearStoreController {

	@Autowired
	private PearService pearService;

	@RequestMapping(method = RequestMethod.GET)
	public String dec(ModelMap model) {
		this.pearService.dec();

		model.addAttribute("pearAmount", this.pearService.queryPearAmount());
		return "store";
	}

}
