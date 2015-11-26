package com.huihuan.jerrys.shiro.server.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huihuan.jerrys.shiro.entity.Authorization;
import com.huihuan.jerrys.shiro.entity.Role;
import com.huihuan.jerrys.shiro.entity.User;
import com.huihuan.jerrys.shiro.server.dao.AuthorizationDao;
import com.huihuan.jerrys.shiro.server.web.exception.DefaultExceptionHandler;

/**
 * 
 * 不多讲自己看
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	@Autowired
	private AuthorizationDao authorizationDao;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AppService appService;

	public Authorization createAuthorization(Authorization authorization) {
		return merge(authorization);
	}

	public Authorization updateAuthorization(Authorization authorization) {
		if (authorization.getId() != null) {
			Authorization dbAuthorization = authorizationDao.findOne(authorization.getId());
			if (dbAuthorization != null) {
				authorizationDao.deleteAuthorization(dbAuthorization.getId());
			}
		}
		return merge(authorization);
	}

	public Authorization merge(Authorization authorization) {
		Authorization dbAuthorization = authorizationDao.findByAppUser(authorization.getAppId(),
				authorization.getUserId());
		if (dbAuthorization == null) {// 如果数据库中不存在相应记录 直接新增
			System.out.println("");
			return authorizationDao.createAuthorization(authorization);
		}

		if (dbAuthorization.equals(authorization)) {// 如果是同一条记录直接更新即可
			return authorizationDao.updateAuthorization(authorization);
		}

		dbAuthorization.setRoleId(authorization.getRoleId());

		if (dbAuthorization.getRoleId() == null) {// 如果没有角色 直接删除记录即可
			authorizationDao.deleteAuthorization(dbAuthorization.getId());
			return dbAuthorization;
		}
		// 否则更新
		return authorizationDao.updateAuthorization(dbAuthorization);
	}

	public void deleteAuthorization(Long authorizationId) {
		authorizationDao.deleteAuthorization(authorizationId);
	}

	@Override
	public Authorization findOne(Long authorizationId) {
		return authorizationDao.findOne(authorizationId);
	}

	@Override
	public List<Authorization> findAll() {
		return authorizationDao.findAll();
	}

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findRoles(String appKey, String username) {
		User user = userService.findByUsername(username);
		if (user == null) {
			return Collections.EMPTY_SET;
		}
		Long appId = appService.findAppIdByAppKey(appKey);
		if (appId == null) {
			return Collections.EMPTY_SET;
		}
		Authorization authorization = authorizationDao.findByAppUser(appId, user.getId());
		if (authorization == null) {
			return Collections.EMPTY_SET;
		}
		return roleService.findRoles(authorization.getRoleId());
	}

	@Override
	public Role findRolesByAppkeyAndUserName(String appKey, String username) throws UnauthenticatedException {
		User user = userService.findByUsername(username);
		System.out.println("-----------------------"+user+"------------------------");
		if (user == null) {
			return null;
		}
		Long appId = appService.findAppIdByAppKey(appKey);
		System.out.println("-----------------------app:"+appId+"------------------------");
		if (appId == null) {
			return null;
		}

		Authorization authorization = authorizationDao.findByAppUser(appId, user.getId());
		System.out.println("-----------------------app:"+appId+"------------------------");
		if (authorization == null) {
			throw new UnauthenticatedException(
					"this is  unauthorization info to the  app:" + appId + "-------user:" + user + "");
		}

		if (authorization.getRoleId() == null) {
			throw new UnauthenticatedException(
					"Permissions do not specify roles for " + "{user:" + username + ";  appkey:" + appKey + "}");
		}
		Long[] roleIds = { authorization.getRoleId() };
		List<Role> roles = roleService.findRolesByIds(roleIds);
		if (roles == null || roles.size() == 0) {
			throw new UnauthenticatedException(
					"Cannot  query to related roles for { appkey:" + appKey + "-------user:" + username + "}");
		}
		return roles.get(0);
	}

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<String> findPermissions(String appKey, String username) {
		User user = userService.findByUsername(username);
		if (user == null) {
			return Collections.EMPTY_SET;
		}
		Long appId = appService.findAppIdByAppKey(appKey);
		if (appId == null) {
			return Collections.EMPTY_SET;
		}
		Authorization authorization = authorizationDao.findByAppUser(appId, user.getId());
		if (authorization == null) {
			return Collections.EMPTY_SET;
		}
		Long[] roleIds = { authorization.getRoleId() };
		return roleService.findPermissions(roleIds);
	}

}
