/**
 * Copyright (c) 2015-2100 huihuan.com
 */
package com.huihuan.jerrys.shiro.app.app1.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * helloword 的案例
 * @author jerrys
 * @Date 2015-09-23
 * @version 1.0.1
 * @since
 */
@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "success";
	}

	@RequestMapping(value = "/attr", method = RequestMethod.POST)
	public String setAttr(@RequestParam("key") String key, @RequestParam("value") String value) {
		SecurityUtils.getSubject().getSession().setAttribute(key, value);
		return "success";
	}

	@RequestMapping(value = "/attr", method = RequestMethod.GET)
	public String getAttr(@RequestParam("key") String key, Model model) {
		model.addAttribute("value", SecurityUtils.getSubject().getSession().getAttribute(key));
		return "success";
	}

	@RequestMapping("/role2")
	@RequiresRoles("role2")
	public String role2() {
		return "success";
	}
}
