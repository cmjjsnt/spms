package com.huihuan.jerrys.shiro.client;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.huihuan.jerrys.shiro.client.remote.PermissionContext;
import com.huihuan.jerrys.shiro.client.remote.RemoteServiceInterface;

/**
 * <p>
 * 提供身份认证信息和授权信息，此处因为是其他应用依赖客户端，而这些应用不会实现身份认证，
 * 所以doGetAuthenticationInfo获取身份认证信息直接无须实现。
 * </p>
 * <p>
 * 另外获取授权信息，是通过远程暴露的服务RemoteServiceInterface获取，提供appKey和用户名获取即可
 * </p>
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public class ClientRealm extends AuthorizingRealm {
	private RemoteServiceInterface remoteService;
	private String appKey;

	public void setRemoteService(RemoteServiceInterface remoteService) {
		this.remoteService = remoteService;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * 获取用户的授权信息
	 * 
	 * @param PrincipalCollection
	 *            principals 是一个身份集合
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		PermissionContext context = remoteService.getPermissions(appKey, username);
		authorizationInfo.setRoles(context.getRoles());
		authorizationInfo.setStringPermissions(context.getPermissions());
		return authorizationInfo;
	}

	/**
	 * 获取用户身份相关的信息<br/>
	 * 
	 * @param AuthenticationToken    token
	 *            <p>
	 *            收集用户提交的身份（如用户名）及凭据（如密码）
	 *            </p>
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 永远不会被调用
		throw new UnsupportedOperationException("永远不会被调用");
	}
}
