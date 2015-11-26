package com.huihuan.jerrys.shiro.server.service;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.UnauthenticatedException;

import com.huihuan.jerrys.shiro.entity.Authorization;
import com.huihuan.jerrys.shiro.entity.Role;

/**
 * 
 * 不多讲自己看
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public interface AuthorizationService {


    public Authorization createAuthorization(Authorization authorization);
    public Authorization updateAuthorization(Authorization authorization);
    public void deleteAuthorization(Long authorizationId);

    public Authorization findOne(Long authorizationId);
    public List<Authorization> findAll();

    /**
     * 根据AppKey和用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String appKey, String username);

    /**
     * 根据AppKey和用户名查找权限字符串
     * @param username
     * @return
     */
    public Set<String> findPermissions(String appKey, String username);

    /**
     * @param appKey
     * @param username
     * @return
     */
	public Role findRolesByAppkeyAndUserName(String appKey, String username)throws UnauthenticatedException  ;

}
