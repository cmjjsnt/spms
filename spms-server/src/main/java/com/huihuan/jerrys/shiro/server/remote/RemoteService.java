package com.huihuan.jerrys.shiro.server.remote;

import java.io.Serializable;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huihuan.jerrys.shiro.client.remote.PermissionContext;
import com.huihuan.jerrys.shiro.client.remote.RemoteServiceInterface;
import com.huihuan.jerrys.shiro.entity.Resource;
import com.huihuan.jerrys.shiro.entity.Role;
import com.huihuan.jerrys.shiro.server.service.AuthorizationService;
import com.huihuan.jerrys.shiro.server.service.ResourceService;
import com.huihuan.jerrys.shiro.server.service.RoleService;

import net.sf.ehcache.util.FindBugsSuppressWarnings;

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
public class RemoteService implements RemoteServiceInterface {

	private static Logger log = Logger.getLogger(RemoteService.class);
	@Autowired
	private AuthorizationService authorizationService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private SessionDAO sessionDAO;

	@Override
	public Session getSession(String appKey, Serializable sessionId) {
		return sessionDAO.readSession(sessionId);
	}

	@Override
	public Serializable createSession(Session session) {
		return sessionDAO.create(session);
	}

	@Override
	public void updateSession(String appKey, Session session) {
		sessionDAO.update(session);
	}

	@Override
	public void deleteSession(String appKey, Session session) {
		System.out.println("--------------------printLog---------------------");
		System.out.println("--------------------" + session + "---------------------");
		System.out.println("--------------------printLog---------------------");
		sessionDAO.delete(session);
	}

	@Override
	public PermissionContext getPermissions(String appKey, String username) {
		PermissionContext permissionContext = new PermissionContext();
		permissionContext.setRoles(authorizationService.findRoles(appKey, username));
		permissionContext.setPermissions(authorizationService.findPermissions(appKey, username));
		return permissionContext;
	}

	@Override
	public List<Resource> getMenuByAppAndUser(String appKey, String username) throws Exception {

		Role role=null;
		try {
			role = authorizationService.findRolesByAppkeyAndUserName(appKey, username);
		} catch (UnauthenticatedException e) {
			throw new Exception("this role  is nullable!", e);
		}
		Set<String> permissions = authorizationService.findPermissions(appKey, username);
		List<Resource> menus = resourceService.findResourcesByAppKeyAndIds(appKey, role.getResourceIds());
		return menus;
	}

	@Override
	public List<Resource> getMenuByParentId(Long pid) {
		return null;
	}

}
