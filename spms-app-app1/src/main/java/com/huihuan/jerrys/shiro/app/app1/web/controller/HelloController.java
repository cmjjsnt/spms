/**
 * Copyright (c) 2015-2020 huihuan.com
 */
package com.huihuan.jerrys.shiro.app.app1.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huihuan.jerrys.shiro.client.ClientSessionDAO;
import com.huihuan.jerrys.shiro.client.remote.PermissionContext;
import com.huihuan.jerrys.shiro.client.remote.RemoteServiceInterface;
import com.huihuan.jerrys.shiro.entity.Resource;
import com.huihuan.jerrys.shiro.entity.Resource.ResourceType;

/**
 * 
 * @author jerrys
 * @Date 2015-09-23
 * @version 1.0.1
 * @since
 */
@Controller
public class HelloController {

	@Autowired
	private RemoteServiceInterface remoteService;

	private static Logger log = Logger.getLogger(HelloController.class);

	@Value("${client.app.key}")
	private String appKey;

	
	@ModelAttribute("moduleType")
	public Resource.ResourceType resourceTypes() {
		return Resource.ResourceType.module;
	}
	
	@RequestMapping("/hello")
	public String hello(Model model) {

		List<Resource> menus = null;
		try {
			menus = remoteService.getMenuByAppAndUser(appKey,
					(String) SecurityUtils.getSubject().getPrincipal());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----------------------------" + appKey+ "-------------------------------");
		
		model.addAttribute("menus", menus);
		return "success";
	}

	@RequestMapping("/getpermission")
	public String getPermission(@RequestParam("key") String key, @RequestParam("username") String userame,
			Model model) {
		PermissionContext permissions = remoteService.getPermissions(key, userame);

		log.debug(permissions.getPermissions().toString());
		model.addAttribute("permissions", permissions.toString());
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

	@RequestMapping("/role1")
	@RequiresRoles("role1")
	public String role1() {
		return "success";
	}

	/**
	 * @param key
	 * @return
	 */
	@RequiresPermissions("app1:exit:view")
	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public String getAttr(@RequestParam("key") String key) {
		try {
			log.info("----------------------------" + SecurityUtils.getSubject().getSession().getId()
					+ "---------------------------------------");

			Session session = remoteService.getSession(key, SecurityUtils.getSubject().getSession().getId());
			log.info("************************" + session.toString() + "*****************************");
			if (SecurityUtils.getSubject().getSession() != null) {
				SecurityUtils.getSubject().logout();
			}
		} catch (InvalidSessionException e) {
			e.printStackTrace();
		}
		return "success";
	}

}
