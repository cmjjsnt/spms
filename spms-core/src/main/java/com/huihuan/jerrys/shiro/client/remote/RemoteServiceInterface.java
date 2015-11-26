package com.huihuan.jerrys.shiro.client.remote;

import org.apache.shiro.session.Session;

import com.huihuan.jerrys.shiro.entity.Resource;

import java.io.Serializable;
import java.util.List;

/**
 * 提供远程调用服务的接口
 * 
 * @author jerrys
 * @Date 2015-09-24
 * @version 1.0.1
 * @since
 */
public interface RemoteServiceInterface {

	/**
	 * 通过key值获取会话Session
	 * 
	 * @param appKey
	 * @paraifm sessionId
	 * @return
	 */
	public Session getSession(String appKey, Serializable sessionId);

	/**
	 * 创建Session
	 * 
	 * @param session
	 * @return
	 */
	Serializable createSession(Session session);

	/**
	 * 更新会话
	 * 
	 * @param appKey
	 * @param session
	 */
	public void updateSession(String appKey, Session session);

	/**
	 * 删除会话Session
	 * 
	 * @param appKey
	 * @param session
	 */
	public void deleteSession(String appKey, Session session);

	/**
	 * 获取权限的上下文
	 * 
	 * @param appKey
	 * @param username
	 * @return
	 */
	public PermissionContext getPermissions(String appKey, String username);

	/**
	 * 根据应用Key和用户获取权限菜单
	 * 
	 * @param appKey
	 * @param username
	 * @return
	 */
	public List<Resource> getMenuByAppAndUser(String appKey, String username)throws Exception;

	/**
	 * 根据父级菜单ID获取子菜单
	 * 
	 * @param resourcePid
	 * @return
	 */
	public List<Resource> getMenuByParentId(Long resourcePid);
}
