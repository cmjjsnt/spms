package com.huihuan.jerrys.shiro.server.web.controller;

import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huihuan.jerrys.shiro.client.remote.RemoteServiceInterface;
import com.huihuan.jerrys.shiro.entity.Resource;
import com.huihuan.jerrys.shiro.entity.User;
import com.huihuan.jerrys.shiro.server.Constants;
import com.huihuan.jerrys.shiro.server.service.AuthorizationService;
import com.huihuan.jerrys.shiro.server.service.ResourceService;
import com.huihuan.jerrys.shiro.server.web.bind.annotation.CurrentUser;

@Controller
public class IndexController {

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private AuthorizationService authorizationService;
	@Autowired
	private RemoteServiceInterface remoteService;

	@ModelAttribute("menuType")
	public Resource.ResourceType resourceTypes() {
		return Resource.ResourceType.menu;
	}

	@RequestMapping("/")
	public String index(@CurrentUser User loginUser, Model model) {
		Set<String> permissions = authorizationService.findPermissions(Constants.SERVER_APP_KEY,
				(String)SecurityUtils.getSubject().getPrincipal());
		List<Resource> menus=null;
		try {
			menus = remoteService.getMenuByAppAndUser(Constants.SERVER_APP_KEY, 	(String)SecurityUtils.getSubject().getPrincipal());
		} catch (Exception e) {
			e.printStackTrace();
			return "unauthorized";
		}
		model.addAttribute("menus", menus);
		return "index";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

}
