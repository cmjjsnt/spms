package com.huihuan.jerrys.shiro.server.dao;

import java.util.List;

import com.huihuan.jerrys.shiro.entity.Authorization;

/**
 * 
 * 权限管理
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public interface AuthorizationDao {

	public Authorization createAuthorization(Authorization authorization);

	public Authorization updateAuthorization(Authorization authorization);

	public void deleteAuthorization(Long authorizationId);

	public Authorization findOne(Long authorizationId);

	public List<Authorization> findAll();

	public Authorization findByAppUser(Long appId, Long userId);
}
